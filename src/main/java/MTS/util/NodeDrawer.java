package MTS.util;

import MTS.App;
import MTS.entity.Node;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class NodeDrawer {
    public static void paintNodes(Group root, GraphicsContext gc, Color color, Node... nodes){
        Circle[] circles = new Circle[nodes.length];
        double workload;
        //gc.setFill(new Color(0.0666, 0.94, 0.31,  1));
        gc.setLineWidth(0.5);
        gc.setStroke(Color.BLACK);
        for (int i = 0; i < nodes.length; i++) {
            List<Node> temp = nodes[i].getLinkedTo();
            for (int j = 0; j < temp.size(); j++) {
                gc.moveTo(nodes[i].getX(), nodes[i].getY());
                gc.lineTo(temp.get(j).getX(), temp.get(j).getY());
                gc.stroke();
            }
        }
        for (int i = 0; i < App.flows.length; i++) {
            FlowDrawer.paintFlows(root, gc, App.flows[i]);
        }
        gc.setLineWidth(2);
        for (int i = 0; i < nodes.length; i++) {
            gc.beginPath();
            workload = ((double)nodes[i].getCurrentWorkload())/((double)nodes[i].getMaxWorkload());
            gc.setFill(new Color(workload, (1-workload), 0.31 * (1-workload),  1));
            gc.strokeText(nodes[i].getId()+"", nodes[i].getX()-3, nodes[i].getY()-15);
            gc.strokeOval(nodes[i].getX()-12, nodes[i].getY()-12, 24, 24);
            gc.fillOval(nodes[i].getX()-12, nodes[i].getY()-12, 24, 24);
        }
    }
}
