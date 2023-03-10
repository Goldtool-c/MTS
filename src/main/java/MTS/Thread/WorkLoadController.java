package MTS.Thread;

import MTS.App;
import MTS.entity.Node;
import MTS.util.NodeDrawer;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WorkLoadController implements Runnable{
    GraphicsContext gc;
    Group root;
    Node[] nodes;
    Thread main;
    public WorkLoadController(GraphicsContext gc, Group root, Thread main) {
        this.gc = gc;
        this.root = root;
        this.main = main;
        nodes = new Node[App.nodeSet.length];
        for (int i = 0; i < App.nodeSet.length; i++) {
            nodes[i] = App.nodeSet[i].clone();
        }
    }

    @Override
    public void run() {
        boolean ifChanged = false;
        while (true) {
            for (int i = 0; i < nodes.length; i++) {
                if (!nodes[i].equals(App.nodeSet[i])) {
                    ifChanged = true;
                    break;
                }
            }
            if(ifChanged){
                for (int i = 0; i < App.nodeSet.length; i++) {
                    nodes[i] = App.nodeSet[i].clone();
                }
                gc.clearRect(0, 0, 500, 600);
                NodeDrawer.paintNodes(root, gc, Color.BLACK, App.nodeSet);
                ifChanged = false;
            }
            if(!main.isAlive()){
                break;
            }
        }
    }
}
