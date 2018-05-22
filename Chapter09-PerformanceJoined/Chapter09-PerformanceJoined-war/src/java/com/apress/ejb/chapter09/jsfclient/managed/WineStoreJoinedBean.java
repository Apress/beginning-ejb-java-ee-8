package com.apress.ejb.chapter09.jsfclient.managed;

import com.apress.ejb.chapter09.ejb.joined.ShoppingCartJoined;
import com.apress.ejb.chapter09.ejb.joined.WineFacadeJoined;
import com.apress.ejb.chapter09.joined.Wine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

public class WineStoreJoinedBean implements Serializable {
  @EJB
  private ShoppingCartJoined shoppingCart;
  @EJB
  private WineFacadeJoined wineFacade;

  private List<SelectItem> wineDisplayList;
  private String qty = "10";
  private String wineKey;

  public void addWineToCart() {
    if (qty == null) {
      qty = "1";
    }
    shoppingCart.addWineToCart(new Integer(getWineKey()), new Integer(getQty()));
  }

  public String processOrder() {
    shoppingCart.processOrder();
    return "success";
  }

  public void setWineDisplayList(List<SelectItem> wineDisplayList) {
    this.wineDisplayList = wineDisplayList;
  }

  public List getWineDisplayList() {
    if (wineDisplayList == null) {
      wineDisplayList = new ArrayList<SelectItem>();
      for (Wine wine : wineFacade.findAll()) {
        wineDisplayList.add(new SelectItem(wine.getId(), wine.getName()));
      }
    }

    return wineDisplayList;
  }

  public void setQty(String qty) {
    this.qty = qty;
  }

  public String getQty() {
    return qty;
  }

  public void setWineKey(String wineKey) {
    this.wineKey = wineKey;
  }

  public String getWineKey() {
    return wineKey;
  }
}
