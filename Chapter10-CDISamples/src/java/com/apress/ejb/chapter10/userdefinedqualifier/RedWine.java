package com.apress.ejb.chapter10.userdefinedqualifier;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Red
class RedWine implements Wine {
   public String getColor() {
       return "Red";
   }
}
