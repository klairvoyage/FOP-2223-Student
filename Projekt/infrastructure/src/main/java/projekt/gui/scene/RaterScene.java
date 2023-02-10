package projekt.gui.scene;

import javafx.scene.chart.BarChart;
import projekt.delivery.archetype.ProblemArchetype;
import projekt.delivery.rating.RatingCriteria;
import projekt.gui.controller.RaterSceneController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* own imports*/
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
/* end own imports*/

public class RaterScene extends MenuScene<RaterSceneController> {
    private Map<RatingCriteria, Double> result;

    public RaterScene() {
        super(new RaterSceneController(), "Simulation Score", "projekt/gui/raterStyle.css");
    }

    public void init(List<ProblemArchetype> problems, Map<RatingCriteria, Double> result) {
        this.result = result;
        super.init(problems);
    }

    @Override
    public void initComponents() {
        CategoryAxis xAxis=new CategoryAxis();
        NumberAxis yAxis=new NumberAxis();
        BarChart<String,Number> barChart=new BarChart<>(xAxis,yAxis);
        xAxis.setLabel("Rating criterion");
        yAxis.setLabel("Performance");
        XYChart.Series<String,Number> series=new XYChart.Series<String,Number>();
        for(RatingCriteria crit:result.keySet()) series.getData().add(new XYChart.Data<>(crit.name(),result.get(crit)));
        barChart.getData().add(series);
        series.setName("Score from 0 to 1");
        root.setCenter(barChart);
    }

    @Override
    public void initReturnButton() {
        returnButton.setOnAction(e -> {
            MainMenuScene scene = (MainMenuScene) SceneSwitcher.loadScene(SceneSwitcher.SceneType.MAIN_MENU, getController().getStage());
            scene.init(new ArrayList<>(problems));
        });
    }

    @Override
    public RaterSceneController getController() {
        return controller;
    }
}
