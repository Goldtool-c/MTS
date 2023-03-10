package MTS.util;

import MTS.entity.Flow;
import MTS.entity.Node;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Iterator;

public class FlowDrawer {
    public static Line[] paintFlows(Group root, GraphicsContext gc, Flow flow) {
        Iterator<Node> it = flow.getNodes().iterator();
        Node node = it.next();
        Line[] res = new Line[flow.getNodes().size()-1];
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.beginPath();
        for (int j = 0; j < flow.getNodes().size(); j++) {
            Line line = new Line();
            line.setStroke(Color.RED);
            line.setStrokeWidth(2);
            line.setStartX(node.getX());
            line.setStartY(node.getY());
            if (it.hasNext()) {
                node = it.next();
                line.setEndX(node.getX());
                line.setEndY(node.getY());
                res[j] = line;
                gc.moveTo(line.getStartX(), line.getStartY());
                gc.lineTo(line.getEndX(), line.getEndY());
                gc.stroke();
            }
        }
        gc.setStroke(Color.BLACK);
        //gc.setLineWidth(1);
        return res;
    }
}
