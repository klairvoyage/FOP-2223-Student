package projekt.gui.pane;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.Nullable;
import projekt.base.Location;
import projekt.delivery.routing.Region;
import projekt.delivery.routing.Vehicle;
import projekt.delivery.routing.VehicleManager;
import projekt.gui.TUColors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static projekt.gui.TUColors.*;

public class MapPane extends Pane {

    public static final float FIVE_TICKS_WIDTH = .125f;
    public static final float TEN_TICKS_WIDTH = .25f;

    private static final Color EDGE_COLOR = TUColors.COLOR_0C;

    private static final Color NODE_COLOR = COLOR_0D;
    private static final double NODE_DIAMETER = 15;

    private static final double IMAGE_SIZE = 0.1;
    private static final double OFFSET_Y=-4;
    private static final Color CAR_COLOR = TUColors.COLOR_6C;
    private static final Image CAR_IMAGE = loadImage("projekt/gui/scene/car.png", CAR_COLOR);
    private static final Image AUTOBAHN_PNG=loadImage("projekt/gui/scene/svg2.png");
    private static final double SCALING_FACTOR_AUTOBAHN_X=0.008;

    private static final double SCALING_FACTOR_AUTOBAHN_Y=0.01;
    public boolean RUNNING=true;

    private static final double SCALE_IN = 1.1;
    private static final double SCALE_OUT = 1 / SCALE_IN;
    private final double MAX_SCALE=100;
    private double MIN_SCALE=3;
    private boolean IN_EDIT_MODE=false;
    private double PRIORI_SHIFT_X=0,PRIORI_SHIFT_Y=0;

    private final AtomicReference<Point2D> lastPoint = new AtomicReference<>();
    private final AffineTransform transformation = new AffineTransform();

    private final Text positionText = new Text();

    public double nodesMinX,nodesMaxX,nodesMinY,nodesMaxY;
    private final Map<Region.Node, LabeledNode> nodes = new HashMap<>();
    private final Map<Region.Edge, LabeledEdge> edges = new HashMap<>();
    private final Map<Vehicle, ImageView> vehicles = new HashMap<>();
    private final Map<Region.Edge, ImageView> imagesOfEdges=new HashMap<>();
    private final Map<Region.Edge, Boolean> hasAnImage=new HashMap<>();
    private final List<Node> grid = new ArrayList<>();

    private Region.Node selectedNode;
    private Consumer<? super Region.Node> nodeSelectionHandler;
    private Consumer<? super Region.Node> nodeRemoveSelectionHandler;

    private Region.Edge selectedEdge;
    private Consumer<? super Region.Edge> edgeSelectionHandler;
    private Consumer<? super Region.Edge> edgeRemoveSelectionHandler;

    private Collection<Vehicle> selectedVehicles;
    private Consumer<? super Collection<Vehicle>> vehiclesSelectionHandler;
    private Consumer<? super Collection<Vehicle>> vehiclesRemoveSelectionHandler;
    public double prevWidth,prevHeight;
    private static final double APPROX_RATIO_OF_MAP=0.6;

    /**
     * Creates a new, empty {@link MapPane}.
     */
    public MapPane() {
        this(List.of(), List.of(), List.of());
    }

    public double getMinScale(){
        if(nodes.size()==0) return 3;
        Region.Node testNode=(Region.Node)nodes.keySet().toArray()[0];
        nodesMinX=testNode.getLocation().getX();
        nodesMaxX=nodesMinX;
        nodesMinY=testNode.getLocation().getY();
        nodesMaxY=nodesMinY;
        for(Region.Node node:nodes.keySet()){
            nodesMinX=Math.min(nodesMinX,node.getLocation().getX());
            nodesMaxX=Math.max(nodesMaxX,node.getLocation().getX());
            nodesMinY=Math.min(nodesMinY,node.getLocation().getY());
            nodesMaxY=Math.max(nodesMaxY,node.getLocation().getY());
        }
        if(nodes.size()<2) return 3;
        double res;
        if(this.getWidth()!=0) res=0.5*Math.min((double)(this.getWidth()+0.0)/(nodesMaxX-nodesMinX),
            (double)(this.getHeight()+0.0)/(nodesMaxY-nodesMinY));
        else res=0.5*Math.min((double)(prevWidth+0.0)/(nodesMaxX-nodesMinX),
            (double)(prevHeight*APPROX_RATIO_OF_MAP+0.0)/(nodesMaxY-nodesMinY));
        //System.out.println(minX+" "+maxX+" "+minY+" "+maxY);
        //System.out.println("Width: "+getWidth());
        return res;

    }
    public MapPane(boolean notRunning){
        if(!notRunning) throw new RuntimeException("This constructor is only for non-running maps!");
        //transformation.translate(getWidth()/2,getHeight()/2);
        transformation.scale(20,20);
        initListeners();
        drawGrid();
        drawPositionText();
        positionText.setFill(Color.WHITE);
        MIN_SCALE=getMinScale();
    }
    /**
     * Creates a new {@link MapPane} nad displays the given components.
     *
     * @param nodes    The {@link Region.Node}s to display.
     * @param edges    The {@link Region.Edge}s to display.
     * @param vehicles The {@link Vehicle}s to display.
     */
    public MapPane(Collection<? extends Region.Node> nodes,
                   Collection<? extends Region.Edge> edges,
                   Collection<? extends Vehicle> vehicles) {
        //TODO make configurable
        transformation.translate(350, 350);
        transformation.scale(20, 20);

        for (Region.Edge edge : edges) {
            addEdge(edge);
        }

        for (Region.Node node : nodes) {
            addNode(node);
        }

        for (Vehicle vehicle : vehicles) {
            addVehicle(vehicle);
        }

        initListeners();
        drawGrid();
        drawPositionText();
        positionText.setFill(Color.WHITE);
        MIN_SCALE=getMinScale();
    }

