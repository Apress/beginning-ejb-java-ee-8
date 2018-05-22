package com.apress.ejb.chapter10.alternatives;

import javax.enterprise.inject.Alternative;

@Alternative
class RedWine implements Wine {
   public String getColor() {
       return "Red";
   }
}
