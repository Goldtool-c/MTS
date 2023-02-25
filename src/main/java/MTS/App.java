package MTS;


import MTS.randomGenerators.*;
import MTS.util.HistBuilder;
import MTS.util.UniformityTest;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        UniformityTest.test(new UniformDistributionGenerator());
        System.out.println("__________________________________________________");
        /*UniformityTest.test(new NormalGenerator(1, 1));
        System.out.println("__________________________________________________");
        UniformityTest.test(new PoissonDistributionGenerator(1));
        System.out.println("__________________________________________________");
        UniformityTest.test(new ParetoGenerator(1, 0));
        System.out.println("__________________________________________________");
        UniformityTest.test(new LogNormalGenerator(1, 1));
        System.out.println("__________________________________________________");
        UniformityTest.test(new ErlangDistributionGenerator(1));
        System.out.println("__________________________________________________");
        UniformityTest.test(new ExponentialDistributionGenerator(1));
        System.out.println("__________________________________________________");
        UniformityTest.test(new WeibullGenerator(1, 1, 0));
        System.out.println("__________________________________________________");*/
    }
}
