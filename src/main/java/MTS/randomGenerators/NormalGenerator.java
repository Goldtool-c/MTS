package MTS.randomGenerators;

import java.util.Random;

public class NormalGenerator implements RandomGenerator{
    private double mean;
    private double std;

    public NormalGenerator(double mean, double std) {
        this.mean = mean;
        this.std = std;
    }

    @Override
    public double generate(double start, double end) {
        Random random = new Random();
        double value;
        do {
            value = random.nextGaussian() * std + mean;
        } while (!(value >= start) || !(value <= end));//ох уж эти подсказки в ide
        return value;
    }

    @Override
    public double[] generateArray(double start, double end, int n) {
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = generate(start, end);
        }
        return res;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStd() {
        return std;
    }

    public void setStd(double std) {
        this.std = std;
    }
}
