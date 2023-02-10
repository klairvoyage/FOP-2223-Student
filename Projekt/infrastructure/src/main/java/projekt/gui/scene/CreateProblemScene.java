package projekt.gui.scene;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import projekt.base.ChessboardDistanceCalculator;
import projekt.base.DistanceCalculator;
import projekt.base.EuclideanDistanceCalculator;
import projekt.base.ManhattanDistanceCalculator;
import projekt.delivery.archetype.ProblemArchetype;
import projekt.delivery.archetype.ProblemArchetypeImpl;
import projekt.delivery.generator.FridayOrderGenerator;
import projekt.delivery.generator.OrderGenerator;
import projekt.delivery.rating.*;
import projekt.delivery.routing.*;
import projekt.gui.controller.CreateProblemSceneController;
import projekt.gui.controller.ProblemInfoSceneController;
import projekt.io.IOHelper;

//import javax.management.timer.Timer;
import java.util.*;

public class CreateProblemScene extends MenuScene<CreateProblemSceneController> {
    VBox preferences=new VBox();
    Slider simulationLengthSlider=new Slider(0,4000,1000);
    TextField nameTextField=new TextField("");
    List<String> takenNames;
    private boolean savedOnce=false, errorMsgRemoved=true;
    private CreateRegionScene createRegionScene=null;
    int counter=0;
    Node[] tmp=new Node[5];
    Map<RatingCriteria, Rater.Factory> raterMap=new HashMap<>();
    FridayOrderGenerator.FactoryBuilder factoryBuilder=FridayOrderGenerator.Factory.builder();
    double tmpAmount=0,tmpDistance=0;
    int tmpmaxT=0,tmpignored=0;
    public PathCalculator pathCalculator=new DijkstraPathCalculator();
    public DistanceCalculator distanceCalculator=new EuclideanDistanceCalculator();
    HBox cachedDijkstraHBox=new HBox();
    Region region= null;
    public CreateProblemScene(){
        super(new CreateProblemSceneController(),"Create a new problem");
        this.takenNames= IOHelper.readProblems().stream().map(ProblemArchetype::name).toList();
        initComponents();
    }
    public CreateProblemScene(CreateProblemSceneController controller,String title,String... styleSheets){
        super(controller,title,styleSheets);
    }
    private void saveToTmp(){
        tmp[0]=root.getTop();
        tmp[1]=root.getCenter();
        tmp[2]=root.getBottom();
        tmp[3]=root.getLeft();
        tmp[4]=root.getRight();
        root.setTop(null);
        root.setCenter(null);
        root.setBottom(null);
        root.setLeft(null);
        root.setRight(null);
    }
    public void initComponents(){
        initReturnButton();
        HBox simulationLengthBox=new HBox(), nameBox=new HBox();
        preferences.setSpacing(30);
        preferences.setPadding(new Insets(50,50,50,50));
        simulationLengthBox.setSpacing(30);
        nameBox.setSpacing(30);
        javafx.scene.text.Text simulationLengthText=new javafx.scene.text.Text("Simulation length:"),
                                nameText=new Text("Problem name:");
        simulationLengthBox.getChildren().addAll(simulationLengthText,simulationLengthSlider);
        simulationLengthSlider.setMaxWidth(200);
        simulationLengthSlider.showTickLabelsProperty().set(true);
        simulationLengthSlider.setMajorTickUnit(1000);
        simulationLengthText.setFill(Color.WHITE);
        simulationLengthText.setFont(new javafx.scene.text.Font("Helvetica",16));
        nameText.setFill(Color.WHITE);
        nameText.setFont(new Font("Helvetica",16));

        nameBox.getChildren().add(nameText);
        nameBox.getChildren().add(nameTextField);

        Button ratersButton=new Button("Rating");
        ratersButton.setOnAction(evt->{
                saveToTmp();
                Button returnToCreateProblemMenu=new Button("Return");
                HBox theReturnButton=new HBox();
                root.setBottom(theReturnButton);
                theReturnButton.getChildren().add(returnToCreateProblemMenu);
                theReturnButton.setAlignment(Pos.CENTER);
                theReturnButton.setPadding(new Insets(20,20,20,20));
                returnToCreateProblemMenu.setOnAction(evt2->{
                    root.setTop(tmp[0]);
                    root.setCenter(tmp[1]);
                    root.setBottom(tmp[2]);
                    root.setLeft(tmp[3]);
                    root.setRight(tmp[4]);
                });
                loadTheTable();
                HBox centerOfHeader=new HBox();
                centerOfHeader.setPadding(new Insets(20,20,20,20));
                Text newHeader=new Text("Rater preferences");
                newHeader.setTextAlignment(TextAlignment.CENTER);
                centerOfHeader.setAlignment(Pos.CENTER);
                centerOfHeader.getChildren().add(newHeader);
                newHeader.setFill(Color.WHITE);
                newHeader.setFont(new Font("Helvetica",32));
                root.setTop(centerOfHeader);
                Text noteText=new Text("Note: Leaving parameters empty deletes the rater!");
                noteText.setFill(Color.GREEN);
                noteText.setFont(new Font("Helvetica",16));
                ((VBox)root.getCenter()).getChildren().add(noteText);
        });

        Button ordersButton=new Button("Order generator");
        ordersButton.setOnAction(evt->{
            saveToTmp();
            Button returnToCreateProblemMenu=new Button("Return");
            HBox theReturnButton=new HBox();
            root.setBottom(theReturnButton);
            theReturnButton.getChildren().add(returnToCreateProblemMenu);
            theReturnButton.setAlignment(Pos.CENTER);
            theReturnButton.setPadding(new Insets(20,20,20,20));
            returnToCreateProblemMenu.setOnAction(evt2->{
                root.setTop(tmp[0]);
                root.setCenter(tmp[1]);
                root.setBottom(tmp[2]);
                root.setLeft(tmp[3]);
                root.setRight(tmp[4]);
            });
            loadTheOrders();
            HBox centerOfHeader=new HBox();
            centerOfHeader.setPadding(new Insets(20,20,20,20));
            Text newHeader=new Text("Order generator preferences");
            newHeader.setTextAlignment(TextAlignment.CENTER);
            centerOfHeader.setAlignment(Pos.CENTER);
            centerOfHeader.getChildren().add(newHeader);
            newHeader.setFill(Color.WHITE);
            newHeader.setFont(new Font("Helvetica",32));
            root.setTop(centerOfHeader);
        });

        Button regionButton=new Button("Region");
        regionButton.setOnAction(evt->{
            if(createRegionScene==null) createRegionScene=new CreateRegionScene(this,getController().getStage());
            createRegionScene.initComponents();
            getController().getStage().setScene(createRegionScene);
        });

        HBox advancedButtons=new HBox();
        advancedButtons.getChildren().addAll(ratersButton, ordersButton, regionButton);
        advancedButtons.setSpacing(20);

        HBox pathCalculatorHBox=new HBox();
        Text pathCalculatorText=new Text("Path calculator:");
        pathCalculatorText.setFill(Color.WHITE);
        pathCalculatorText.setFont(new Font("Helvetica",16));
        pathCalculatorHBox.setSpacing(20);

        ChoiceBox<String> pathCalculatorChoice=new ChoiceBox<>();
        pathCalculatorChoice.setItems(FXCollections.observableArrayList("Dijkstra path calculator","Cached dijkstra"));
        pathCalculatorChoice.setValue("Dijkstra path calculator");
        pathCalculatorChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue()==1){
                    pathCalculator=new CachedPathCalculator(new DijkstraPathCalculator(),1024);
                    int indexOfHBox=preferences.getChildren().indexOf(pathCalculatorHBox);
                    cachedDijkstraHBox=new HBox();
                    preferences.getChildren().add(indexOfHBox+1,cachedDijkstraHBox);
                    cachedDijkstraHBox.setSpacing(20);
                    Text cacheSize=new Text("Cache size:");
                    cacheSize.setFill(Color.WHITE);
                    cacheSize.setFont(new Font("Helvetica",16));
                    Slider cacheSizeSlider=new Slider(0,2048,1024);
                    cachedDijkstraHBox.getChildren().addAll(cacheSize,cacheSizeSlider);

                    cacheSizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            pathCalculator=new CachedPathCalculator(new DijkstraPathCalculator(), newValue.intValue());
                        }
                    });
                } else {
                    pathCalculator=new DijkstraPathCalculator();
                    preferences.getChildren().remove(cachedDijkstraHBox);
                }
            }
        });

        pathCalculatorHBox.getChildren().addAll(pathCalculatorText, pathCalculatorChoice);

        HBox distanceCalculatorHBox=new HBox();
        Text distanceCalculatorText=new Text("Distance calculator:");
        distanceCalculatorText.setFill(Color.WHITE);
        distanceCalculatorText.setFont(new Font("Helvetica",16));
        distanceCalculatorHBox.setSpacing(20);
        ChoiceBox<String> distanceCalculatorChoice=new ChoiceBox<>();
        distanceCalculatorChoice.setItems(FXCollections.observableArrayList("Euclidean distance","Manhattan distance",
                                                                            "Chessboard distance"));
        distanceCalculatorChoice.setValue("Euclidean distance");
        distanceCalculatorChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue()==0) distanceCalculator=new EuclideanDistanceCalculator();
                else if(newValue.intValue()==1) distanceCalculator=new ManhattanDistanceCalculator();
                else distanceCalculator=new ChessboardDistanceCalculator();
            }
        });
        distanceCalculatorHBox.getChildren().addAll(distanceCalculatorText,distanceCalculatorChoice);

        preferences.getChildren().addAll(nameBox,simulationLengthBox,advancedButtons,pathCalculatorHBox, distanceCalculatorHBox);
        root.setCenter(preferences);

        Button createProblemButton=new Button("Create problem");
        preferences.getChildren().add(createProblemButton);
        createProblemButton.setOnAction(evt->{
            if(!checkWhetherNameIsFine()){
                createErrorMessage("Name must not contain only whitespaces!");
            } else if(checkWhetherNameIsTaken()){
                createErrorMessage("Name is already taken!");
            } else if(!savedOnce){
                createErrorMessage("No raters specified");
            }else if(createRegionScene==null){
                createErrorMessage("No region specified");
            } else {
                createErrorMessage("Not implemented yet");
            }
        });
    }
    public void loadTheOrders(){
        VBox tableOfOrders=new VBox();
        tableOfOrders.setSpacing(20);
        tableOfOrders.setPadding(new Insets(20,20,20,20));
        Font defaultFont=new Font("Helvetica", 16);
        final Text numberOfOrders=new Text("Number of orders:"), deliveryInterval=new Text("Delivery interval:"),
                maxWeight=new Text("Maximal weight:"),variance=new Text("Variance:"), lastTick=new Text("Last tick:"),
                seedText=new Text("Random seed");
        for(Text text:List.of(numberOfOrders, deliveryInterval, maxWeight, variance, lastTick, seedText)){
            text.setFill(Color.WHITE);
            text.setFont(defaultFont);
        }
        final Slider numberOfOrdersSlider=new Slider(0,Math.max(2000,factoryBuilder.orderCount),factoryBuilder.orderCount),
                    deliveryIntervalSlider=new Slider(0,Math.max(30, factoryBuilder.deliveryInterval),factoryBuilder.deliveryInterval),
                    maxWeightSlider=new Slider(0,Math.max(1, factoryBuilder.maxWeight),factoryBuilder.maxWeight),
                    lastTickSlider=new Slider(0,Math.max(600,factoryBuilder.lastTick),factoryBuilder.lastTick),
                    seedTextSlider=new Slider(Integer.MIN_VALUE,Integer.MAX_VALUE,factoryBuilder.seed);
        for(Slider slider:List.of(numberOfOrdersSlider, deliveryIntervalSlider, maxWeightSlider, lastTickSlider, seedTextSlider)){
            slider.showTickLabelsProperty().set(!slider.equals(seedTextSlider));
            slider.setMajorTickUnit((slider.getMax()-slider.getMin())/5);
            slider.setMinWidth(200);
        }
        numberOfOrdersSlider.valueProperty().addListener(
            (observable, oldValue, newValue) -> factoryBuilder.orderCount=newValue.intValue());
        deliveryIntervalSlider.valueProperty().addListener(
            (observable, oldValue, newValue) -> factoryBuilder.deliveryInterval=newValue.intValue());
        maxWeightSlider.valueProperty().addListener(
            (observable, oldValue, newValue) -> factoryBuilder.maxWeight=newValue.intValue());
        lastTickSlider.valueProperty().addListener(
            (observable, oldValue, newValue) -> factoryBuilder.lastTick=newValue.intValue());
        seedTextSlider.valueProperty().addListener(
            (observable, oldValue, newValue) -> factoryBuilder.seed=newValue.intValue());

        root.setCenter(tableOfOrders);
        HBox numberOfOrdersHBox=new HBox(), deliveryIntervalHBox=new HBox(), maxWeightHBox=new HBox(),
            lastTickHBox=new HBox(), seedTextHBox=new HBox();
        numberOfOrdersHBox.getChildren().addAll(numberOfOrders,numberOfOrdersSlider);
        deliveryIntervalHBox.getChildren().addAll(deliveryInterval,deliveryIntervalSlider);
        maxWeightHBox.getChildren().addAll(maxWeight,maxWeightSlider);
        lastTickHBox.getChildren().addAll(lastTick,lastTickSlider);
        seedTextHBox.getChildren().addAll(seedText,seedTextSlider);
        numberOfOrdersHBox.setSpacing(30);
        deliveryIntervalHBox.setSpacing(30);
        maxWeightHBox.setSpacing(30);
        lastTickHBox.setSpacing(30);
        seedTextHBox.setSpacing(30);
        tableOfOrders.getChildren().addAll(numberOfOrdersHBox, deliveryIntervalHBox, maxWeightHBox, lastTickHBox, seedTextHBox);
    }
    public void loadTheTable(){
        VBox tableOfRaters=new VBox();
        List<RatingCriteria> listOfCriteria=Arrays.stream(RatingCriteria.values()).toList();
        // spacing and stuff
        tableOfRaters.setSpacing(20);
        tableOfRaters.setPadding(new Insets(20,20,20,20));
        final TextField factorAmount=new TextField(),factorDistance=new TextField(),
            maxTicksOffProp=new TextField(),ignoredTicksOffProp=new TextField();

        if(raterMap.containsKey(RatingCriteria.AMOUNT_DELIVERED)) factorAmount.setText(Double.toString(tmpAmount));
        else if(!savedOnce) factorAmount.setText("0.8");
        else factorAmount.setText("");
        if(raterMap.containsKey(RatingCriteria.TRAVEL_DISTANCE)) factorDistance.setText(Double.toString(tmpDistance));
        else if(!savedOnce) factorDistance.setText("0.8");
        else factorDistance.setText("0.8");
        if(raterMap.containsKey(RatingCriteria.IN_TIME)){
            maxTicksOffProp.setText(Integer.toString(tmpmaxT));
            ignoredTicksOffProp.setText(Integer.toString(tmpignored));
        } else if(!savedOnce){
            maxTicksOffProp.setText("25");
            ignoredTicksOffProp.setText("5");
        } else{
            maxTicksOffProp.setText("");
            ignoredTicksOffProp.setText("");
        }
        for(RatingCriteria crit:listOfCriteria){
            HBox theBox=new HBox();
            theBox.setSpacing(10);
            Text nameOfRatingCriterion=new Text(crit.name());
            nameOfRatingCriterion.setFill(Color.WHITE);
            nameOfRatingCriterion.setFont(new Font("Helvetica", 16));
            theBox.getChildren().add(nameOfRatingCriterion);
            if(crit.equals(RatingCriteria.AMOUNT_DELIVERED)||crit.equals(RatingCriteria.TRAVEL_DISTANCE)){
                Text factor=new Text("Enter factor:");
                factor.setFill(Color.WHITE);
                factor.setFont(new Font("Helvetica",16));
                theBox.getChildren().add(factor);

                TextField factorField;
                if(crit.equals(RatingCriteria.AMOUNT_DELIVERED)) factorField=factorAmount;
                else factorField=factorDistance;
                theBox.getChildren().add(factorField);

            } else if(crit.equals(RatingCriteria.IN_TIME)){
                Text maxTicksOff=new Text("maxTicksOff:"), ignoredTicksOff=new Text("ignoredTicksOff:");
                maxTicksOff.setFill(Color.WHITE);
                ignoredTicksOff.setFill(Color.WHITE);
                maxTicksOff.setFont(new Font("Helvetica",16));
                ignoredTicksOff.setFont(new Font("Helvetica",16));

                TextField maxTicksOffField, ignoredTicksOffField;
                maxTicksOffField=maxTicksOffProp;
                ignoredTicksOffField=ignoredTicksOffProp;
                theBox.getChildren().addAll(maxTicksOff, maxTicksOffField, ignoredTicksOff, ignoredTicksOffField);
            } else{
                Text notSupported=new Text("No custom raters for this criterion supported");
                notSupported.setFill(Color.WHITE);
                notSupported.setFont(new Font("Helvetica",16));
                theBox.getChildren().add(notSupported);
            }
            tableOfRaters.getChildren().add(theBox);
        }
        // rest
        root.setCenter(tableOfRaters);
        Button saveButton=new Button("Save raters");
        ((HBox)root.getBottom()).getChildren().add(0,saveButton);
        ((HBox)root.getBottom()).setSpacing(30);
        saveButton.setOnAction(evt->{
            if(!factorAmount.getText().equals("")){
                try{
                    double factor=Double.parseDouble(factorAmount.getText());
                    if(factor<0 || factor>1) throw new NumberFormatException();
                    else {
                        AmountDeliveredRater.FactoryBuilder factoryBuilder=AmountDeliveredRater.Factory.builder();
                        factoryBuilder.setFactor(factor);
                        raterMap.put(RatingCriteria.AMOUNT_DELIVERED,factoryBuilder.build());
                        tmpAmount=factor;
                    }
                } catch(NumberFormatException exc){
                    createErrorMessage("Please enter a number between 0 and 1 for AMOUND_DELIVERED-factor");
                    return;
                }
            }
            if(!factorDistance.getText().equals("")){
                try{
                    double factor=Double.parseDouble(factorDistance.getText());
                    if(factor<0 || factor>1) throw new NumberFormatException();
                    else {
                        TravelDistanceRater.FactoryBuilder factoryBuilder=TravelDistanceRater.Factory.builder();
                        factoryBuilder.setFactor(factor);
                        raterMap.put(RatingCriteria.TRAVEL_DISTANCE,factoryBuilder.build());
                        tmpDistance=factor;
                    }
                } catch(NumberFormatException exc){
                    createErrorMessage("Please enter a number between 0 and 1 for TRAVEL_DISTANCE-factor");
                    return;
                }
            }
            if(!maxTicksOffProp.getText().equals("")&&!ignoredTicksOffProp.getText().equals("")){
                try{
                    int maxT=Integer.parseInt(maxTicksOffProp.getText()),
                        ignoredT=Integer.parseInt(ignoredTicksOffProp.getText());
                    if(maxT<0 || ignoredT<0) throw new NumberFormatException();
                    else {
                        InTimeRater.FactoryBuilder factoryBuilder=InTimeRater.Factory.builder();
                        factoryBuilder.setIgnoredTicksOff(ignoredT);
                        factoryBuilder.setMaxTicksOff(maxT);
                        raterMap.put(RatingCriteria.IN_TIME,factoryBuilder.build());
                        tmpmaxT=maxT;
                        tmpignored=ignoredT;
                    }
                } catch(NumberFormatException exc){
                    createErrorMessage("Please enter non-negative integers for IN_TIME-parameters");
                    return;
                }
            }
            if(maxTicksOffProp.getText().equals("")^ignoredTicksOffProp.getText().equals("")) {
                createErrorMessage("Enter valid parameters or leave both fields blank");
                return;
            }
            unloadTable();
            savedOnce=true;
        });
    }
    public void unloadTable(){
        root.setTop(tmp[0]);
        root.setCenter(tmp[1]);
        root.setBottom(tmp[2]);
        root.setLeft(tmp[3]);
        root.setRight(tmp[4]);
        ((VBox)root.getCenter()).getChildren().removeIf(node->node instanceof Text);
    }
    public void createErrorMessage(String errorMessage){
        if(counter>0) return;
        if(!errorMsgRemoved) ((VBox)root.getCenter()).getChildren().removeIf(node->node instanceof Text);
        Text errorText=new Text(errorMessage);
        errorText.setFont(new Font("Helvetica",16));
        errorText.setFill(Color.RED);
        ((VBox)(root.getCenter())).getChildren().add(errorText);
        errorMsgRemoved=false;
        Timer timer=new Timer();
        counter=100;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    counter--;
                    errorText.setOpacity(counter/100d);
                    if(counter==0) {
                        timer.cancel();
                        ((VBox)(root.getCenter())).getChildren().remove(errorText);
                        errorMsgRemoved=true;
                    }
                });
            }
        }, 0,20);
    }
    public boolean checkWhetherNameIsFine(){
        String toCheck=nameTextField.getText();
        return !toCheck.equals("") && !toCheck.isBlank();
    }
    public boolean checkWhetherNameIsTaken(){
        boolean taken=false;
        for(String s:takenNames) if(s.equals(nameTextField.getText())) taken=true;
        return taken;
    }
    public void initReturnButton(){
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenuScene scene = (MainMenuScene) SceneSwitcher.loadScene(SceneSwitcher.SceneType.MAIN_MENU, getController().getStage());
                scene.init();
            }
        });
    }
    /*public ProblemArchetype createProblem() {
        ProblemArchetype newProblem=new ProblemArchetype() {
            @Override
            public OrderGenerator.Factory orderGeneratorFactory() {
                return null;
            }
            @Override
            public VehicleManager vehicleManager() {
                return null;
            }
            @Override
            public Map<RatingCriteria, Rater.Factory> raterFactoryMap() {
                return null;
            }
            @Override
            public long simulationLength() {
                return (long)simulationLengthSlider.getValue();
            }
            @Override
            public String name() {
                return nameTextField.getText();
            }
        };
        return newProblem;
    }*/
    public ProblemArchetypeImpl createProblem(){
        //return new ProblemArchetypeImpl();
        return null;
    }
}
