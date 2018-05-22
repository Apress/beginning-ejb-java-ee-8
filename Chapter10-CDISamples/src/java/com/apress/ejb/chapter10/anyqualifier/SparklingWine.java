package com.apress.ejb.chapter10.anyqualifier;

@Sparkling
class SparklingWine implements Wine {
   public String getColor() {
       return "Sparkling";
   }
}
