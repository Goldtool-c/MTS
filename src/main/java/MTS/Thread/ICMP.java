package MTS.Thread;

import MTS.App;
import MTS.entity.Flow;
import MTS.randomGenerators.*;
import MTS.service.FindBestRoute;

import java.util.ArrayList;
import java.util.Date;

public class ICMP implements Runnable {
    private Flow flow;
    private Flow backwardsFlow;
    public void establishConnection() throws InterruptedException {
        int bestRouteIndex = 0;
        Flow flow = FindBestRoute.find();
        for (int i = 0; i < App.flows.length; i++) {
            if (flow.equals(App.flows[i])) {
                bestRouteIndex = i;
                break;
            }
        }
        Flow backwardsFlow = App.flows[2 + bestRouteIndex];
        this.flow = flow;
        this.backwardsFlow = backwardsFlow;
        PackageCourier firstHandshake = new PackageCourier(flow, 1);
        Thread courier = new Thread(firstHandshake);
        System.out.println("first handshake");
        courier.start();
        courier.join();
        PackageCourier secondHandshake = new PackageCourier(backwardsFlow, 1);
        courier = new Thread(secondHandshake);
        System.out.println("second handshake");
        courier.start();
        courier.join();
        PackageCourier thirdHandshake = new PackageCourier(flow, 1);
        courier = new Thread(thirdHandshake);
        System.out.println("third handshake");
        courier.start();
        courier.join();
        System.out.println("connection established");
    }

    @Override
    public void run() {
        try {
            long time = new Date().getTime();
            establishConnection();
            int packages = 0;
            RandomGenerator generator = new PoissonDistributionGenerator(1);
            ErlangDistributionGenerator timeGenerator = new ErlangDistributionGenerator(1);
            ArrayList<Thread> couriers = new ArrayList<>();
            while (packages<=7){
                int add = (int) generator.generate(0, 7);
                packages = packages + add;
                if (packages>=7){
                    add = add - (packages - 7);
                }
                PackageCourier courier = new PackageCourier(flow, add);
                Thread hi = new Thread(courier);
                hi.start();
                couriers.add(hi);
                synchronized (this){
                    wait((int)(timeGenerator.generate(0.1,1)*2000));
                }
            }
            for (Thread courier : couriers) {
                courier.join();
            }
            System.out.println("Total time for ICMP with "+generator.getClass().getSimpleName()+": "+
                    ((double)(new Date().getTime() - time))/1000 + " seconds");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
