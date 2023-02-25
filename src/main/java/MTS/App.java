package MTS;


import MTS.randomGenerators.UniformDistributionGenerator;
import MTS.util.IndependenceTesting;
import MTS.util.UniformityTest;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        UniformDistributionGenerator generator = new UniformDistributionGenerator();
        double[] array = generator.generateArray(0,1,100);
        System.out.println("R = "+ IndependenceTesting.coefficientOfCorrelation(array,30) + "| n=100");
        array = generator.generateArray(0,1,1000);
        System.out.println("R = "+ IndependenceTesting.coefficientOfCorrelation(array,30) + "| n=1000");
        UniformityTest.test(new UniformDistributionGenerator());
        System.out.println("__________________________________________________");

    }
}