    // --- Edge Handling --- //

    /**
     * Adds an {@link Region.Edge} to this {@link MapPane} and displays it.
     *
     * @param edge The {@link Region.Edge} to display.
     */
    public void addEdge(Region.Edge edge) {
        edges.put(edge, drawEdge(edge));
        if(edge.getName().length()>0&&edge.getName().charAt(0)=='A'){
            ImageView edgeLogo = new ImageView();
            edgeLogo.setImage(AUTOBAHN_PNG);
            edgeLogo.scaleXProperty().set(AUTOBAHN_PNG.getWidth()*SCALING_FACTOR_AUTOBAHN_X);
            edgeLogo.scaleYProperty().set(AUTOBAHN_PNG.getHeight()*SCALING_FACTOR_AUTOBAHN_Y);
            edgeLogo.setX(transform(midPoint(edge)).getX() - edgeLogo.getImage().getWidth() / 2);
            edgeLogo.setY(transform(midPoint(edge)).getY() - edgeLogo.getImage().getHeight() / 2);
            getChildren().add(edgeLogo);
            imagesOfEdges.put(edge,edgeLogo);
            if(!hasAnImage.containsKey(edge)) hasAnImage.put(edge,true);
            Text tmp=edges.get(edge).text;
            getChildren().remove(tmp);
            getChildren().add(tmp);
        }else{
            if(!hasAnImage.containsKey(edge)) hasAnImage.put(edge,false);
        }
    }

    /**
     * Adds the {@link Region.Edge}s to this {@link MapPane} and displays them.
     *
     * @param edges The {@link Region.Edge}s to display.
     */
    public void addAllEdges(Collection<? extends Region.Edge> edges) {
        for (Region.Edge edge : edges) {
            addEdge(edge);
        }
    }

    /**
     * Removes the given {@link Region.Edge} from this {@link MapPane}.
     *
     * @param edge The {@link Region.Edge} to remove.
     */
    public void removeEdge(Region.Edge edge) {
        LabeledEdge labeledEdge = edges.remove(edge);

        if (labeledEdge != null) {
            getChildren().removeAll(labeledEdge.line(), labeledEdge.text());
        }
        if(hasAnImage.get(edge)) imagesOfEdges.remove(edge);
        hasAnImage.remove(edge);
    }

    /**
     * Returns the {@link Region.Edge} selected by the user by clicking onto it or its name.
     *
     * @return The {@link Region.Edge} selected by the user or null if no {@link Region.Edge} is selected.
     */
    public Region.Edge getSelectedEdge() {
        return selectedEdge;
    }

    /**
     * Sets the action that is supposed to be executed when the user selects an {@link Region.Edge}.
     *
     * @param edgeSelectionHandler The {@link Consumer} that executes the action.
     *                             The apply method of the {@link Consumer} will be called with
     *                             the selected {@link Region.Edge} as the parameter.
     */
    public void onEdgeSelection(Consumer<? super Region.Edge> edgeSelectionHandler) {
        this.edgeSelectionHandler = edgeSelectionHandler;
    }

