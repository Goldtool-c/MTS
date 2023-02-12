package MTS;


import MTS.randomGenerators.NormalGenerator;
import MTS.randomGenerators.RandomGenerator;
import MTS.util.HistBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        /*RandomGenerator normalGenerator = new NormalGenerator(0, 20);
        double[] normal = normalGenerator.generateArray(-50, 50, 50000);
        HistBuilder.build(normal, "normal distribution");*/ // образeц
    }
}
