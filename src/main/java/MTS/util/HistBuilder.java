package MTS.util;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;

public class HistBuilder {
    public static void build(double[] values, String generator) {
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
        for (int i = min; i <= max; i++) {
            ds.getData().add(new XYChart.Data(""+i, findInRange(values, i)));
        }
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(ds);// помещаем ее в окно
        stage.setScene(scene);
        stage.show();// показываем окно
    }

    private static int findInRange(double[] values, int range) {
        int counter = 0;
        for (int i = 0; i < values.length; i++) {
            if(values[i]>=range-1&&values[i]<range)
            {
                counter++;
            }
        }
        return counter;
    }
}
