package MTS.util;

//set = множество
public class SetParameters {
    public static double getMean(double[] z){
        double res = 0;
        for (int i = 0; i < z.length; i++) {
            res += z[i];
        }
        return res/z.length;
    }
    public static double getStd(double[] z){
        double res = 0;
        double mean = getMean(z);
        for (int i = 0; i < z.length; i++) {
            res += (z[i]*z[i]) - (mean*mean);
        }
        return res/z.length;
    }
}
