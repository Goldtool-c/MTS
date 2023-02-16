package MTS;


import MTS.randomGenerators.ParetoGenerator;
import MTS.randomGenerators.RandomGenerator;
import MTS.util.HistBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        RandomGenerator normalGenerator = new ParetoGenerator(2, 7);
        double[] normal = normalGenerator.generateArray(-50, 50, 50);
        HistBuilder.build(normal, "Pareto distribution");
    }
}
