package com.apress.ejb.chapter12.view.managed;

import com.apress.ejb.chapter07.ejb.ShoppingCartLocal;
import com.apress.ejb.chapter07.entities.Customer;
import javax.ejb.EJB;

public class Login
{
  public Login() {
  }
  String email;
  Customer customer;
  ShoppingCartLocal shoppingCart;

  public void setEmail(String email) {
    this.email = email;
  }
 
  public String getEmail() {
    return email;
  }

  public String processLogin() {
    String navigation = null;
    customer = (Customer)shoppingCart.findCustomer(email);

    if (customer != null) {
      navigation = "winehome";
    }
    else {
      navigation = "register";
    }
    return navigation;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return customer;
  }

  @EJB
  public void setShoppingCart(ShoppingCartLocal shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public ShoppingCartLocal getShoppingCart() {
    return shoppingCart;
  }
}
