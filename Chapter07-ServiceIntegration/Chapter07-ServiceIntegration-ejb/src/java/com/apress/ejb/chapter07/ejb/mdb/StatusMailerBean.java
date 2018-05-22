package com.apress.ejb.chapter07.ejb.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName = "destinationName",
                            propertyValue = "StatusMessageTopic"),
  @ActivationConfigProperty(propertyName = "destinationType",
                            propertyValue = "javax.jms.Topic")
}, mappedName = "StatusMessageTopic")
public class StatusMailerBean implements MessageListener {
  @Resource(name = "mail/wineappMail")
  private Session mailSession;

  public void onMessage(Message message) {
    try {
      if (message instanceof MapMessage) {
        MapMessage orderMessage = (MapMessage) message;
        String from = orderMessage.getStringProperty("from");
        String to = orderMessage.getStringProperty("to");
        String subject = orderMessage.getStringProperty("subject");
        String content = orderMessage.getStringProperty("content");
        javax.mail.Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] address = {new InternetAddress(to)};
        msg.setRecipients(RecipientType.TO, address);
        msg.setSubject(subject);
        msg.setSentDate(new java.util.Date());
        msg.setContent(content, "text/html");
        System.out.println("MDB: Sending Message from " + from + " to " + to + "...");
        Transport.send(msg);
        System.out.println("MDB: Message Sent");
      } else {
        System.out.println("Invalid message ");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
