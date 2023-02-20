package MTS.randomGenerators;

public interface RandomGenerator {
    double generate(double start, double end);
    double[] generateArray(double start, double end, int n);
}
