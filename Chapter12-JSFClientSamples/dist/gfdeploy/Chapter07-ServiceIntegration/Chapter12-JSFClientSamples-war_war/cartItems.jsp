<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
      <title>Cart Items Page</title>
    </head>
    <body>
      <h:form>
        <h3>Beginning EJB: Wine Store Application</h3>
        <h4>Shopping Cart</h4>
          <h:dataTable value="#{JSFShoppingCart.cartItems}" var="cartItems" cellspacing="2" cellpadding="3" border="1" width="40%">
            <h:column>
              <f:facet name="header">
                <h:outputText value="Id"/>
              </f:facet>
              <h:outputText value="#{cartItems.id}"/>
            </h:column>
            <h:column>
              <f:facet name="header">
                <h:outputText value="Created Date"/>
              </f:facet>
              <h:outputText value="#{cartItems.createdDate}"/>
            </h:column>
            <h:column>
              <f:facet name="header">
                <h:outputText value="Wine"/>
              </f:facet>
              <h:outputText value="#{cartItems.wine.name}"/>
            </h:column>
            <h:column>
              <f:facet name="header">
                <h:outputText value="Quantity"/>
              </f:facet>
              <h:outputText value="#{cartItems.quantity}"/>
            </h:column>
          </h:dataTable>
          <h:commandButton value="Submit Order" action="#{JSFShoppingCart.ProcessOrder}"/>
      </h:form>
    </body>
  </html>
</f:view>