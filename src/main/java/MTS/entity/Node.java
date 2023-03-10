package MTS.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Node implements Cloneable {
    private int id;
    private double x;
    private double y;
    private List<Node> linkedTo = new LinkedList<>();

    private int currentWorkload;

    private int maxWorkload;

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

    public void connectNode(Node node) {
        List<Node> nodeLinkedTo = node.linkedTo;
        for (int i = 0; i < linkedTo.size(); i++) {
            if (node.getId() == this.id) {
                return;
            }
        }
        for (int i = 0; i < nodeLinkedTo.size(); i++) {
            if (node.getId() == this.id) {
                return;
            }
        }
        linkedTo.add(node);
        nodeLinkedTo.add(this);
    }

    public void addWorkload(int packages) {
        currentWorkload = currentWorkload + packages;
    }

    public int processWorkload() {
        int res;
        if (currentWorkload >= maxWorkload) {
            currentWorkload = 0;
            return maxWorkload;
        } else {
            res = currentWorkload;
            currentWorkload = 0;
            return res;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < linkedTo.size(); i++) {
            sb.append(linkedTo.get(i).getId());
            sb.append(", ");
        }
        return "Node{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", currentWorkload = " + currentWorkload +
                ", linkedTo=" + sb +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id && Double.compare(node.x, x) == 0 && Double.compare(node.y, y) == 0 && currentWorkload == node.currentWorkload && maxWorkload == node.maxWorkload;
    }

    @Override
    public Node clone() {
        Node clone = new Node();
        clone.setId(id);
        clone.setX(x);
        clone.setY(y);
        clone.setMaxWorkload(maxWorkload);
        clone.setCurrentWorkload(currentWorkload);
        return clone;
    }
}
