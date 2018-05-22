package com.apress.ejb.chapter10.userdefinedqualifier;

@White
class WhiteWine implements Wine {
   public String getColor() {
       return "White";
   }
}
