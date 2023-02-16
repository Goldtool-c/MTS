package MTS.randomGenerators;

import umontreal.ssj.randvar.UniformGen;
import umontreal.ssj.rng.MRG32k3a;
import umontreal.ssj.rng.RandomStream;

public class WeibullGenerator implements RandomGenerator {
    //todo переделать с равномерным генератором
    private final double shape;
    private final double shift;

    private final double delta;
    private final RandomStream stream = new MRG32k3a();

    public WeibullGenerator(double shape, double shift, double delta) {
        this.shape = shape;
        this.shift = shift;
        this.delta = delta;
    }

    @Override
    public double generate(double start, double end) {
        double value;
        do {
            double uni = UniformGen.nextDouble(stream, 0, 1);
            value = (shape * Math.pow(Math.log(-uni), (1.0/shift)))+delta;
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

    public double getShift() {
        return shift;
    }

    public double getDelta() {
        return delta;
    }
}
