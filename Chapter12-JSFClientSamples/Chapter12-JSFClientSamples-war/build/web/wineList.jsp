<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
      <title>Wine List Page</title>
    </head>
    <body>
      <h:form>
        <h2> Beginning EJB: Wine Store Application </h2>
        <h:dataTable value="#{WineList.winesList}" var="wines"  binding="#{WineList.dataTable1}" id="dataTable1" cellspacing="2" cellpadding="3" border="1" width="80%">
          <h:column>
            <f:facet name="header">
              <h:outputText value="Id"/>
            </f:facet>
            <h:commandLink action="#{WineList.invokeAddToCart}">
                <h:outputText value="#{wines.id}"/>
            </h:commandLink>
        
          </h:column>
          <h:column>
            <f:facet name="header">
              <h:outputText value="Name"/>
            </f:facet>
            <h:outputText value="#{wines.name}"/>
          </h:column>
          <h:column>
            <f:facet name="header">
              <h:outputText value="Varietal"/>
            </f:facet>
            <h:outputText value="#{wines.varietal}"/>
          </h:column>
          <h:column>
            <f:facet name="header">
              <h:outputText value="Country"/>
            </f:facet>
            <h:outputText value="#{wines.country}"/>
          </h:column>
          <h:column>
            <f:facet name="header">
              <h:outputText value="Year"/>
            </f:facet>
            <h:outputText value="#{wines.year}"/>
          </h:column>
          <h:column>
            <f:facet name="header">
              <h:outputText value="Region"/>
            </f:facet>
            <h:outputText value="#{wines.region}"/>
          </h:column>
          <h:column>
            <f:facet name="header">
              <h:outputText value="Rating"/>
            </f:facet>
            <h:outputText value="#{wines.rating}"/>
          </h:column>
          <h:column>
            <f:facet name="header">
              <h:outputText value="Retail Price"/>
            </f:facet>
            <h:outputText value="#{wines.retailPrice}"/>
          </h:column>
          <h:column>
            <f:facet name="header">
              <h:outputText value="Description"/>
            </f:facet>
            <h:outputText value="#{wines.description}"/>
          </h:column>
          <h:column>
            <h:commandButton value="Add to Cart" action="#{WineList.invokeAddToCart}"/>
          </h:column>
        </h:dataTable>
      </h:form>
    </body>
  </html>
</f:view>