    /**
     * Sets the action that is supposed to be executed when the user removes the selection of an {@link Region.Edge}.<p>
     * When a different {@link Region.Edge} is selected than the previous one only the action set by
     * {@link #onEdgeSelection(Consumer) will be executed.
     * <p>
     *
     * @param edgeRemoveSelectionHandler The {@link Consumer} that executes the action.
     *                                   The apply method of the {@link Consumer} will be called with
     *                                   the previously selected {@link Region.Edge} as the parameter.
     */
    public void onEdgeRemoveSelection(Consumer<? super Region.Edge> edgeRemoveSelectionHandler) {
        this.edgeRemoveSelectionHandler = edgeRemoveSelectionHandler;
    }

    /**
     * Updates the position of all {@link Region.Edge}s on this {@link MapPane}.
     */
    public void redrawEdges() {
        for (Region.Edge edge : edges.keySet()) {
            redrawEdge(edge);
        }
    }

    /**
     * Updates the position of the given {@link Region.Edge}.
     *
     * @param edge The {@link Region.Edge} to update.
     * @throws IllegalArgumentException If the given {@link Region.Edge} is not part of this {@link MapPane}.
     */
    public void redrawEdge(Region.Edge edge) {
        if (!edges.containsKey(edge)) {
            throw new IllegalArgumentException("The given edge is not part of this MapPane");
        }

        Point2D transformedMidPoint = transform(midPoint(edge));
        Point2D transformedPointA = transform(edge.getNodeA().getLocation());
        Point2D transformedPointB = transform(edge.getNodeB().getLocation());

        LabeledEdge labeledEdge = edges.get(edge);
        Text text=labeledEdge.text();
        labeledEdge.line().setStartX(transformedPointA.getX());
        labeledEdge.line().setStartY(transformedPointA.getY());

        labeledEdge.line().setEndX(transformedPointB.getX());
        labeledEdge.line().setEndY(transformedPointB.getY());

        if(hasAnImage.get(edge)){
            text.setX(transformedMidPoint.getX()-text.getLayoutBounds().getWidth()/2);
            text.setY(transformedMidPoint.getY()-OFFSET_Y);
            imagesOfEdges.get(edge).setX(transformedMidPoint.getX()-imagesOfEdges.get(edge).getImage().getWidth()/2);
            imagesOfEdges.get(edge).setY(transformedMidPoint.getY()-imagesOfEdges.get(edge).getImage().getHeight()/2);
        } else{
            text.setX(transformedMidPoint.getX());
            text.setY(transformedMidPoint.getY());
        }
    }

    // --- Node Handling --- //

    /**
     * Adds a {@link Region.Node} to this {@link MapPane} and displays it.
     *
     * @param node The {@link Region.Node} to display.
     */
    public void addNode(Region.Node node) {
        nodes.put(node, drawNode(node));
        MIN_SCALE=getMinScale();
    }

    public void update2(){
        transformation.scale(getMinScale()/transformation.getScaleX(),getMinScale()/
            transformation.getScaleY());
        double new_shift_x = PRIORI_SHIFT_X-(nodesMaxX + nodesMinX) / 2,
            new_shift_y = PRIORI_SHIFT_Y-(nodesMaxY+nodesMinY)/2;
        transformation.translate(new_shift_x, new_shift_y);
        PRIORI_SHIFT_X=(nodesMaxX+nodesMinX)/2;
        PRIORI_SHIFT_Y=(nodesMaxY+nodesMinY)/2;
    }

    public void update(){
        getMinScale();
        double new_shift_x = PRIORI_SHIFT_X-(nodesMaxX + nodesMinX) / 2,
            new_shift_y = PRIORI_SHIFT_Y-(nodesMaxY+nodesMinY)/2;
        transformation.translate(new_shift_x, new_shift_y);
        PRIORI_SHIFT_X=(nodesMaxX+nodesMinX)/2;
        PRIORI_SHIFT_Y=(nodesMaxY+nodesMinY)/2;

        /*System.out.println("Transformation scales: "+getMinScale()+" "+transformation.getScaleX()+
            " "+transformation.getScaleY());*/

        double tempTransformationX=(getWidth()/2-transformation.getTranslateX())/transformation.getScaleX(),
            tempTransformationY=(getHeight()/2-transformation.getTranslateY())/transformation.getScaleY();
        //System.out.println("Translations: "+tempTransformationX+" "+tempTransformationY);
        transformation.translate(tempTransformationX,tempTransformationY);
        //System.out.println(transformation.getTranslateX()+" "+transformation.getTranslateY());
        if(getMinScale()==0) throw new RuntimeException("Error");
        transformation.scale(getMinScale()/transformation.getScaleX(), getMinScale()/transformation.getScaleY());
        transformation.translate(-tempTransformationX,-tempTransformationY);

        redrawMap();
        redrawGrid();
    }

