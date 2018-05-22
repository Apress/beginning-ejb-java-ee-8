<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
      <title>Add To Cart Page</title>
    </head>
    <body>
      <h:form>
        <h3>Beginning EJB: Wine Store Application</h3>
        <h5>Selected Wine - Enter Quantity and press AddtoCart button</h5>
        <table cellspacing="3" cellpadding="2" border="1" width="50%">
          <tr>
            <td>Wine ID</td>
            <td> <h:outputText value="#{JSFShoppingCart.selectedWine.id}"/> </td>
          </tr>
          <tr>
            <td>Name</td>
            <td> <h:outputText value="#{JSFShoppingCart.selectedWine.name}"/> </td>
          </tr>
          <tr>
            <td>Description</td>
            <td> <h:outputText value="#{JSFShoppingCart.selectedWine.description}"/> </td>
          </tr>
          <tr>
            <td>Country</td>
            <td> <h:outputText value="#{JSFShoppingCart.selectedWine.country}"/> </td>
          </tr>
          <tr>
            <td>Rating</td>
            <td> <h:outputText value="#{JSFShoppingCart.selectedWine.rating}"/> </td>
          </tr>
          <tr>
            <td>Region</td>
            <td> <h:outputText value="#{JSFShoppingCart.selectedWine.region}"/> </td>
          </tr>
          <tr>
            <td>Retail Price</td>
            <td> <h:outputText value="#{JSFShoppingCart.selectedWine.retailPrice}"/> </td>
          </tr>
          <tr>
            <td>Varietal</td>
            <td> <h:outputText value="#{JSFShoppingCart.selectedWine.varietal}"/> </td>
          </tr>
          <tr>
            <td>Year</td>
            <td>
              <h:outputText value="#{JSFShoppingCart.selectedWine.year}"/>
            </td>
          </tr>
          <tr>
            <td>Quantity</td>
            <td> <h:inputText value="#{JSFShoppingCart.quantity}"/> </td>
          </tr>
        </table>
        <p>
          <h:commandButton value="Add to cart" action="#{JSFShoppingCart.addToCart}"/>
        </p>
      </h:form>
    </body>
  </html>
</f:view>