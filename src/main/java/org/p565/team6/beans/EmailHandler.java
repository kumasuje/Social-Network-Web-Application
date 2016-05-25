package org.p565.team6.beans;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHandler {

	public static String sendEmail(String emailID, String oldPassword) throws Exception {
		System.out.println("Entered ForgetPassword");
	if(oldPassword != null){
		final String username = "socialnetworkiu@gmail.com";
		final String password = "Se5652016";
		System.out.println("EmailHandler"+oldPassword);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailID));
			message.setSubject("Your old password!");
			message.setText("Dear " + emailID + ",\n\n\n Your password is: " + oldPassword);

			Transport.send(message);

			System.out.println("Email Done");
            return "true";
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	else {
		System.out.println("Email not Sent");
		return "false";
	}
}
}
