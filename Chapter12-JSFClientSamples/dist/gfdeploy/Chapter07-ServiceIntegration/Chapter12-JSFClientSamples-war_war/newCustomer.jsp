<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
      <title>New Customer Page</title>
    </head>
    <body>
      <h:form>
        <h1> Beginning EJB: Wine Store Application </h1>
        Enter the following information to register as a new customer:
        <table cellspacing="2" cellpadding="3" border="1" width="40%">
          <tr>
            <td width="33%">First Name</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.firstName}"/> </td>
          </tr>
          <tr>
            <td width="33%">Last Name</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.lastName}"/> </td>
          </tr>
          <tr>
            <td width="33%">Phone</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.phone}"/> </td>
          </tr>
          <tr>
            <td width="33%">Email</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.email}"/> </td>
          </tr>
          <tr>
            <td width="33%">Street 1</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.streetOne}"/> </td>
          </tr>
          <tr>
            <td width="33%">Street 2</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.streetTwo}"/> </td>
          </tr>
          <tr>
            <td width="33%">City</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.city}"/> </td>
          </tr>
          <tr>
            <td width="33%">State</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.state}"/> </td>
          </tr>
          <tr>
            <td width="33%">Zip Code</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.zipCode}"/> </td>
          </tr>
          <tr>
            <td width="33%">Credit Card </td>
            <td width="67%"> <h:inputText value="#{NewCustomer.ccnum}"/> </td>
          </tr>
          <tr>
            <td width="33%">Credit Card Expiry date</td>
            <td width="67%"> <h:inputText value="#{NewCustomer.ccexpDate}"/> </td>
          </tr>
        </table>
        <h1>
          <h:commandButton value="Submit" action="#{NewCustomer.AddNewCustomer}"/>
        </h1>
      </h:form>
    </body>
  </html>
</f:view>