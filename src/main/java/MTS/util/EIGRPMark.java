package MTS.util;

import MTS.entity.Flow;

import java.util.Arrays;

public class EIGRPMark {
    public static double calculateMark(Flow flow){
        double[] load = new double[flow.getNodes().size()];
        for (int i = 0; i < load.length; i++) {
            load[i] = ((double) flow.getNodes().get(i).getCurrentWorkload())/flow.getNodes().get(i).getMaxWorkload();
        }
        double flowWorkload = Arrays.stream(load).sum();
        double bandwidth = 20.0/flow.getNodes().size();
        double delay = ((double) (flow.getNodes().size()*2))/10;
        return bandwidth + (bandwidth/(0.1 + flowWorkload)) + delay;
    }
}