    /**
     * Adds the {@link Region.Node}s to this {@link MapPane} and displays them.
     *
     * @param nodes The {@link Region.Node}s to display.
     */
    public void addAllNodes(Collection<? extends Region.Node> nodes) {
        for (Region.Node node : nodes) {
            addNode(node);
        }
    }

    /**
     * Removes the given {@link Region.Node} from this {@link MapPane}.<p>
     * {@link Region.Edge}s and {@link Vehicle}s connected to the removed {@link Region.Node} will not get removed.
     *
     * @param node The {@link Region.Node} to remove.
     */
    public void removeNode(Region.Node node) {
        LabeledNode labeledNode = nodes.remove(node);

        if (labeledNode != null) {
            getChildren().removeAll(labeledNode.ellipse(), labeledNode.text());
        }
        MIN_SCALE=getMinScale();
        if(RUNNING) update();
    }

    /**
     * Returns the {@link Region.Node} selected by the user by clicking onto it or its name.
     *
     * @return The {@link Region.Node} selected by the user or null if no {@link Region.Node} is selected.
     */
    public Region.Node getSelectedNode() {
        return selectedNode;
    }

    /**
     * Sets the action that is supposed to be executed when the user selects an {@link Region.Node}.
     *
     * @param nodeSelectionHandler The {@link Consumer} that executes the action.
     *                             The apply method of the {@link Consumer} will be called with
     *                             the selected {@link Region.Node} as the parameter.
     */
    public void onNodeSelection(Consumer<? super Region.Node> nodeSelectionHandler) {
        this.nodeSelectionHandler = nodeSelectionHandler;
    }

    /**
     * Sets the action that is supposed to be executed when the user removes the selection of an {@link Region.Node}.<p>
     * When a different {@link Region.Node} is selected than the previous one only the action set by
     * {@link #onNodeSelection(Consumer)} will be executed.
     * <p>
     *
     * @param nodeRemoveSelectionHandler The {@link Consumer} that executes the action.
     *                                   The apply method of the {@link Consumer} will be called with
     *                                   the previously selected {@link Region.Edge} as the parameter.
     */
    public void onNodeRemoveSelection(Consumer<? super Region.Node> nodeRemoveSelectionHandler) {
        this.nodeRemoveSelectionHandler = nodeRemoveSelectionHandler;
    }

    /**
     * Updates the position of all {@link Region.Node}s on this {@link MapPane}.
     */
    public void redrawNodes() {
        for (Region.Node node : nodes.keySet()) {
            redrawNode(node);
        }
    }

    /**
     * Updates the position of the given {@link Region.Node}.
     *
     * @param node The {@link Region.Node} to update.
     * @throws IllegalArgumentException If the given {@link Region.Node} is not part of this {@link MapPane}.
     */
    public void redrawNode(Region.Node node) {
        if (!nodes.containsKey(node)) {
            throw new IllegalArgumentException("The given node is not part of this MapPane");
        }

        Point2D transformedMidPoint = transform(midPoint(node));

        LabeledNode labeledNode = nodes.get(node);

        labeledNode.ellipse().setCenterX(transformedMidPoint.getX());
        labeledNode.ellipse().setCenterY(transformedMidPoint.getY());

        labeledNode.text().setX(transformedMidPoint.getX() + NODE_DIAMETER);
        labeledNode.text().setY(transformedMidPoint.getY());
    }

    // --- Vehicle Handling --- //

