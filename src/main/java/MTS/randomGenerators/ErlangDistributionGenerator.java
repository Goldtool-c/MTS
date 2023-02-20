package MTS.randomGenerators;

import java.util.Random;

public class ErlangDistributionGenerator implements RandomGenerator{
    private double rate;// это типа лямда, хз почему rate, как-то раз скопировала с какого-то сайта и оно осталось

    public ErlangDistributionGenerator(double rate) {
        this.rate = rate;
    }
    @Override
    public double generate(double start, double end) {
        double value;
        do {
            value = -(Math.log(Math.random())+Math.log(Math.random())+Math.log(Math.random()))/rate ;
        } while (!(value >= start) || !(value <= end));
        return value;
    }

    @Override
    public double[] generateArray(double start, double end, int n) {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) a[i] = generate(start,end);
        return a;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
