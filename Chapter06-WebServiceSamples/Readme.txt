Readme.txt - Chapter 05
=======================

This Readme file will help you in configuring the WebService that is
referenced using the WSDL file's location.

NOTE: No change is required if your GlassFish server is configured to use
port 8080.

Updating the port number in the WebService reference
----------------------------------------------------
"CreditServiceClient" class in  "com.apress.ejb.chapter06.client"
package contains the following line:

@WebServiceRef(wsdlLocation = "http://localhost:8080/CreditService/CreditCheckEndpointBean?WSDL")

"8080" is GlassFish server's default port.

If your GlassFish server has been configured to run at a different port
then you need to replace "8080" with that port before running the
Chapter 6 sample code.


