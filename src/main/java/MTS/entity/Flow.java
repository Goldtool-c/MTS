package MTS.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class Flow {
    private List<Node> nodes = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nodes.get(0).getId());
        for (int i = 1; i < nodes.size(); i++) {
            sb.append("->");
            sb.append(nodes.get(i).getId());
        }
        return "Flow{" +sb+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flow flow = (Flow) o;
        boolean flag = true;
        if (nodes.size()!=((Flow) o).nodes.size()) return false;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getId()!=((Flow) o).getNodes().get(i).getId()) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }
}
