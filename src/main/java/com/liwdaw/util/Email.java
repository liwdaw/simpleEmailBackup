package com.liwdaw.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
	private Properties properties;
	private Session session;
	private Message message;
	private Multipart multipart;
	
	public void setProperties(String smtpHost, String smtpPort) {
		properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		properties.put("mail.smtp.EnableSSL.enable","true");  
	}
	
	public void createSession(String senderEmail, String senderPassword) throws Exception {
		final String username = senderEmail;
		final String password = senderPassword;
		session = Session.getInstance(
			properties,
		    new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
		    	}
			}
		);
		message = new MimeMessage(session);
    	message.setFrom(new InternetAddress(username));
	}
	
	public void setReceiver(String receiverEmail) throws Exception {
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receiverEmail));
	}
	
	public void setTitle(String title) throws Exception {
		message.setSubject(title);
	}
	
	void addAtachments(Path directoryPath, List<String> filesNames, String filesExtension) throws Exception {
        multipart = new MimeMultipart();
		for (int i=0; i<filesNames.size(); i++) {
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.attachFile(directoryPath.toString()+filesNames.get(i));
			multipart.addBodyPart(mimeBodyPart);
  			System.out.println(filesNames.get(i));
		}	
		message.setContent(multipart);
	}
	
	void send() throws Exception {		    	
	    System.out.println("Sending...");
	    Transport.send(message);
	    System.out.println("Done!");
	  }
}
