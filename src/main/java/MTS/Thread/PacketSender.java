package MTS.Thread;

import MTS.entity.Flow;
import MTS.randomGenerators.ErlangDistributionGenerator;
import MTS.randomGenerators.PoissonDistributionGenerator;

public class PacketSender implements Runnable{

    private Flow flow;

    private boolean isActive = true;

    void disable(){ // включить выключить поток
        this.isActive=false;
    }

    public PacketSender(Flow flow){
        this.flow= flow;
    }

    @Override
    public void run() {
        while (isActive){
        PoissonDistributionGenerator packageGenerator = new PoissonDistributionGenerator(1);
        int numberOfPackages = (int)packageGenerator.generate(1,100); // генерим количество пакетов
        PackageCourier addPackeges = new PackageCourier(flow,numberOfPackages);
            Thread courier = new Thread(addPackeges);
            courier.start();
            synchronized (this){
                try {
                    ErlangDistributionGenerator timeGenerator = new ErlangDistributionGenerator(1);
                    int packageArrivalTimes = (int)(timeGenerator.generate(0.1,1)*10000); // время, через которое поток возобновит работу
                    wait(packageArrivalTimes);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
