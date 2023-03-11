package MTS.Thread;

import MTS.entity.Flow;
import MTS.entity.Node;

public class PackageCourier implements Runnable{
    private Flow flow;
    private int packages;
    public PackageCourier(Flow flow, int packages) {
        this.flow = flow;
        this.packages = packages;
    }
    @Override
    public void run() {
        flow.getNodes().get(0).addWorkload(packages);
        for (int i = 0; i < flow.getNodes().size()-1; i++) {
            synchronized (this){
                try {
                    wait(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Node node = flow.getNodes().get(i);
            node.setCurrentWorkload(node.getCurrentWorkload()-packages);
            synchronized (this){
                try {
                    wait(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            packages =
                    flow.getNodes().get(i+1).addWorkload(packages);
        }
        System.out.println("packages received: "+packages);
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
