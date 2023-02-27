package MTS.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Node {
    private int id;
    private double x;
    private double y;
    private List<Node> linkedTo = new LinkedList<>();

    private double operatingPeriod;

    public Node(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Node(int id, double x, double y, List<Node> linkedTo) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.linkedTo = linkedTo;
        for (Node node : linkedTo) {
            boolean isConnected = false;
            for (int j = 0; j < node.linkedTo.size(); j++) {
                if (this.id == node.linkedTo.get(j).id) {
                    isConnected = true;
                    break;
                }
            }
            if (!isConnected) {
                node.linkedTo.add(this);
            }
        }
    }

    public void connectNode(Node node){
        List<Node> nodeLinkedTo = node.linkedTo;
        for (int i = 0; i < linkedTo.size(); i++) {
            if(node.getId()==this.id){
                return;
            }
        }
        for (int i = 0; i < nodeLinkedTo.size(); i++) {
            if(node.getId()==this.id){
                return;
            }
        }
        linkedTo.add(node);
        nodeLinkedTo.add(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" {");
        for (int i = 0; i < linkedTo.size(); i++) {
            sb.append(linkedTo.get(i).getId());
            sb.append(", ");
        }
        return "Node{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", linkedTo=" + sb +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y);
    }
}
