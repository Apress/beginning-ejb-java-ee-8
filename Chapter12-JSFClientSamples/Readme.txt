Readme.txt - Chapter 12
=======================

This Readme file will help you in configuring the JSF Wine Store application.
It will also provide pointers that will help resolve any issues that you
may face while executing the application.

Application Project Setup
-------------------------
Assuming you are using "C:\SampleCode" directory for trying out the samples
then make sure you have the "Chapter06-WebServiceSamples" directory and 
"Chapter12-JSFClientSamples" directory along side each other as shown below.

- C:\
  - SampleCode
    + Chapter06-WebServiceSamples
    + Chapter12-JSFClientSamples

For Chapter12-JSFClientSamples application we have cloned Chapter 7's 
EJB and JPA entity code hence, after opening the NetBeans IDE make sure 
you don't have the Chapter 7 project open inside NetBeans. Else you
might end up with duplicate project artifacts.

Server-side Artifacts
---------------------
Make sure various server-side artifact have been created.

- WineApp database with login/password as wineapp/wineapp
- Two TopicConnectionFactories:
  1) StatusMessageTopicConnectionFactory
  2) poTopicConnectionFactory
- Two Topics: 
  1) StatusMessageTopic 
  2) PurchaseOrderTopic
- JavaMail configuration

