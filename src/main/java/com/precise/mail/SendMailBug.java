package com.precise.mail;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;
@Component
public class SendMailBug {

	
	public void sent(String kp){
		final String username = "preciseexpense2017@gmail.com";
	    final String password = "precise2017";

	    Properties p = new Properties();
	    p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "25");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.EnableSSL.enable", "true");
		p.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		p.setProperty("mail.smtp.socketFactory.fallback", "false");
		p.setProperty("mail.smtp.port", "465");
		p.setProperty("mail.smtp.socketFactory.port", "465");
	    
	    Session session;
	    session = Session.getInstance(p,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

	    try {

	        Message message = new MimeMessage(session);
	       // message.setFrom(new InternetAddress("from.mail.id@gmail.com"));
	        message.setFrom(new InternetAddress("parthdarak11@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(kp));
	        message.setSubject("Daily Bug Report Sheet");
	        message.setText("PFA");

	        MimeBodyPart messageBodyPart = new MimeBodyPart();

	        Multipart multipart = new MimeMultipart();

	        messageBodyPart = new MimeBodyPart();
	        //String file = "path of file to be attached";
	        File file = new File("D:\\BugSheet.xls");
	        //String file = "D:\\secure";
	        
	        //String fileName = "h.jpg";
	        
	        
	       // String fileName = "attachmentName";
	        DataSource source = new FileDataSource(file);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        //messageBodyPart.setFileName(fileName);
	        multipart.addBodyPart(messageBodyPart);
	        System.out.println(source);

	        message.setContent(multipart);

	        System.out.println("Sending");

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	  }

	
}
