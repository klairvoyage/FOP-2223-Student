package projekt.gui.scene;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import projekt.base.Location;
import projekt.delivery.routing.Region;
import projekt.delivery.routing.Vehicle;
import projekt.gui.controller.CreateRegionSceneController;
import projekt.gui.pane.MapPane;

import java.util.*;

public class CreateRegionScene extends MenuScene<CreateRegionSceneController>{
    private CreateProblemScene parent;
    private int counter=0;
    private boolean errorMsgRemoved=true;
    private javafx.stage.Stage parentStage;
    public Region.Builder regionBuilder;
    private BorderPane centralPane;
    private VBox menu;
    private MapPane mapPane;
    private Collection<Vehicle> vehicles;
    public CreateRegionScene(){super(new CreateRegionSceneController(),"Create a new region");}
    public CreateRegionScene(CreateProblemScene parent, javafx.stage.Stage parentStage){
        super(new CreateRegionSceneController(),"Create a new region");
        this.parent=parent;
        this.parentStage=parentStage;
        regionBuilder=Region.builder();
    }
    public CreateRegionScene(CreateRegionSceneController controller,String title,String... styleSheets){
        super(controller,title,styleSheets);
    }
    public CreateRegionSceneController getController(){
        return controller;
    }
    @Override
    public void initComponents() {
        initReturnButton();
        centralPane=new BorderPane();
        root.setCenter(centralPane);
        menu=new VBox();
        mapPane=new MapPane(true);
        regionBuilder=regionBuilder.distanceCalculator(parent.distanceCalculator);
        mapPane.RUNNING=false;
        if(vehicles==null) vehicles=new HashSet<Vehicle>();
        menu.setPadding(new Insets(20,20,20,20));
        menu.setSpacing(20);
        menu.setAlignment(Pos.TOP_CENTER);

        Button addNode=new Button("Add Node"), addEdge=new Button("Add edge"),
            removeNode=new Button("Remove Node"), removeEdge=new Button("Remove edge");
        Text nameText=new Text("Name:");
        TextField nameField=new TextField("");
        menu.getChildren().addAll(addNode,removeNode, addEdge, removeEdge);

        addNode.setOnAction(evt->{
            TextField nodeName=new TextField("Node's name");
            Button confirmButton=new Button("Confirm"), cancelButton=new Button("Cancel");
            HBox addNodeButtons=new HBox();
            addNodeButtons.getChildren().addAll(confirmButton,cancelButton);
            addNodeButtons.setSpacing(20);
            menu.getChildren().addAll(nodeName,addNodeButtons);
            cancelButton.setOnAction(evt2->{
                menu.getChildren().removeAll(nodeName,addNodeButtons);
            });
            confirmButton.setOnAction(evt2->{
                String s=nodeName.getText();
                if(s.equals("")){
                    createMsg("Enter a non-empty name");
                    return;
                }
                if(mapPane.getNodes().stream().map(node->node.getName()).toList().contains(s)){
                    createMsg("Name already taken");
                    return;
                } else {
                    double centralX=(getWidth()/2-mapPane.getTransformation().getTranslateX())
                        /mapPane.getTransformation().getScaleX(),
                            centralY=(getHeight()/2-mapPane.getTransformation().getTranslateY())/
                                mapPane.getTransformation().getScaleY();
                    int icentralX=(int)centralX, icentralY=(int)centralY;
                    boolean locationAlreadyTaken;
                    while(true){
                        locationAlreadyTaken=false;
                        for(Region.Node node:mapPane.getNodes()) locationAlreadyTaken|=(node.getLocation().getX()==icentralX&&
                                                                                        node.getLocation().getY()==icentralY);
                        if(locationAlreadyTaken) icentralX++;
                        else break;
                    }
                    mapPane.getMinScale();
                    System.out.println(mapPane.getTranslateX());
                    System.out.println(mapPane.getScaleX());
                    regionBuilder.addNode(s,new Location(icentralX,icentralY));
                    Region r=regionBuilder.build();
                    r.getNodes().stream().filter(node->!mapPane.getNodes().contains(node)).forEach(node->mapPane.addNode(node));
                    mapPane.redrawMap();
                }

            });
        });

        removeNode.setOnAction(evt->{
            if(mapPane.getSelectedNode()==null){
                createMsg("No node selected");
                return;
            }
            regionBuilder.removeComponent(mapPane.getSelectedNode().getName());
            for(Region.Edge edge:mapPane.getSelectedNode().getAdjacentEdges()) mapPane.removeEdge(edge);
            mapPane.removeNode(mapPane.getSelectedNode());
            mapPane.resetSelectedNode();
            mapPane.redrawMap();
        });

        centralPane.setLeft(menu);
        centralPane.setCenter(mapPane);
    }

    @Override
    public void initReturnButton() {
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!errorMsgRemoved) menu.getChildren().removeIf(node->node instanceof Text);
                parentStage.setScene(parent);
            }
        });
    }
    public void createMsg(String s){
        if(counter>0) return;
        Text message=new Text(s);
        message.setFont(new Font("Helvetica", 16));
        message.setFill(Color.RED);
        menu.getChildren().add(message);
        errorMsgRemoved=false;
        Timer timer=new Timer();
        counter=100;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    counter--;
                    message.setOpacity(counter/100d);
                    if(counter==0) {
                        timer.cancel();
                        menu.getChildren().remove(message);
                        errorMsgRemoved=true;
                    }
                });
            }
        }, 0,20);
    }
}
