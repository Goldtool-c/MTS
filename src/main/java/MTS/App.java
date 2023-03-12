package MTS;

import MTS.Thread.PacketSender;
import MTS.Thread.PackageCourier;
import MTS.Thread.WorkLoadController;
import MTS.entity.Flow;
import MTS.entity.Node;
import MTS.randomGenerators.UniformDistributionGenerator;
import MTS.service.CalculateDenyProbabilityService;
import MTS.util.NodeDrawer;
import MTS.util.NodesBuilder;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class App extends Application {
    public static Node[] nodeSet;
    public static Flow[] flows = new Flow[2];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, ParseException, InterruptedException {
        initApp();
        UniformDistributionGenerator generator = new UniformDistributionGenerator();
        Group root = new Group();
        Scene s = new Scene(root, 800, 600);
        final Canvas canvas = new Canvas(500, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        NodeDrawer.paintNodes(root, gc, new Color(0.0666, 0.94, 0.31, 1), nodeSet);
        root.getChildren().add(canvas);
        TextField daysField = new TextField();
        Label label = new Label("Enter period (days)");
        label.setLabelFor(daysField);
        label.setLayoutX(520);
        label.setLayoutY(30);
        daysField.setPromptText("Enter period");
        daysField.setLayoutX(520);
        daysField.setLayoutY(50);
        Label output = new Label();
        output.setLayoutX(520);
        output.setLayoutY(100);
        Button button = new Button();
        button.setOnAction(actionEvent -> {
                    StringBuilder sb = new StringBuilder();
                    for (Flow flow : flows) {
                        sb.append("Probability of denial of\n").append(flow.toString()).append(" is ");
                        sb.append(CalculateDenyProbabilityService.calculate(flow, Double.parseDouble(daysField.getText())));
                        sb.append('\n');
                    }
                    output.setText(sb.toString());
                    //todo выпилить рандом
                    PackageCourier courier = new PackageCourier(flows[0], 6);
                    Thread courierThread = new Thread(courier);
                    courierThread.start();
                }
        );
        button.setText("Calculate");
        button.setLayoutX(520);
        button.setLayoutY(75);
        Button packageButton = new Button();// кнопка для запуска рандомных пакетов
        packageButton.setOnAction(actionEvent -> {
                    PacketSender newpackage = new PacketSender(flows[1]);
                    Thread packegeThread = new Thread(newpackage);
                    packegeThread.start();
                }
        );
        packageButton.setText("Start");
        packageButton.setLayoutX(520);
        packageButton.setLayoutY(170);
        root.getChildren().addAll(daysField,button, packageButton, output, label);
        stage.setScene(s);
        stage.show();
        WorkLoadController controller = new WorkLoadController(gc, root, Thread.currentThread());
        Thread controllerThread = new Thread(controller, "con");
        controllerThread.start();
    }

    public static void initApp() throws IOException, ParseException {
        Node[] nodes = NodesBuilder.build("resources/nodes.txt");
        nodeSet = nodes;
        Flow flow = new Flow();
        flow.getNodes().add(nodes[0]);
        flow.getNodes().add(nodes[1]);
        flow.getNodes().add(nodes[2]);
        flow.getNodes().add(nodes[3]);
        flow.getNodes().add(nodes[4]);
        flows[0] = flow;
        flow = new Flow();
        flow.getNodes().add(nodes[0]);
        flow.getNodes().add(nodes[5]);
        flow.getNodes().add(nodes[6]);
        flow.getNodes().add(nodes[11]);
        flow.getNodes().add(nodes[8]);
        flow.getNodes().add(nodes[4]);
        flows[1] = flow;
    }
}
