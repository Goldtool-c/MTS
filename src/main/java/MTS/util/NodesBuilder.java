package MTS.util;

import MTS.entity.Node;
import MTS.randomGenerators.RandomGenerator;
import MTS.randomGenerators.UniformDistributionGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class NodesBuilder {
    public static Node[] build(String path) throws IOException, ParseException {

        Object o = new JSONParser().parse(new FileReader(path));
        JSONArray nodes1 = (JSONArray) o;
        Node[] nodes = new Node[nodes1.size()];
        for (int i = 0; i < nodes1.size(); i++) {
            JSONObject nodeJSON = (JSONObject) nodes1.get(i);
            Node node = new Node();
            node.setX((double) nodeJSON.get("x"));
            node.setY((double) nodeJSON.get("y"));
            node.setOperatingPeriod((double) nodeJSON.get("operatingPeriod"));
            node.setId(((Long) nodeJSON.get("id")).intValue());
            nodes[i] = node;
        }
        for (int i = 0; i < nodes1.size(); i++) {
            JSONObject nodeJSON = (JSONObject) nodes1.get(i);
            JSONArray idArray = (JSONArray) nodeJSON.get("linkedTo");
            for (int j = 0; j < idArray.size(); j++) {
                int id = ((Long) idArray.get(j)).intValue() - 1;
                nodes[i].connectNode(nodes[id]);
            }
        }
        return nodes;
    }
}
