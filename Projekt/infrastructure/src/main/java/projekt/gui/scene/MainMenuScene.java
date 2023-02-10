package projekt.gui.scene;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import projekt.delivery.archetype.ProblemArchetype;
import projekt.delivery.archetype.ProblemGroup;
import projekt.delivery.archetype.ProblemGroupImpl;
import projekt.delivery.rating.*;
import projekt.delivery.service.BasicDeliveryService;
import projekt.delivery.service.BogoDeliveryService;
import projekt.delivery.service.DeliveryService;
import projekt.delivery.service.OurDeliveryService;
import projekt.delivery.simulation.SimulationConfig;
import projekt.gui.controller.MainMenuSceneController;
import projekt.io.IOHelper;
import projekt.delivery.archetype.runner.RunnerImpl;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* own imports*/
import javafx.scene.Node;

import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
/*end own imports*/

public class MainMenuScene extends MenuScene<MainMenuSceneController> {

    private int simulationRuns = 1;
    private DeliveryService.Factory deliveryServiceFactory;
    private Canvas problemMenu;
    private GraphicsContext problemContext;
    private HBox problemInfo;
    private Button openButton,createButton;
    public static ProblemArchetype problemSelection=null;
    private HashMap<String, ProblemArchetype> problemsByName=new HashMap<>();
    private Node[] temporaryNodes=new Node[5];
    private TableView<ProblemArchetype> tableOfProblems;
    private int problemChoiceBoxSelection=-1;
    public static List<String> knownProblemNames=new ArrayList<>();

    public MainMenuScene() {
        super(new MainMenuSceneController(), "Delivery Service Simulation");
    }

    @Override
    public void initComponents() {
        root.setCenter(createOptionsVBox());
        initProblemTable();
    }

