<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
      <title>Process Order Page</title>
    </head>
    <body>
      <h:form>
        <p> <strong>Beginning EJB: Wine Store Application</strong> </p>
        <p> <strong>Your order has been submitted, you will receive an email with order id and details.</strong> </p>
        <p>
          <h:commandLink value="Back to Home" action="winehome2">
            <h:outputText value="Back to wine search"/>
          </h:commandLink>
        </p>
      </h:form>
    </body>
  </html>
</f:view>