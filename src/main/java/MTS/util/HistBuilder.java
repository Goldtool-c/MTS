package MTS.util;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Arrays;

public class HistBuilder {
    public static void build(double[] values, String generator, double precision) {
        Stage stage = new Stage();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        stage.setTitle(generator);
        bc.setTitle(generator);
        xAxis.setLabel("Values");
        yAxis.setLabel("");
        int max = (int) (Arrays.stream(values).max().getAsDouble() + 1);
        int min = (int) (Arrays.stream(values).min().getAsDouble()-1);
        XYChart.Series ds = new XYChart.Series();
        double temp;
        for (double i = min; i <= max; i=i+precision) {
            temp = Math.round(i * 100);
            temp = temp/100;
            ds.getData().add(new XYChart.Data(""+temp, findInRange(values, i, precision)));
        }
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(ds);// помещаем ее в окно
        stage.setScene(scene);
        stage.show();// показываем окно
    }

    private static int findInRange(double[] values, double range, double precision) {
        int counter = 0;
        for (int i = 0; i < values.length; i++) {
            if(values[i]>=range-precision&&values[i]<range)
            {
                counter++;
            }
        }
        return counter;
    }
}