    public void initProblemTable(){
        problemInfo=new HBox();
        problemInfo.setAlignment(Pos.CENTER);
        problemInfo.setSpacing(10);

        initAddProblemButton();
        //for(int i=0;i<100;i++)problems.add(problems.get(0));                 // TESTS HOW THE TABLE LOOKS WITH MANY ENTRIES
        reassignTable();

        centralRegion.setBottom(root.getCenter());
        centralRegion.setCenter(problemInfo);
        root.setCenter(centralRegion);

        // initialize problemMenu

        problemMenu=new Canvas(root.getWidth()*0.2,root.getHeight()*0.5);
        problemContext = problemMenu.getGraphicsContext2D();
    }
    public void initAddProblemButton(){
        openButton=new Button("Add from file");
        createButton=new Button("New");
        problemInfo.getChildren().clear();
        problemInfo.getChildren().add(openButton);
        problemInfo.getChildren().add(createButton);
        openButton.setOnAction(event -> {
            final List<ProblemArchetype> problemArchetypes=IOHelper.readProblems().
                stream().filter(Objects::nonNull).filter(problemArchetype -> {
                    boolean alreadyListed=false;
                    for(ProblemArchetype problemArchetype1:problems) alreadyListed|=problemArchetype1.name().
                        equals(problemArchetype.name());
                    return !alreadyListed;
                }).toList();

            ChoiceBox<ProblemArchetype> problemArchetypeChoiceBox=new ChoiceBox<>();

            problemArchetypeChoiceBox.setMinWidth(20);

            problemArchetypeChoiceBox.setItems(FXCollections.observableArrayList(problemArchetypes));
            problemArchetypeChoiceBox.setConverter(new StringConverter<ProblemArchetype>() {
                @Override
                public String toString(ProblemArchetype object) {
                    if(object==null) return "Select problem";
                    return object.name();
                }
                @Override
                public ProblemArchetype fromString(String string) {
                    throw new UnsupportedOperationException("There should be no way to get this exception");
                }
            });
            problemArchetypeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    problemChoiceBoxSelection=newValue.intValue();
                    System.out.println(IOHelper.readProblems().size());
                    int index=problemInfo.getChildren().indexOf(problemArchetypeChoiceBox);
                    problemInfo.getChildren().remove(problemArchetypeChoiceBox);
                    problemInfo.getChildren().add(index,openButton);

                    problems.add(problemArchetypes.get(problemChoiceBoxSelection));
                    reassignTable();

                    problemChoiceBoxSelection=-1;
                }
            });
            int index=problemInfo.getChildren().indexOf(openButton);
            problemInfo.getChildren().remove(openButton);
            problemInfo.getChildren().add(index,problemArchetypeChoiceBox);

        });
        createButton.setOnAction(event->{
            SceneSwitcher.loadScene(SceneSwitcher.SceneType.CREATEPROBLEM,getController().getStage());
        });
    }
    public void reassignTable(){
        TableView<ProblemArchetype> table=new TableView();
        tableOfProblems=table;
        TableColumn<ProblemArchetype,String> firstColumn=new TableColumn("Problem list");
        firstColumn.setMinWidth(100);
        table.setMaxWidth(200);
        firstColumn.setCellValueFactory(problem -> new SimpleStringProperty(problem.getValue().name()));
        List<ProblemArchetype> problemNames=new ArrayList<>();
        for(ProblemArchetype p:this.problems) problemNames.add(p);
        for(ProblemArchetype p:this.problems) problemsByName.put(p.name(),p);
        ObservableList<ProblemArchetype> tableNames=FXCollections.observableArrayList(problemNames);
        table.setItems(tableNames);
        table.getColumns().add(firstColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setFixedCellSize(25);
        table.setMaxHeight(Math.min(root.getHeight()*0.3,problems.size()*25+26));
        centralRegion.setTop(table);
        centralRegion.setAlignment(table,Pos.CENTER);
        table.getFocusModel().focusedCellProperty().addListener(
            new ChangeListener<TablePosition>() {
                @Override
                public void changed(ObservableValue<? extends TablePosition> observable,
                                    TablePosition oldValue, TablePosition newValue) {
                    initProblemMenu(table.getColumns().get(0).getCellObservableValue(newValue.getRow()).getValue());
                }
            }
        );
        knownProblemNames=problems.stream().map(ProblemArchetype::name).toList();
    }

    public void initProblemMenu(Object selectedProblem){
        if(selectedProblem instanceof String str){
            ProblemArchetype problem=problemsByName.get(str);
            if(problem==null) throw new RuntimeException("A problem was selected whose name is not in the list of problems.");
            else initProblemMenuGraphics(problem);
        } else{
            throw new RuntimeException("Object in problem table was not of type ProblemArcheType, but of type " +
                selectedProblem.getClass());
        }
    }
    public void initProblemMenuGraphics(ProblemArchetype problem){
        problemSelection=problem;
        if(problemInfo.getChildren().size()<3) {
            Button problemInfoSelectButton=new Button("Problem Info");
            Button removeProblem=new Button("Remove");
            problemInfoSelectButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    showInfoSelectedProblem();
                }
            });
            removeProblem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    problems.remove(problemSelection);
                    initAddProblemButton();
                    reassignTable();
                    problemSelection=null;
                    problemInfo.getChildren().removeAll(removeProblem,problemInfoSelectButton);
                }
            });
            problemInfo.getChildren().add(problemInfoSelectButton);
            problemInfo.getChildren().add(removeProblem);
        }
        }
    void showInfoSelectedProblem(){
        temporaryNodes[0]=root.getLeft();
        temporaryNodes[1]=root.getRight();
        temporaryNodes[2]=root.getCenter();
        temporaryNodes[3]=root.getTop();
        temporaryNodes[4]=root.getBottom();
        root.setLeft(null);
        root.setRight(null);
        root.setCenter(null);
        root.setTop(null);
        root.setBottom(null);
        Label problemTitle=new Label();
        problemTitle.setText("Information for Problem "+problemSelection.name());
        root.setTop(problemTitle);
        BorderPane.setAlignment(problemTitle,Pos.TOP_CENTER);
        problemTitle.setFont(new Font(20));
        problemTitle.setPadding(new Insets(50,50,50,50));
        Button returnToMainMenu=new Button("Return to Main Menu");
        Button advancedInfo=new Button("Advanced info");
        //root.setBottom(returnToMainMenu);
        VBox bottomBox=new VBox();
        bottomBox.getChildren().add(returnToMainMenu);
        bottomBox.getChildren().add(advancedInfo);
        //root.setAlignment(returnToMainMenu,Pos.BOTTOM_CENTER);
        //returnToMainMenu.setPadding(new Insets(50,50,50,50));
        root.setBottom(bottomBox);
        root.setAlignment(bottomBox,Pos.BOTTOM_CENTER);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setMargin(returnToMainMenu,new Insets(0,0,30,0));
        bottomBox.setMargin(advancedInfo,new Insets(0,0,30,0));
        returnToMainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetTemporaryNodes();
            }
        });
        advancedInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadAdvancedInfo();
            }
        });
        showInfoSelectedProblemRater();
        /*graphicCOntext.setFill(Color.BLUE);
        graphicCOntext.setStroke(Color.BROWN);
        graphicCOntext.setLineWidth(3);
        Font theFont = Font.font("Castellar", FontWeight.BOLD, 38);
        graphicCOntext.setFont(theFont);
        graphicCOntext.fillText("This is Text Area for Canvas", 61, 52);
        root.setRight(problemMenu);*/
    }
    void loadAdvancedInfo(){
        ProblemInfoScene problemInfoScene = (ProblemInfoScene) SceneSwitcher.loadScene(SceneSwitcher.SceneType.PROBLEMINFO, getController().getStage());
        problemInfoScene.initComponents();
    }
    void resetTemporaryNodes(){
        root.setLeft(temporaryNodes[0]);
        root.setRight(temporaryNodes[1]);
        root.setCenter(temporaryNodes[2]);
        root.setTop(temporaryNodes[3]);
        root.setBottom(temporaryNodes[4]);
    }
    void showInfoSelectedProblemRater(){
        TableView<RatingCriteria> tableOfRatingCriteria=new TableView<>();
        TableColumn<RatingCriteria,String> firstColumn=new TableColumn("Criterion"),secondColumn=new TableColumn<>("Rater");
        firstColumn.setMinWidth(40);
        secondColumn.setMinWidth(80);
        firstColumn.setCellValueFactory(criterion -> new SimpleStringProperty(criterion.getValue().toString()));
        List<RatingCriteria> listOfCriteria=new ArrayList<>();
        HashMap<RatingCriteria,Rater> raterByCriterion=new HashMap<>();
        for(RatingCriteria p:problemSelection.raterFactoryMap().keySet()) listOfCriteria.add(p);
        for(RatingCriteria p:problemSelection.raterFactoryMap().keySet()) raterByCriterion.put(p,problemSelection.raterFactoryMap().get(p).create());
        HashMap<RatingCriteria,String> criterionToString=new HashMap<>();
        for(RatingCriteria criteria:RatingCriteria.values()){
            if(listOfCriteria.contains(criteria)){
                Rater r=raterByCriterion.get(criteria);
                String totalInfo="";
                if(r instanceof AmountDeliveredRater rater){
                    totalInfo+="factor: "+rater.factorPublic;
                } else if(r instanceof TravelDistanceRater rater){
                    totalInfo+="factor: "+rater.factorPublic;
                } else if(r instanceof InTimeRater rater){
                    totalInfo+="ignoredTicksOff: "+rater.ignoredTicksOffPublic+", maxTicksOff: "+rater.maxTicksOffPublic;
                } else{
                    totalInfo+="no specification available";
                }
                criterionToString.put(criteria,totalInfo);
            } else criterionToString.put(criteria,"no rater selected for this criterion");
        }
        tableOfRatingCriteria.setEditable(false);
        secondColumn.setCellValueFactory(criterion->new SimpleStringProperty(criterionToString.get(criterion.getValue())));
        ObservableList<RatingCriteria> tableNames=FXCollections.observableArrayList(criterionToString.keySet());
        tableOfRatingCriteria.setItems(tableNames);
        //tableOfRatingCriteria.setPadding(new Insets(20,20,20,20));
        tableOfRatingCriteria.setBackground(root.getBackground());
        tableOfRatingCriteria.getColumns().add(firstColumn);
        tableOfRatingCriteria.getColumns().add(secondColumn);
        tableOfRatingCriteria.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        /*tableOfRatingCriteria.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>() {
            @Override
            public Boolean call(TableView.ResizeFeatures param) {
                return false;
            }
        });*/
        tableOfRatingCriteria.setMaxWidth(root.getWidth()*0.7);
        BorderPane problemTableCenter=new BorderPane();
        tableOfRatingCriteria.setOpaqueInsets(new Insets(30,30,30,30));
        problemTableCenter.setCenter(tableOfRatingCriteria);

        tableOfRatingCriteria.setFixedCellSize(25);
        tableOfRatingCriteria.setMaxHeight(Math.min(root.getHeight()*0.3, criterionToString.size()*25+26));
        root.setCenter(problemTableCenter);
    }

    /**
     * Initializes this {@link MainMenuScene} with the {@link ProblemArchetype} presets in the resource dir.
     */
    public void init() {
        super.init(IOHelper.readProblems());
    }

    private VBox createOptionsVBox() {
        VBox optionsVbox = new VBox();
        optionsVbox.setPrefSize(200, 100);
        optionsVbox.setAlignment(Pos.CENTER);
        optionsVbox.setSpacing(10);

        optionsVbox.getChildren().addAll(
            createStartSimulationButton(),
            createSimulationRunsHBox(),
            createDeliveryServiceChoiceBox()
            //TODO H11.2
        );

        optionsVbox.getChildren().stream()
            .filter(Button.class::isInstance)
            .map(Button.class::cast)
            .forEach(button -> button.setPrefSize(200, 50));

        return optionsVbox;
    }

    private Button createStartSimulationButton() {
        Button startSimulationButton = new Button("Start Simulation");
        startSimulationButton.setOnAction((e) -> {
            //store the SimulationScene
            AtomicReference<SimulationScene> simulationScene = new AtomicReference<>();
            //Execute the GUIRunner in a separate Thread to prevent it from blocking the GUI
            new Thread(() -> {
                ProblemGroup problemGroup = new ProblemGroupImpl(problems, Arrays.stream(RatingCriteria.values()).toList());
                new RunnerImpl().run(
                    problemGroup,
                    new SimulationConfig(20),
                    simulationRuns,
                    deliveryServiceFactory,
                    (simulation, problem, i) -> {
                        //CountDownLatch to check if the SimulationScene got created
                        CountDownLatch countDownLatch = new CountDownLatch(1);
                        //execute the scene switching on the javafx application thread
                        Platform.runLater(() -> {
                            //switch to the SimulationScene and set everything up
                            SimulationScene scene = (SimulationScene) SceneSwitcher.loadScene(SceneSwitcher.SceneType.SIMULATION, getController().getStage());
                            scene.init(simulation, problem, i, simulationRuns);
                            simulation.addListener(scene);
                            simulationScene.set(scene);
                            countDownLatch.countDown();
                        });

                        try {
                            //wait for the SimulationScene to be set
                            countDownLatch.await();
                        } catch (InterruptedException exc) {
                            throw new RuntimeException(exc);
                        }
                    },
                    (simulation, problem) -> {
                        //remove the scene from the list of listeners
                        simulation.removeListener(simulationScene.get());

                        //check if gui got stopped
                        return simulationScene.get().isClosed();
                    },
                    result -> {
                        //execute the scene switching on the javafx thread
                        Platform.runLater(() -> {
                            RaterScene raterScene = (RaterScene) SceneSwitcher.loadScene(SceneSwitcher.SceneType.RATING, getController().getStage());
                            raterScene.init(problemGroup.problems(), result);
                        });
                    });
            }).start();
        });

        return startSimulationButton;
    }

    private HBox createSimulationRunsHBox() {
        HBox simulationRunsHBox = new HBox();
        simulationRunsHBox.setMaxWidth(200);

        Label simulationRunsLabel = new Label("Simulation Runs:");
        TextField simulationRunsTextField = createPositiveIntegerTextField(value -> simulationRuns = value, 1);
        simulationRunsTextField.setMaxWidth(50);

        simulationRunsHBox.getChildren().addAll(simulationRunsLabel, createIntermediateRegion(0), simulationRunsTextField);

        return simulationRunsHBox;
    }

    private VBox createDeliveryServiceChoiceBox() {
        VBox deliveryServiceVBox = new VBox();
        deliveryServiceVBox.setMaxWidth(200);
        deliveryServiceVBox.setSpacing(10);

        HBox labelHBox = new HBox();
        Label label = new Label("Delivery Service:");
        labelHBox.getChildren().addAll(label);

        HBox choiceBoxHBox = new HBox();
        ChoiceBox<DeliveryService.Factory> choiceBox = new ChoiceBox<>();

        choiceBox.getItems().setAll(
            DeliveryService.BASIC,
            DeliveryService.OUR,
            DeliveryService.BOGO
        );
        choiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(DeliveryService.Factory deliveryService) {
                if (deliveryService instanceof BasicDeliveryService.Factory) {
                    return "Basic Delivery Service";
                }
                if (deliveryService instanceof OurDeliveryService.Factory) {
                    return "Our Delivery Service";
                }
                if (deliveryService instanceof BogoDeliveryService.Factory) {
                    return "Bogo Delivery Service";
                }

                return "Delivery Service";
            }

            @Override
            public DeliveryService.Factory fromString(String distanceCalculator) {
                throw new UnsupportedOperationException();
            }
        });

        choiceBox.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) ->
            deliveryServiceFactory = choiceBox.getItems().get((Integer) newValue));

        choiceBox.getSelectionModel().select(0);

        choiceBoxHBox.getChildren().addAll(choiceBox);

        deliveryServiceVBox.getChildren().addAll(label, choiceBox);

        return deliveryServiceVBox;
    }

    @Override
    public void initReturnButton() {
        ((HBox) root.getBottom()).getChildren().remove(returnButton);
    }

    @Override
    public MainMenuSceneController getController() {
        return controller;
    }
}
