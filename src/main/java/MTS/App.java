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
        RandomGenerator normalGenerator = new ErlangDistributionGenerator(0.5);
        double[] uniformDistributionGenerator = normalGenerator.generateArray(-50, 50, 500);
        HistBuilder.build(uniformDistributionGenerator, "Erlang Distribution");
        UniformDistributionGenerator generator = new UniformDistributionGenerator();
        double[] array = generator.generateArray(0,1,100);
        System.out.println("R = "+IndependenceTesting.coefficientOfCorrelation(array,30));
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
