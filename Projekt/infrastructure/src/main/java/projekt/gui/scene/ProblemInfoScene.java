package projekt.gui.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import projekt.delivery.archetype.ProblemArchetype;
import projekt.delivery.routing.Region;
import projekt.gui.controller.ProblemInfoSceneController;
import projekt.gui.controller.RaterSceneController;
import projekt.gui.pane.MapPane;

import java.util.ArrayList;

public class ProblemInfoScene extends MenuScene<ProblemInfoSceneController>{
    MapPane map;
    ProblemArchetype problem;
    Region region;
    public ProblemInfoScene(){super(new ProblemInfoSceneController(),"Advanced info");
        problem=MainMenuScene.problemSelection;}
    public ProblemInfoScene(ProblemInfoSceneController controller,String title,String... styleSheets){
        super(controller,title,styleSheets);
        problem=MainMenuScene.problemSelection;
    }
    public ProblemInfoSceneController getController() {
        return controller;
    }
    public void initComponents(){
        initReturnButton();
        region=problem.vehicleManager().getRegion();
        map=new MapPane(region.getNodes(),region.getEdges(),problem.vehicleManager().getVehicles());
        map.RUNNING=false;
        map.prevWidth=getWidth();
        map.prevHeight=getHeight();
        root.setCenter(map);
        map.update2();
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
}
