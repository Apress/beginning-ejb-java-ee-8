Readme.txt - Chapter 05
=======================

This Readme file will help you in configuring the JavaMail Session in the 
GlassFish application server for the "mail/winappMail" resource for the GMail 
and Yahoo mail servers.

Yahoo Mail Server Configuration
-------------------------------
If you wish to configure Yahoo's SMTP server for sending the mail then use the
following settings in conjunction with the information provided in Figure 5-13
and Figure 5-14 of Chapter 5.

- Mail Host: smtp.mail.yahoo.com
- Default User: wineapp@yahoo.com
- Default Sender Address: wineapp@yahoo.com

Additional Properties:
- mail-smtp-host: smtp.mail.yahoo.com
- mail-smtp-user: wineapp@yahoo.com
- mail-smtp-password: wine_app
- mail-smtp-auth: true
- mail-smtp-port: 465
- mail-smtp-socketFactory-port: 465
- mail-smtp-socketFactory-class: javax.net.ssl.SSLSocketFactory
- mail-smtp-starttls-enable: true
- mail-smtp-socketFactory-fallback: false

Restart the GlassFish server after saving the settings!!!

If you wish to use your personal Yahoo Mail ID then you need to replace
wineapp@yahoo.com with <Your Yahoo ID>@yahoo.com in settings as well as in
OrderProcessingBean.java source file.

GMail Mail Server Configuration
-------------------------------
If you wish to configure GMail's SMTP server for sending the mail then use the
following settings in conjunction with the information provided in Figure 5-13
and Figure 5-14 of Chapter 5.

- Mail Host: smtp.gmail.com
- Default User: <Your GMail ID>@gmail.com
- Default Sender Address: <Your GMail ID>@gmail.com

Additional Properties:
- mail-smtp-host: smtp.gmail.com
- mail-smtp-user: <Your GMail ID>@gmail.com
- mail-smtp-password: <Your GMail Password>
- mail-smtp-auth: true
- mail-smtp-port: 465
- mail-smtp-socketFactory-port: 465
- mail-smtp-socketFactory-class: javax.net.ssl.SSLSocketFactory
- mail-smtp-starttls-enable: true
- mail-smtp-socketFactory-fallback: false

Restart the GlassFish server after saving the settings!!!

Don't forget to update the "From" and "To" fields to <Your GMail ID>@gmail.com
in OrderProcessingBean.java before running the sample client program!!!

Problems
--------
If you have problems in sending/receiving mails, then see the Troubleshooting 
section available in Chapter 1.

Upon successful execution of the sample client GlassFish server will log the
following details in the "Output" log console.

INFO: Before status TopicCF connection
INFO: Created connection
INFO: started connection
INFO: Starting Topic Session
INFO: created producer
INFO: before send
INFO: after send