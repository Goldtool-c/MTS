package MTS.Thread;

import MTS.entity.Flow;
import MTS.randomGenerators.*;
import MTS.service.FindBestRoute;

public class PacketSender implements Runnable{

    private Flow flow;

    private boolean isActive ;

    public void disable(boolean status){ // включить выключить поток
        this.isActive=status;
    }

    public PacketSender(Flow flow){
        this.flow= flow;
    }

    @Override
    public void run() {
        while (isActive){
        //PoissonDistributionGenerator packageGenerator = new PoissonDistributionGenerator(7);
            UniformDistributionGenerator packageGenerator = new UniformDistributionGenerator();// равномерное
            LogNormalGenerator logNormalGenerator = new LogNormalGenerator(0.5,0.5);//лог нормальное
            ExponentialDistributionGenerator exponentialDistributionGenerator = new ExponentialDistributionGenerator(1.5);
            //int numberOfPackages = (int)packageGenerator.generate(1,100); // генерим количество пакетов
            //int numberOfPackages = (int)logNormalGenerator.generate(1,100);
           int numberOfPackages = (int)exponentialDistributionGenerator.generate(1,100);
            PackageCourier addPackeges = new PackageCourier(FindBestRoute.find(),numberOfPackages);
            Thread courier = new Thread(addPackeges);
            courier.start();
            synchronized (this){
                try {
                    ErlangDistributionGenerator timeGenerator = new ErlangDistributionGenerator(1);
                    int packageArrivalTimes = (int)(timeGenerator.generate(0.1,1)*5000);
                    // время, через которое поток возобновит работу
                    wait(packageArrivalTimes);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
