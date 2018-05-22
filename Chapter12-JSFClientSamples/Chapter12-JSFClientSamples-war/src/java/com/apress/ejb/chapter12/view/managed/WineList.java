package com.apress.ejb.chapter12.view.managed;

import com.apress.ejb.chapter07.ejb.SearchFacadeLocal;
import com.apress.ejb.chapter07.entities.Wine;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

public class WineList
{
  public WineList() {
  }

  @EJB
  private SearchFacadeLocal searchFacade;
  private List<Wine> winesList = new ArrayList();
  private HtmlDataTable dataTable1;

  public String findAllWines() {
    if (searchFacade == null) {
      return "gohome";
    }
    else {
      winesList = searchFacade.getWineFindAll();
      return "allwines";
    }
  }

  public String searchByCountry() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding wineyear = app.createValueBinding("#{SearchWines.country}");
    String country = wineyear.getValue(ctx).toString();
    if (searchFacade == null) {
      return "gohome";
    }
    else {
      winesList = searchFacade.getWineFindByCountry(country);
      return "success";
    }
  }

  public String searchByVarietal() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding wineyear = app.createValueBinding("#{SearchWines.varietal}");
    String varietal = wineyear.getValue(ctx).toString();
    if (searchFacade == null) {
      return "gohome";
    }
    else {
      winesList = searchFacade.getWineFindByVarietal(varietal);
      return "success";
    }
  }

  public String searchByYear() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding wineyear = app.createValueBinding("#{SearchWines.year}");
    String year = wineyear.getValue(ctx).toString();
    if (searchFacade == null) {
      return "gohome";
    }
    else {
      winesList = searchFacade.getWineFindByYear(new Integer(year));
      return "success";
    }
  }

  public String invokeAddToCart() {
    Wine addWine = (Wine)this.getDataTable1().getRowData();
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding binding = app.createValueBinding("#{JSFShoppingCart.selectedWine}");
    binding.setValue(ctx, addWine);
    return "addtocart";
  }

  public void setWinesList(List<Wine> winesList) {
    this.winesList = winesList;
  }

  public List<Wine> getWinesList() {
    return winesList;
  }

  public void setDataTable1(HtmlDataTable dataTable1) {
    this.dataTable1 = dataTable1;
  }

  public HtmlDataTable getDataTable1() {
    return dataTable1;
  }
}