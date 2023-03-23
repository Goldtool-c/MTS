package MTS.Thread;

import MTS.entity.Flow;
import MTS.entity.Node;
import MTS.randomGenerators.RandomGenerator;
import MTS.randomGenerators.UniformDistributionGenerator;

import java.util.Random;

public class PackageCourier implements Runnable{
    private Flow flow;
    private int packages;
    public PackageCourier(Flow flow, int packages) {
        this.flow = flow;
        this.packages = packages;
    }
    @Override
    public void run() {
        int startPackages = packages;
        /*Random random = new Random();
        for (int i = 0; i < flow.getNodes().size(); i++) {
            double errorProb = flow.getNodes().get(i).getErrorProb();
            if(errorProb*10000000>=random.nextInt(100000)){
                System.out.println("Error occurred: package lost");
                packages = packages - 1;
            }
        }*/
        //if(errorProb*10000000<=new UniformDistributionGenerator().generate(0, 100000))
        flow.getNodes().get(0).addWorkload(packages);
        for (int i = 0; i < flow.getNodes().size()-1; i++) {
            synchronized (this){
                try {
                    wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Node node = flow.getNodes().get(i);
            node.setCurrentWorkload(node.getCurrentWorkload()-packages);
            synchronized (this){
                try {
                    wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            packages =
                    flow.getNodes().get(i+1).addWorkload(packages);
        }
        System.out.println("packages sent: "+startPackages+" | packages received: "+packages);
        synchronized (this){
            try {
                wait(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        flow.getNodes().get(flow.getNodes().size()-1).setCurrentWorkload(0);
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }
}
