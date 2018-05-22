<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type"content="text/html; charset=windows-1252"/>
      <title>Login Page</title>
    </head>
    <body>
      <h:form>
        <h3> Beginning EJB: Wine Store Application </h3>
        <h5> Enter Email address: </h5>
        <p>  <h:inputText value="#{Login.email}"/> </p>
        <p>  <h:commandButton value="Login" action="#{Login.processLogin}"/> </p>
      </h:form>
    </body>
  </html>
</f:view>