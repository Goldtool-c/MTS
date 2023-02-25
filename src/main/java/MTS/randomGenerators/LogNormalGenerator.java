package MTS.randomGenerators;


import umontreal.ssj.randvar.LognormalGen;
import umontreal.ssj.rng.MRG32k3a;
import umontreal.ssj.rng.RandomStream;

public class LogNormalGenerator implements RandomGenerator{
    private final RandomStream stream = new MRG32k3a();
    private final double sigma;
    private final double mean;
    private final LognormalGen generator;

    public LogNormalGenerator(double sigma, double mean) {
        this.sigma = sigma;
        this.mean = mean;
        generator =new LognormalGen(stream, mean, sigma);
    }

    @Override
    public double generate(double start, double end) {
        double value;
        do {
            value = generator.nextDouble();
        } while (!(value >= start) || !(value <= end));
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
}
