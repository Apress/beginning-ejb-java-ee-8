package com.apress.ejb.chapter10.alternatives;

import javax.enterprise.inject.Alternative;

@Alternative
class WhiteWine implements Wine {
   public String getColor() {
       return "White";
   }
}
