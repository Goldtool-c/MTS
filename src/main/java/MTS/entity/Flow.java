package MTS.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
