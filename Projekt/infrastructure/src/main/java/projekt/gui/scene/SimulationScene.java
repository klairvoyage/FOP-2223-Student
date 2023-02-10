package projekt.gui.scene;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import projekt.delivery.archetype.ProblemArchetype;
import projekt.delivery.event.ArrivedAtEdgeEvent;
import projekt.delivery.event.ArrivedAtNodeEvent;
import projekt.delivery.event.Event;
import projekt.delivery.event.SpawnEvent;
import projekt.delivery.routing.Region;
import projekt.delivery.routing.VehicleManager;
import projekt.delivery.simulation.Simulation;
import projekt.delivery.simulation.SimulationListener;
import projekt.gui.controller.ControlledScene;
import projekt.gui.controller.SimulationSceneController;
import projekt.gui.pane.ControlsPane;
import projekt.gui.pane.MapPane;

import java.lang.module.Configuration;
import java.util.List;

/*own imports*/
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import projekt.delivery.routing.Vehicle;
import java.util.Collection;
import projekt.delivery.routing.ConfirmedOrder;
import javafx.scene.layout.HBox;

import javax.swing.event.ChangeEvent;
/*end own imports*/

public class SimulationScene extends Scene implements SimulationListener, ControlledScene<SimulationSceneController> {

    private final BorderPane root;
    private final SimulationSceneController controller;



    private MapPane mapPane;
    private ControlsPane controlsPane;
    private TableView<Vehicle> tableOfVehicles;
    private TableView<ConfirmedOrder> tableOfOrders;
    private VehicleManager vehicleManager;

    private boolean closed;
    private boolean messagedMapPane=false;
    public SimulationScene() {
        super(new BorderPane());
        controller = new SimulationSceneController();
        root = (BorderPane) getRoot();

        root.setPrefSize(700, 700);
        root.getStylesheets().addAll("projekt/gui/darkMode.css", "projekt/gui/simulationStyle.css");
    }

