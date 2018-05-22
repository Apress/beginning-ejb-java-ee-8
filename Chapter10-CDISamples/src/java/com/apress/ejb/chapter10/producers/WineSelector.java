package com.apress.ejb.chapter10.producers;

import java.util.Random;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;

class WineSelector {
   @Produces
   @RandomSelector
   public Wine getWine(@New WhiteWine ww,
                       @New RedWine rw,
                       @New RoseWine rsw,
                       @New SparklingWine sw) {
       final int wineNumber = new Random().nextInt(4);
       System.out.println(wineNumber);
               
       if (wineNumber == 0) {
           return ww;
       }
       else if (wineNumber == 1) {
           return rw;
       }
       if (wineNumber == 2) {
           return rsw;
       }
       else if (wineNumber == 3) {
           return sw;
       }       
       else{
           return null;
       }
   } 
}

