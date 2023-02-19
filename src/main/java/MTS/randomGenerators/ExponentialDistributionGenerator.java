package MTS.randomGenerators;

import java.util.Random;

public class ExponentialDistributionGenerator implements RandomGenerator{

    private double rate;// это типа лямда, хз почему rate, как-то раз скопировала с какого-то сайта и оно осталось

    public ExponentialDistributionGenerator(double rate) {
        this.rate = rate;
    }

    @Override
    public double generate(double start, double end) {
        Random random = new Random();
        double a;
        do {
        a= Math.log(1-random.nextDouble())/(-rate);
        } while (!(a >= start) || !(a <= end));
        return a;
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
