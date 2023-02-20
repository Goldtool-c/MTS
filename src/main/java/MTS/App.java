package MTS;


import MTS.randomGenerators.*;
import MTS.util.HistBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        /*RandomGenerator normalGenerator = new NormalGenerator(2, 2);
        double[] normal = normalGenerator.generateArray(-50, 50, 500);
        HistBuilder.build(normal, "normal distribution");*/
        /*RandomGenerator weibullGenerator = new WeibullGenerator(1, 1, 20);
        double[] weibull = weibullGenerator.generateArray(-50, 50, 200);
        HistBuilder.build(weibull, "weibull distribution");*/
        /*RandomGenerator paretoGenerator = new ParetoGenerator(2, 7);
        double[] pareto = paretoGenerator.generateArray(-50, 50, 200);
        HistBuilder.build(pareto, "Pareto distribution");*/
        RandomGenerator logNormalGenerator = new LogNormalGenerator(1, 1);
        double[] logNormal = logNormalGenerator.generateArray(-5000, 5000, 200);
        HistBuilder.build(logNormal, "logNormal distribution");
    }
}
