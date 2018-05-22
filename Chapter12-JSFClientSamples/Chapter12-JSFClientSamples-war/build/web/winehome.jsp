<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
      <title>Wine Home Page</title>
    </head>
    <body>
      <h:form>
        <h1> Beginning EJB: Wine Store Application </h1>
        <p>
          <h:commandLink action="#{WineList.findAllWines}">
            <h:outputText value="Complete List of Wines"/>
          </h:commandLink>
        </p>
        <p>
          <h:commandLink action="search">
            <h:outputText value="Search by Year or Country or Varietal"/>
          </h:commandLink>
        </p>
        <p>
          <h:commandLink action="cartitems">
            <h:outputText value="View shopping cart and submit order"/>
          </h:commandLink>
        </p>
      </h:form>
    </body>
  </html>
</f:view>