    /**
     * Adds a {@link Vehicle} to this {@link MapPane} and displays it.
     *
     * @param vehicle The {@link Vehicle} to display.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle, drawVehicle(vehicle));
    }

    /**
     * Adds the {@link Vehicle}s to this {@link MapPane} and displays them.
     *
     * @param vehicles The {@link Vehicle}s to display.
     */
    public void addAllVehicles(Collection<? extends Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            addVehicle(vehicle);
        }
    }

    /**
     * Removes the given {@link Vehicle} from this {@link MapPane}.
     *
     * @param vehicle The {@link Vehicle} to remove.
     */
    public void removeVehicle(Vehicle vehicle) {
        ImageView imageView = vehicles.remove(vehicle);

        if (imageView != null) {
            getChildren().remove(imageView);
        }
    }

    /**
     * Returns the {@link Vehicle}s selected by the user by clicking onto the {@link Region.Node} or its name
     * the {@link Vehicle}s are on.
     *
     * @return The {@link Vehicle}s selected by the user or null if no {@link Region.Edge} is selected.
     */
    public Collection<Vehicle> getSelectedVehicles() {
        return selectedVehicles;
    }

    /**
     * Sets the action that is supposed to be executed when the user selects {@link Vehicle}s.
     *
     * @param vehiclesSelectionHandler The {@link Consumer} that executes the action.
     *                                 The apply method of the {@link Consumer} will be called with
     *                                 the selected {@link Vehicle}s as the parameter.
     */
    public void onVehicleSelection(Consumer<? super Collection<Vehicle>> vehiclesSelectionHandler) {
        this.vehiclesSelectionHandler = vehiclesSelectionHandler;
    }

    /**
     * Sets the action that is supposed to be executed when the user removes the selection of {@link Vehicle}s.<p>
     * When different {@link Vehicle}s are selected than the previous one only the action set by
     * {@link #onVehicleSelection(Consumer)} will be executed.
     * <p>
     *
     * @param vehiclesRemoveSelectionHandler The {@link Consumer} that executes the action.
     *                                       The apply method of the {@link Consumer} will be called with
     *                                       the previously selected {@link Vehicle}s as the parameter.
     */
    public void onVehicleRemoveSelection(Consumer<? super Collection<Vehicle>> vehiclesRemoveSelectionHandler) {
        this.vehiclesRemoveSelectionHandler = vehiclesRemoveSelectionHandler;
    }

    /**
     * Updates the position of all {@link Vehicle}s on this {@link MapPane}.
     */
    public void redrawVehicles() {
        for (Vehicle vehicle : vehicles.keySet()) {
            redrawVehicle(vehicle);
        }
    }

    /**
     * Updates the position of the given {@link Vehicle}.
     *
     * @param vehicle The {@link Vehicle} to update.
     * @throws IllegalArgumentException If the given {@link Vehicle} is not part of this {@link MapPane}.
     */
    public void redrawVehicle(Vehicle vehicle) {
        if (!vehicles.containsKey(vehicle)) {
            throw new IllegalArgumentException("The given vehicle is not part of this MapPane.");
        }

        Point2D transformedMidPoint = transform(midPoint(vehicle));

        ImageView imageView = vehicles.get(vehicle);
        imageView.setX(transformedMidPoint.getX() - imageView.getImage().getWidth() / 2);
        imageView.setY(transformedMidPoint.getY() - imageView.getImage().getHeight() / 2);
    }

    // --- Other Util --- //

    /**
     * Removes all components from this {@link MapPane}.
     */
    public void clear() {
        for (Region.Node node : new HashSet<>(nodes.keySet())) {
            removeNode(node);
        }

        for (Region.Edge edge : new HashSet<>(edges.keySet())) {
            removeEdge(edge);
        }

        for (Vehicle vehicle : new HashSet<>(vehicles.keySet())) {
            removeVehicle(vehicle);
        }
    }

    /**
     * Updates the position of all components on this {@link MapPane}.
     */
    public void redrawMap() {
        redrawNodes();
        redrawEdges();
        redrawVehicles();
        /*ImageView edgeLogo = new ImageView();
        edgeLogo.setImage(AUTOBAHN_PNG);
        System.out.println(AUTOBAHN_PNG.getWidth()*0.003);
        edgeLogo.scaleXProperty().set(AUTOBAHN_PNG.getWidth()*0.0003);
        edgeLogo.scaleYProperty().set(AUTOBAHN_PNG.getHeight()*0.0003);
        edgeLogo.setX(nodesMaxX - edgeLogo.getImage().getWidth() / 2);
        edgeLogo.setY(nodesMaxY - edgeLogo.getImage().getHeight() / 2);
        getChildren().add(edgeLogo);*/
    }

    // --- Private Methods --- //

    private void initListeners() {
        //zentriert die Position, auf die man clickt
        /*setOnMouseClicked(actionEvent->{
            transformation.translate(-(actionEvent.getX()-getWidth()/2)/transformation.getScaleX(),
                -(actionEvent.getY()-getHeight()/2)/transformation.getScaleY());
            redrawMap();
            redrawGrid();
        });*/

        setOnMouseDragged(actionEvent -> {
            Point2D point = new Point2D.Double(actionEvent.getX(), actionEvent.getY());
            Point2D diff = getDifference(point, lastPoint.get());

            transformation.translate(diff.getX() / transformation.getScaleX(), diff.getY() / transformation.getScaleY());

            redrawMap();
            redrawGrid();
            updatePositionText(point);

            lastPoint.set(point);

        });

        setOnScroll(event -> {
            MIN_SCALE=getMinScale();
            double scale = event.getDeltaY() > 0 ? SCALE_IN : SCALE_OUT;

            if (RUNNING&&(((transformation.getScaleX() < MIN_SCALE || transformation.getScaleY() < MIN_SCALE) && scale < 1)
                || ((transformation.getScaleX() > MAX_SCALE || transformation.getScaleX() > MAX_SCALE) && scale > 1))) {

                return;
            }
            //System.out.println("Width:"+getWidth()/2+", xtrans"+transformation.getTranslateX());
            //System.out.println("Height:"+getHeight()/2+", ytrans"+transformation.getTranslateY());
            double tempTransformationX=(getWidth()/2-transformation.getTranslateX())/transformation.getScaleX(),
                    tempTransformationY=(getHeight()/2-transformation.getTranslateY())/transformation.getScaleY();
            //System.out.println("Translations: "+tempTransformationX+" "+tempTransformationY);
            transformation.translate(tempTransformationX,tempTransformationY);
            //System.out.println(transformation.getTranslateX()+" "+transformation.getTranslateY());
            transformation.scale(scale, scale);
            transformation.translate(-tempTransformationX,-tempTransformationY);


            redrawMap();
            redrawGrid();
        });


        setOnMouseMoved(actionEvent -> {
            Point2D point = new Point2D.Double(actionEvent.getX(), actionEvent.getY());
            lastPoint.set(point);
            updatePositionText(point);
        });

        widthProperty().addListener((obs, oldValue, newValue) -> {
            setClip(new Rectangle(0, 0, getWidth(), getHeight()));
            redrawGrid();
            redrawMap();
            drawPositionText();
        });

        heightProperty().addListener((obs, oldValue, newValue) -> {
            setClip(new Rectangle(0, 0, getWidth(), getHeight()));
            redrawGrid();
            redrawMap();
            drawPositionText();
        });
    }

    private LabeledEdge drawEdge(Region.Edge edge) {
        if(!hasAnImage.containsKey(edge))
            hasAnImage.put(edge,edge.getName().length()>0&&edge.getName().charAt(0)=='A');
        Location a = edge.getNodeA().getLocation();
        Location b = edge.getNodeB().getLocation();

        Point2D transformedA = transform(a);
        Point2D transformedB = transform(b);

        Line line = new Line(transformedA.getX(), transformedA.getY(), transformedB.getX(), transformedB.getY());

        line.setStroke(edge.equals(selectedEdge) ? COLOR_9B : COLOR_0A);
        line.setStrokeWidth(1);

        Point2D transformedMidPoint = transform(midPoint(edge));
        //modified, so that indices like A67_1 will not appear
        Text text;
        javafx.scene.text.Font myFont;

        if(!hasAnImage.get(edge)) {
            text = new Text(transformedMidPoint.getX(), transformedMidPoint.getY(), edge.getName().split("_")[0]);
            text.setStroke(COLOR_0A);
        }else{
            String s;
            s=edge.getName().split("_")[0];
            s=s.substring(1);
            text=new Text(transformedMidPoint.getX(),transformedMidPoint.getY(),s);
            text.setStroke(Color.WHITE);
            myFont=new Font("Helvetica",text.getFont().getSize());
            text.setFont(myFont);
            text.setX(text.getX()-text.getLayoutBounds().getWidth()/2);
            text.setY(text.getY()-OFFSET_Y);
        }

        getChildren().addAll(line, text);

        line.setOnMouseClicked(e -> handleEdgeClick(line, edge));
        text.setOnMouseClicked(e -> handleEdgeClick(line, edge));

        //autobahnlogo start
        /*if(edge.getName().length()>0 &&edge.getName().charAt(0)=='A') {
            ImageView edgeLogo = new ImageView();
            edgeLogo.setImage(AUTOBAHN_PNG);
            edgeLogo.scaleXProperty().set(AUTOBAHN_PNG.getWidth() / 100);
            edgeLogo.scaleYProperty().set(AUTOBAHN_PNG.getHeight() / 100);
            edgeLogo.setX(transformedMidPoint.getX() - edgeLogo.getImage().getWidth() / 2);
            edgeLogo.setY(transformedMidPoint.getY() - edgeLogo.getImage().getHeight() / 2);
            getChildren().add(edgeLogo);
        }*/
        //autobahnlogo end

        return new LabeledEdge(line, text);
    }

    private LabeledNode drawNode(Region.Node node) {
        Point2D transformedPoint = transform(node.getLocation());

        Ellipse ellipse = new Ellipse(transformedPoint.getX(), transformedPoint.getY(), NODE_DIAMETER, NODE_DIAMETER);
        ellipse.setFill(NODE_COLOR);
        ellipse.setStrokeWidth(1);
        ellipse.setStroke(node.equals(selectedNode) ? COLOR_9B : COLOR_0A);
        setMouseTransparent(false);

        Text text = new Text(transformedPoint.getX(), transformedPoint.getY(), node.getName());
        text.setStroke(COLOR_0A);

        getChildren().addAll(ellipse, text);

        ellipse.setOnMouseClicked(e -> handleNodeClick(ellipse, node));
        text.setOnMouseClicked(e -> handleNodeClick(ellipse, node));

        return new LabeledNode(ellipse, text);
    }

    private ImageView drawVehicle(Vehicle vehicle) {
        Point2D transformedMidPoint = transform(midPoint(vehicle));

        var imageView = new ImageView();
        imageView.setImage(CAR_IMAGE);
        imageView.scaleXProperty().set(IMAGE_SIZE);
        imageView.scaleYProperty().set(IMAGE_SIZE);
        imageView.setX(transformedMidPoint.getX() - imageView.getImage().getWidth() / 2);
        imageView.setY(transformedMidPoint.getY() - imageView.getImage().getHeight() / 2);
        getChildren().add(imageView);

        return imageView;
    }

    @SuppressWarnings("SameParameterValue")
    private static Image loadImage(String name, Color color) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(MapPane.class.getClassLoader().getResource(name)));
            for (int x = 0; x < image.getWidth(); x++)
                for (int y = 0; y < image.getHeight(); y++)
                    if (image.getRGB(x, y) == java.awt.Color.BLACK.getRGB())
                        image.setRGB(x, y, new java.awt.Color(
                            (float) color.getRed(),
                            (float) color.getGreen(),
                            (float) color.getBlue(),
                            (float) color.getOpacity())
                            .getRGB());
            return SwingFXUtils.toFXImage(image, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Image loadImage(String name) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(MapPane.class.getClassLoader().getResource(name)));
            /*for (int x = 0; x < image.getWidth(); x++)
                for (int y = 0; y < image.getHeight(); y++)
                    if (image.getRGB(x, y) == java.awt.Color.BLACK.getRGB())
                        image.setRGB(x, y, new java.awt.Color(
                            (float) color.getRed(),
                            (float) color.getGreen(),
                            (float) color.getBlue(),
                            (float) color.getOpacity())
                            .getRGB());*/
            return SwingFXUtils.toFXImage(image, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void handleNodeClick(Ellipse ellipse, Region.Node node) {
        if (selectedNode != null) {
            nodes.get(selectedNode).ellipse().setStroke(EDGE_COLOR);
        }

        if (node.equals(selectedNode)) {
            if (nodeRemoveSelectionHandler != null) {
                nodeRemoveSelectionHandler.accept(selectedNode);
            }

            if (vehiclesRemoveSelectionHandler != null && selectedVehicles != null) {
                vehiclesRemoveSelectionHandler.accept(selectedVehicles);
            }

            selectedNode = null;
            selectedVehicles = null;
        } else {
            ellipse.setStroke(COLOR_9B);
            selectedNode = node;
            selectedVehicles = vehicles.keySet().stream()
                .filter(vehicle -> vehicle.getOccupied().getComponent().equals(selectedNode))
                .toList();

            if (nodeSelectionHandler != null) {
                nodeSelectionHandler.accept(selectedNode);
            }

            if (vehiclesSelectionHandler != null && selectedVehicles != null) {
                vehiclesSelectionHandler.accept(selectedVehicles);
            }
        }
    }

    private void handleEdgeClick(Line line, Region.Edge edge) {
        if (selectedEdge != null) {
            edges.get(selectedEdge).line().setStroke(EDGE_COLOR);
        }

        if (edge.equals(selectedEdge)) {
            if (edgeRemoveSelectionHandler != null) {
                edgeRemoveSelectionHandler.accept(selectedEdge);
            }
            selectedEdge = null;
        } else {
            line.setStroke(COLOR_9B);
            selectedEdge = edge;
            if (edgeSelectionHandler != null) {
                edgeSelectionHandler.accept(selectedEdge);
            }
        }
    }

    private void drawGrid() {
        Color color = COLOR_0D;

        int stepX = (int) (transformation.getScaleX() / 2);
        int stepY = (int) (transformation.getScaleY() / 2);

        if(stepX==0||stepY==0) return;

        int offsetX = (int) transformation.getTranslateX();
        int offsetY = (int) transformation.getTranslateY();

        // Vertical Lines
        for (int i = 0, x = offsetX % (stepX * 5); x <= getWidth(); i++, x += stepX) {
            Float strokeWidth = getStrokeWidth(i, offsetX % (stepX * 10) > stepX * 5);
            if (strokeWidth == null) continue;
            Line line = new Line(x, 0, x, getHeight());
            line.setStrokeWidth(strokeWidth);
            line.setStroke(color);
            getChildren().add(line);
            grid.add(line);
        }

        // Horizontal Lines
        for (int i = 0, y = offsetY % (stepY * 5); y <= getHeight(); i++, y += stepY) {
            Float strokeWidth = getStrokeWidth(i, offsetY % (stepY * 10) > stepY * 5);
            if (strokeWidth == null) continue;

            var line = new Line(0, y, getWidth(), y);
            line.setStrokeWidth(strokeWidth);
            line.setStroke(color);
            getChildren().add(line);
            grid.add(line);
        }
    }

    @Nullable
    private static Float getStrokeWidth(int i, boolean inverted) {
        float strokeWidth;
        if (i % 10 == 0) {
            strokeWidth = inverted ? TEN_TICKS_WIDTH : FIVE_TICKS_WIDTH;
        } else if (i % 5 == 0) {
            strokeWidth = inverted ? FIVE_TICKS_WIDTH : TEN_TICKS_WIDTH;
        } else {
            return null;
        }
        return strokeWidth;
    }

    private static Point2D locationToPoint2D(Location location) {
        return new Point2D.Double(location.getX(), location.getY());
    }

    private static Point2D getDifference(Point2D p1, Point2D p2) {
        return new Point2D.Double(p1.getX() - p2.getX(), p1.getY() - p2.getY());
    }

    private static Point2D midPoint(VehicleManager.Occupied<?> occupied) {
        if (occupied.getComponent() instanceof Region.Node) {
            return midPoint(((Region.Node) occupied.getComponent()).getLocation());
        } else if (occupied.getComponent() instanceof Region.Edge) {
            return midPoint((Region.Edge) occupied.getComponent());
        }
        throw new UnsupportedOperationException("unsupported type of component");
    }

    private static Point2D midPoint(Location location) {
        return new Point2D.Double(location.getX(), location.getY());
    }

    /* ATTENTION: THIS WAS CHANGED!!!*/
    private static Point2D midPoint(Vehicle vehicle) {
        /*if(vehicle.getOccupied().getComponent() instanceof Region.Edge edge)
            return weightedMidPoint(edge,vehicle.g);*/
        return midPoint(vehicle.getOccupied());
    }

    private static Point2D midPoint(Region.Node node) {
        return midPoint(node.getLocation());
    }

    private static Point2D midPoint(Region.Edge edge) {
        var l1 = edge.getNodeA().getLocation();
        var l2 = edge.getNodeB().getLocation();
        return new Point2D.Double((l1.getX() + l2.getX()) / 2d, (l1.getY() + l2.getY()) / 2d);
    }

    private static Point2D weightedMidPoint(Region.Edge edge,double lambda){
        var l1=edge.getNodeA().getLocation();
        var l2=edge.getNodeB().getLocation();
        return new Point2D.Double(lambda*l1.getX()+(1-lambda)*l2.getX(),lambda*l1.getY()+(1-lambda)*l2.getY());
    }

    private void redrawGrid() {
        getChildren().removeAll(grid);
        grid.clear();
        drawGrid();
    }

    private void drawPositionText() {
        positionText.setX(getWidth() - positionText.getLayoutBounds().getWidth());
        positionText.setY(getHeight());
        positionText.setText("(-, -)");
        if (!getChildren().contains(positionText)) {
            getChildren().add(positionText);
        }
    }

    private void updatePositionText(Point2D point) {
        point = getReverseTransform().transform(point, null);
        positionText.setText("(%d, %d)".formatted((int) point.getX(), (int) point.getY()));
        positionText.setX(getWidth() - positionText.getLayoutBounds().getWidth());
        positionText.setY(getHeight());
    }

    private AffineTransform getReverseTransform() {
        try {
            return transformation.createInverse();
        } catch (NoninvertibleTransformException e) {
            throw new IllegalStateException("transformation is not invertible");
        }
    }

    private Point2D transform(Point2D point) {
        return transformation.transform(point, null);
    }

    private Point2D transform(Location location) {
        return transformation.transform(locationToPoint2D(location), null);
    }

    private record LabeledEdge(Line line, Text text) {
    }

    private record LabeledNode(Ellipse ellipse, Text text) {
    }

    public double getActualXFromLocation(Location location){
        return location.getX()-0.5*(nodesMinX+nodesMaxX);
    }
    public double getActualYFromLocation(Location location){
        return location.getY()-0.5*(nodesMinY+nodesMaxY);
    }

    public void addTranslation(double deltaX,double deltaY){
        transformation.translate(deltaX,deltaY);
    }
    public Set<Region.Node> getNodes(){
        return nodes.keySet();
    }
    public AffineTransform getTransformation(){
        return transformation;
    }
    public void resetSelectedNode(){
        selectedNode=null;
    }
}
