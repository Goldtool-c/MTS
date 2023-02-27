package MTS.service;

import MTS.entity.Flow;

public class CalculateDenyProbabilityService {
    public static double calculate(Flow flow, double days){
        double[] nodeProb = new double[flow.getNodes().size()];
        double result = 1;
        for (int i = 1; i < nodeProb.length-1; i++) {
            double lambda = 1.0/(flow.getNodes().get(i).getOperatingPeriod()*24*3600*365.25);
            nodeProb[i] = Math.exp((-lambda)*(days*24*3600));
            result = result * nodeProb[i];
        }
        return result;
    }
}
