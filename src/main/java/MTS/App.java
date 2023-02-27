package MTS;

import MTS.entity.Flow;
import MTS.entity.Node;
import MTS.service.CalculateDenyProbabilityService;
import MTS.util.FlowDrawer;
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
import javafx.scene.shape.Circle;
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
    public void start(Stage stage) throws IOException, ParseException {
        initApp();
        Group root = new Group();
        Scene s = new Scene(root, 800, 600);
        final Canvas canvas = new Canvas(500, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Circle[] circles = NodeDrawer.paintNodes(root, gc, nodeSet);
        root.getChildren().add(canvas);
        root.getChildren().addAll(FlowDrawer.paintFlows(root, gc, flows[0]));
        root.getChildren().addAll(FlowDrawer.paintFlows(root, gc, flows[1]));
        root.getChildren().addAll(circles);
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
            for (int i = 0; i < flows.length; i++) {
                sb.append("Probability of denial of\n").append(flows[i].toString()).append(" is ");
                sb.append(CalculateDenyProbabilityService.calculate(flows[i], Double.parseDouble(daysField.getText())));
                sb.append('\n');
            }
            output.setText(sb.toString());
        }
        );
        button.setText("Calculate");
        button.setLayoutX(520);
        button.setLayoutY(75);
        root.getChildren().addAll(daysField, button, output, label);
        stage.setScene(s);
        stage.show();
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
        flow.getNodes().add(nodes[4]);
        flows[1] = flow;
    }
}
