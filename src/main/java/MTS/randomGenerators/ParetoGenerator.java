package MTS.randomGenerators;

import umontreal.ssj.randvar.ParetoGen;
import umontreal.ssj.rng.MRG32k3a;
import umontreal.ssj.rng.RandomStream;

public class ParetoGenerator implements RandomGenerator{
    private final double shape;//alpha
    private final double min;//k
    private final ParetoGen generator;


    public ParetoGenerator(double shape, double min) {
        this.shape = shape;
        this.min = min;
        generator = new ParetoGen(new MRG32k3a(),shape, min);
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

    public double getShape() {
        return shape;
    }

    public double getMin() {
        return min;
    }
}
