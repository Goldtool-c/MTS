package MTS.randomGenerators;

public class PoissonDistributionGenerator implements RandomGenerator {

    private double rate;// это типа лямда, хз почему rate, как-то раз скопировала с какого-то сайта и оно осталось

    public PoissonDistributionGenerator(double rate) {
        this.rate = rate;
    }

    @Override
    public double generate(double start, double end) {//короче, я не уверена ,но вроде так, все со stackoverflow,а там с википедии
        double L = Math.exp(-rate);
        int k = 0;
        do {
            double p = 1.0;
            do {
                k++;
                p *= Math.random();
             } while (p > L);
       } while (!(k-1 >= start) || !(k-1 <= end));
        return k-1;
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
