package MTS.util;

import MTS.randomGenerators.RandomGenerator;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class UniformityTest {
    public static void test(RandomGenerator generator){
        int n1 = 100;
        double[] z = generator.generateArray(0, 1, n1);
        int n2 = 10000;
        double[] z1 = generator.generateArray(0, 1, n2);
        System.out.println("mean of "+ generator.getClass().getSimpleName()+" "+ SetParameters.getMean(z) + "| n =  100");
        System.out.println("std of "+ generator.getClass().getSimpleName()+" "+ SetParameters.getStd(z)  + "| n =  100");
        System.out.println("mean of "+ generator.getClass().getSimpleName()+" "+ SetParameters.getMean(z1)  + "| n =  10000");
        System.out.println("std of "+ generator.getClass().getSimpleName()+" "+ SetParameters.getStd(z1)  + "| n =  10000");
        withN(z);
        withN(z1);
    }
    private static int findInRange(double[] values, double range, double precision) {
        int counter = 0;
        for (double value : values) {
            if (value >= range - precision && value < range) {
                counter++;
            }
        }
        return counter;
    }
    private static void withN(double[] z){
        double[] p = new double[10];
        for (int i = 0; i < 10; i++) {
            p[i]= findInRange(z, (i+1)/10.0, 0.1)/ (double) z.length;
        }
        Stage stage = new Stage();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        stage.setTitle("Uniformity "+z.length);
        bc.setTitle("Uniformity "+z.length);
        xAxis.setLabel("Values");
        yAxis.setLabel("");
        XYChart.Series ds = new XYChart.Series();
        for (int i = 0; i < 10; i++) {
            ds.getData().add(new XYChart.Data(""+(i+1)/10.0, p[i]));
        }
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(ds);
        stage.setScene(scene);
        stage.show();
    }
}
