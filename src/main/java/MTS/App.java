package MTS;
import MTS.randomGenerators.*;
import MTS.util.HistBuilder;
import MTS.util.IndependenceTesting;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        RandomGenerator normalGenerator = new ErlangDistributionGenerator(0.5);
        double[] uniformDistributionGenerator = normalGenerator.generateArray(-50, 50, 500);
        HistBuilder.build(uniformDistributionGenerator, "Erlang Distribution");
        UniformDistributionGenerator generator = new UniformDistributionGenerator();
        double[] array = generator.generateArray(0,1,100);
        System.out.println("R = "+IndependenceTesting.coefficientOfCorrelation(array,30));
    }
}
