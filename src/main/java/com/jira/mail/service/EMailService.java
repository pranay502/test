package com.jira.mail.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.Address;
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

import org.apache.log4j.Logger;

import com.jira.util.PropertyLoader;

public class EMailService extends Thread{

	private static Logger log = Logger.getLogger(EMailService.class);
	private String htmlBody = null;
	private String senderFullName;
	private String subject = null;
	Properties externalProperties = null;
	private ArrayList<String> toList = null;
	private ArrayList<String> ccList = null;
	private ArrayList<String> bccList = null;
	private ArrayList<MimeBodyPart> messageBodyPartList = new ArrayList<MimeBodyPart>();
	private Multipart multipart = null;

	public EMailService() throws IOException {
		externalProperties = PropertyLoader.getInstance().getExternalProperties();
		this.multipart = new MimeMultipart();
		toList = new ArrayList<String>();
	}

	public EMailService(String subject, String body, String senderName) throws IOException {
		externalProperties = PropertyLoader.getInstance().getExternalProperties();
		this.subject = subject;
		this.htmlBody = body;
		senderFullName = senderName;
		this.multipart = new MimeMultipart();
		toList = new ArrayList<String>();
	}

	public void run() {
		String hostAddress = getHostAddress(); // "192.168.240.100";//getHostAddress();
		int port = getSMTPPort(); // 25;//getSMTPPort();
		final String smtpUserName = getSMTPUserName();
		final String smtpPassword = getSMTPPassword();
		String sender = smtpUserName;
		getToList();
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.host", hostAddress);
			props.put("mail.smtp.port", port);
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(smtpUserName, smtpPassword);
				}
			});

			MimeMessage email = new MimeMessage(session);
			MimeBodyPart messageBodyPart1 = new MimeBodyPart();

			if (toList != null) {
				Address[] addresses = new Address[toList.size()];
				for (int i = 0; i < toList.size(); i++) {
					addresses[i] = new InternetAddress(toList.get(i).toString());
				}
				email.setRecipients(Message.RecipientType.TO, addresses);
			}

			if (ccList != null) {
				for (int i = 0; i < ccList.size(); i++) {
					email.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccList.get(i).toString()));
				}
			}

			if (bccList != null) {
				for (int i = 0; i < bccList.size(); i++) {
					email.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccList.get(i).toString()));
				}
			}

			email.setFrom(new InternetAddress(sender, senderFullName));
			
			email.setSubject(subject);
			messageBodyPart1.setText(htmlBody, "utf-8", "html");

			multipart.addBodyPart(messageBodyPart1);
			if(!messageBodyPartList.isEmpty()){
				for(MimeBodyPart bodyPart : messageBodyPartList)
					multipart.addBodyPart(bodyPart);
			}

			email.setContent(multipart);
			log.info("Sending Mail :: " + subject);
			Transport.send(email);
			log.info("Mail sent successfully :: " + subject);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private String getHostAddress() {
		String serverIP = null;
		if (externalProperties != null) {
			serverIP = externalProperties.getProperty("SMTP_SERVER_IP_ADDRESS");
		}
		return serverIP;
	}

	private int getSMTPPort() {
		int port = 25;
		if (externalProperties != null) {
			String strPort = externalProperties.getProperty("SMTP_SERVER_PORT_NUMBER");
			if (strPort != null && strPort.length() > 0) {
				port = Integer.parseInt(strPort);
			}
		}
		return port;
	}

	private String getSMTPUserName() {
		String smtpUserName = null;
		if (externalProperties != null) {
			smtpUserName = externalProperties.getProperty("SMTP_USERNAME");
		}
		return smtpUserName;
	}

	private String getSMTPPassword() {
		String smtpPassword = null;
		if (externalProperties != null) {
			smtpPassword = externalProperties.getProperty("SMTP_PASSWORD");
		}
		return smtpPassword;
	}

	private void getToList() {
		String toListString = null;
		if (externalProperties != null) {
			toListString = externalProperties.getProperty("TO_MAIL");
		}
		if (toListString.contains(",")) {
			for (String email : toListString.split(","))
				toList.add(email);
		} else
			toList.add(toListString);
	}

	public void addAttachment(String attachmentPath, String attachmentName) throws IOException, MessagingException {
		String filesPath = "";
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		File attachmentFile = new File(attachmentPath);
		if (!attachmentFile.canRead()) {
			throw new IOException("Cannot attach the file " + attachmentPath + " to the email.");
		}

		if (attachmentFile.canRead()) {
			filesPath = attachmentFile.getPath();
			log.info("************filesPath" + filesPath);
			DataSource source = new FileDataSource(filesPath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			if (attachmentName != null)
				messageBodyPart.setFileName(attachmentName);
			else
				messageBodyPart.setFileName(attachmentFile.getName());
			multipart.addBodyPart(messageBodyPart);
		}

	}

	public void embedImage(String strImageLocation, String contentId, boolean isUrl) {
		DataSource logo = null;
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		try {
			if(isUrl){
				try {
					logo = new URLDataSource(new URL(strImageLocation));
				} catch (MalformedURLException e) {
					log.error(e.getMessage(), e);
					return;
				}
			} else {
				logo = new FileDataSource(strImageLocation);
			}
			messageBodyPart.setDataHandler(new DataHandler(logo));
			messageBodyPart.setHeader("Content-ID", contentId);
			messageBodyPart.setHeader("Content-Disposition", "inline");
			messageBodyPartList.add(messageBodyPart);
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public static void triggerErrorNotification(Exception e, String issueId, String notificationType, String subject){
		String body = "<html><body>";
		body += "<p> Error Message: " + e.getMessage() + "</p>";
		body += "<br/><br/><p> StackTrace:<span style='font-color:red'>";
		for(Object trace : e.getStackTrace()){
			body += trace.toString();
		}
		body += "</span></p>";
		if(issueId != null){
			body += "<br/><br/> for issue: " + issueId;
			body += "<br/> while sending " + notificationType + " notification.";
		}
		try {
			new EMailService(subject, body, "Saikrishna").start();;
		} catch (IOException e1) {
			log.error(e1.getMessage(), e1);
		}
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
