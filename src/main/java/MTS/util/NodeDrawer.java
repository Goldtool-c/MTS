package MTS.util;

import MTS.entity.Node;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class NodeDrawer {
    public static Circle[] paintNodes(Group root, GraphicsContext gc, Node... nodes){
        Circle[] circles = new Circle[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            Circle circle = new Circle(nodes[i].getX(), nodes[i].getY(), 12, Color.PURPLE);
            gc.strokeText(nodes[i].getId()+"", nodes[i].getX()-3, nodes[i].getY()-15);
            circles[i] = circle;
        }
        gc.setLineWidth(0.5);
        for (int i = 0; i < nodes.length; i++) {
            List<Node> temp = nodes[i].getLinkedTo();
            for (int j = 0; j < temp.size(); j++) {
                gc.moveTo(nodes[i].getX(), nodes[i].getY());
                gc.lineTo(temp.get(j).getX(), temp.get(j).getY());
                gc.stroke();
            }
        }
        return circles;
    }
}
