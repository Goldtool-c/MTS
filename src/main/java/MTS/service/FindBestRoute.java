package MTS.service;

import MTS.App;
import MTS.entity.Flow;
import MTS.util.EIGRPMark;

public class FindBestRoute {
    public static Flow find(){
        Flow res = App.flows[0];
        double mark = 0;
        System.out.println("==============================================");
        for (int i = 2; i < App.flows.length; i++) {
            if(EIGRPMark.calculateMark(App.flows[i])>mark){
                res = App.flows[i];
                mark = EIGRPMark.calculateMark(App.flows[i]);
            }
            System.out.println("Flow "+(i+1)+ " mark = "+EIGRPMark.calculateMark(App.flows[i]));
        }
        System.out.println(res.toString() + " " + mark);
        System.out.println("==============================================");
        return res;
    }
}
