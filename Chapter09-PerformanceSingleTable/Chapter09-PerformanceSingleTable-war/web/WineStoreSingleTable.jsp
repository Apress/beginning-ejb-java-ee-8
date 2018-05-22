<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1252"/>
      <title>Performance test for WineStoreSingleTable application</title>
      <style type="text/css">
        body {
          background-color: #f7f7f7; 
        }
      </style>
    </head>
    <body><h:form>
        <p>
          <strong>Simple JSF page to add wines to shoppingcart and finally
            submit an order</strong>
        </p>
        <p>
          <h:messages/>
        </p>
        <hr/>
        <p>
          Select a Wine from the following list:
        </p>
        <p>
          <h:selectOneListbox value="#{WineStoreSingleTableBean.wineKey}"
                              id="selectOneListbox1">
            <f:selectItems value="#{WineStoreSingleTableBean.wineDisplayList}"/>
          </h:selectOneListbox>
        </p>
        <p>
          Enter Quantity
        </p>
        <p>
          <h:inputText value="#{WineStoreSingleTableBean.qty}"/>
        </p>
        <p>
          Click on Add to cart to add to shopping cart
        </p>
        <p>
          <h:commandButton value="Add to Cart"
                           action="#{WineStoreSingleTableBean.addWineToCart}"/>
        </p>
        <p>
          After adding Wines to the cart, click on Submit Order button
        </p>
        <h:commandButton value="Submit Order"
                         action="#{WineStoreSingleTableBean.ProcessOrder}"/>
      </h:form></body>
  </html>
</f:view>