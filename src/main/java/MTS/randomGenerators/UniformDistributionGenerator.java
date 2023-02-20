package MTS.randomGenerators;

public class UniformDistributionGenerator implements RandomGenerator {
    @Override
    public double generate(double start, double end) {
        double a = (Math.random() * (end - start)) + start;
        return a;
    }

    @Override
    public double[] generateArray(double start, double end, int n) {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) a[i] = (Math.random() * (end - start)) + start;
        return a;
    }
}
