package MTS.util;

public class IndependenceTesting {
    public static double coefficientOfCorrelation(double[] narr, int s) {
        if (narr.length > s) {
            double Z = 0;
            for (int i = 0; i < narr.length - s; i++) Z += narr[i] * narr[i + s];
            return ((12 * Z) / (narr.length - s)) - 3;
        } else return 0;
    }

}
