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
      <title>Search Wines Page</title>
    </head>
    <body><h:form>
        <h2> Beginning EJB: Wine Store Application </h2>
        <h4> Search Wines </h4>
        <table cellspacing="2" cellpadding="3" border="1" width="33%">
          <tr>
            <td><h:outputText value="Year"/></td>
            <td>           
            <h:selectOneListbox value="#{SearchWines.year}">
              <f:selectItem itemLabel="2001" itemValue="2001"/>
              <f:selectItem itemLabel="2002" itemValue="2002"/>
              <f:selectItem itemLabel="2003" itemValue="2003"/>
              <f:selectItem itemLabel="2004" itemValue="2004"/>
              <f:selectItem itemLabel="2005" itemValue="2005"/>
              <f:selectItem itemLabel="2006" itemValue="2006"/>
            </h:selectOneListbox></td>
            <td><h:commandButton value="Go" action="#{WineList.searchByYear}"/></td>
          </tr>
          <tr>
            <td><h:outputLabel value="Country"/></td>
            <td>          
          <h:selectOneListbox value="#{SearchWines.country}">
            <f:selectItem itemLabel="USA" itemValue="USA"/>
            <f:selectItem itemLabel="France" itemValue="France"/>
            <f:selectItem itemLabel="Australia" itemValue="Australia"/>
          </h:selectOneListbox></td>
            <td>
          <h:commandButton value="Go" action="#{WineList.searchByCountry}"/></td>
          </tr>
          <tr>
            <td><h:outputLabel value="Varietal"/></td>
            <td><h:selectOneListbox value="#{SearchWines.varietal}">
            <f:selectItem itemLabel="Zinfandel" itemValue="Zinfandel"/>
          </h:selectOneListbox></td>
            <td><h:commandButton value="Go" action="#{WineList.searchByVarietal}"/></td>
          </tr>
        </table>
      </h:form></body>
  </html>
</f:view>