    public void init(Simulation simulation, ProblemArchetype problem, int run, int simulationRuns) {
        vehicleManager = simulation.getDeliveryService().getVehicleManager();
        Region region = vehicleManager.getRegion();

        mapPane = new MapPane(region.getNodes(), region.getEdges(), vehicleManager.getVehicles());
        mapPane.prevWidth=getWidth();
        mapPane.prevHeight=getHeight();

        controlsPane = new ControlsPane(simulation, problem, run, simulationRuns, problem.simulationLength());
        TitledPane titledControlsPane = new TitledPane("Controls", controlsPane);
        titledControlsPane.setCollapsible(false);

        root.setCenter(mapPane);
        root.setBottom(titledControlsPane);
        //TODO H11.4
        BorderPane topTables=new BorderPane();
        tableOfVehicles=new TableView<Vehicle>();
        tableOfOrders=new TableView<ConfirmedOrder>();
        topTables.setLeft(tableOfVehicles);
        topTables.setRight(tableOfOrders);
        root.setTop(topTables);
        tableOfVehicles.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableOfOrders.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //tableOfVehicles.setFixedCellSize(25);
        //tableOfOrders.setFixedCellSize(25);
        //tableOfOrders.setPrefSize();
        refreshTable();
        //topTables.setSpacing(20);

        // add columns to tableOfVehicles
        TableColumn<Vehicle,String> idColumn=new TableColumn<>("Vehicle ID"),positionColumn=new TableColumn<>("Position"),
            ordersColumn=new TableColumn<>("Loaded orders");
        idColumn.setMinWidth(40);
        positionColumn.setMinWidth(60);
        ordersColumn.setMinWidth(120);
        idColumn.setCellValueFactory(vehicle->new SimpleStringProperty(vehicle.getValue().getId()+""));
        positionColumn.setCellValueFactory(vehicle-> new SimpleStringProperty(vehicle.getValue().getOccupied().getComponent().getName()));
        /*ordersColumn.setCellValueFactory(vehicle-> {
            String s="";
            for(ConfirmedOrder order:vehicle.getValue().getOrders()) s+=order.toString();
            return new SimpleStringProperty(s);
            //return new SimpleStringProperty(vehicle.getValue().getOrders().stream().reduce("",(a,b)->a+b.toString(),(a,b)->a+b));
        });*/
        ordersColumn.setCellValueFactory(vehicle->{
            String s="";
            for(ConfirmedOrder order:vehicle.getValue().getOrders()) if(s.isEmpty()) s+=order.getOrderID(); else
                s+=", "+order.getOrderID();
            return new SimpleStringProperty(s);
        });
        tableOfVehicles.getColumns().addAll(idColumn,positionColumn,ordersColumn);

        // add columns to tableOfOrders
        TableColumn<ConfirmedOrder,String> orderId=new TableColumn<>("Order ID"), location=new TableColumn<>("Location"),
            foodList=new TableColumn<>("Ordered food"), restaurant=new TableColumn<>("Restaurant");
        orderId.setMinWidth(30);
        location.setMinWidth(50);
        foodList.setMinWidth(100);
        restaurant.setMinWidth(50);

        orderId.setCellValueFactory(order->new SimpleStringProperty(order.getValue().getOrderID()+""));
        location.setCellValueFactory(order->new SimpleStringProperty(vehicleManager.getRegion().getNode(order.getValue().getLocation()).getName()));
        foodList.setCellValueFactory(order->{
            String s="";
            for(String str:order.getValue().getFoodList()) if(s.isEmpty()) s+=str; else s+="\n"+str;
            return new SimpleStringProperty(s);
        });
        restaurant.setCellValueFactory(order->new SimpleStringProperty(order.getValue().getRestaurant().getComponent().getName()));
        tableOfOrders.getColumns().addAll(orderId,location,foodList,restaurant);
        //HBox.setHgrow(topTables, Priority.ALWAYS);
        tableOfVehicles.setPrefWidth(root.getWidth()*0.48);
        tableOfOrders.setPrefWidth(root.getWidth()*0.48);

        //System.out.println(((VehicleManager.OccupiedRestaurant)vehicleManager.getOccupiedRestaurants().toArray()[0]).getComponent()
         //   .getAvailableFood().toString());
        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                tableOfVehicles.setPrefWidth(root.getWidth()*0.48);
                tableOfOrders.setPrefWidth(root.getWidth()*0.48);
            }
        });



        //stop the simulation when closing the window
        controller.getStage().setOnCloseRequest(e -> {
            simulation.endSimulation();
            closed = true;
        });
    }

    /*public void refreshTable(){
        TableColumn<Vehicle,String> idColumn=new TableColumn<>("Vehicle ID"),positionColumn=new TableColumn<>("Position"),
            ordersColumn=new TableColumn<>("Loaded orders");
        idColumn.setMinWidth(70);
        positionColumn.setMinWidth(70);
        ordersColumn.setMinWidth(100);
        idColumn.setCellValueFactory(vehicle->new SimpleStringProperty(vehicle.getValue().getId()+""));
        positionColumn.setCellValueFactory(vehicle->new SimpleStringProperty(vehicle.getValue().getOccupied().toString()));
        ordersColumn.setCellValueFactory(vehicle-> new SimpleStringProperty(vehicle.getValue().getOrders().stream().reduce("",(a,b)->a+b.toString(),(a,b)->a+b)));
        tableOfVehicles.getColumns().clear();
        tableOfVehicles.getColumns().addAll(idColumn,positionColumn,ordersColumn);
    }*/
    public void refreshTable(){
        tableOfVehicles.getItems().clear();
        tableOfVehicles.setItems(FXCollections.observableArrayList(vehicleManager.getVehicles()));

        tableOfVehicles.refresh();

        tableOfOrders.getItems().clear();
        for(Vehicle v:vehicleManager.getVehicles()) tableOfOrders.getItems().addAll(v.getOrders());
        tableOfOrders.refresh();
        tableOfVehicles.setMaxHeight(Math.min(root.getHeight()*0.25,
            Math.min(vehicleManager.getVehicles().size()*25+26,tableOfOrders.getHeight()+26)));
        tableOfOrders.setMaxHeight(tableOfVehicles.getMaxHeight());
    }
    @Override
    public void onTick(List<Event> events, long tick) {
        //Execute GUI updates on the javafx application thread
        Platform.runLater(() -> {
            if(!messagedMapPane) {
                mapPane.update();
                messagedMapPane=true;
            }
            refreshTable();
            events.stream()
                .filter(SpawnEvent.class::isInstance)
                .map(SpawnEvent.class::cast)
                .forEach(spawnEvent -> mapPane.addVehicle(spawnEvent.getVehicle()));

            events.stream()
                .filter(ArrivedAtNodeEvent.class::isInstance)
                .map(ArrivedAtNodeEvent.class::cast)
                .forEach(arrivedAtNodeEvent -> mapPane.redrawVehicle(arrivedAtNodeEvent.getVehicle()));

            events.stream()
                .filter(ArrivedAtEdgeEvent.class::isInstance)
                .map(ArrivedAtEdgeEvent.class::cast)
                .forEach(arrivedAtEdgeEvent -> mapPane.redrawVehicle(arrivedAtEdgeEvent.getVehicle()));

            controlsPane.updateTickLabel(tick);
        });

    }

    @Override
    public SimulationSceneController getController() {
        return controller;
    }

    public boolean isClosed() {
        return closed;
    }
}
