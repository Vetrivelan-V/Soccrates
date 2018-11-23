package com.soccrates.middlertier.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;

// TODO: Auto-generated Javadoc
/**
 * The Class SendMail.
 */
public class SendMail {

	/**
	 * Send exception mail.
	 *
	 * @param packageName
	 *            the package name
	 * @param Emessage
	 *            the emessage
	 * @param toEmail
	 *            the to email
	 * @param s
	 *            the s
	 * @throws TaxometryException
	 *             the taxometry exception
	 */
	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// sendExceptionMail("Orange", "NullPointer Exception");
	//
	// }

	public static void sendExceptionMail(String packageName, String Emessage, String toEmail, org.hibernate.Session s)
			throws SoccratesException {

		MailSettingEntityHandler mailHandler = new MailSettingEntityHandler();
		final MailSettingBO handler = mailHandler.viewMailSetting(s, 1);
		// SettingEntityHandler handler = new SettingEntityHandler(s);
		// Recipient's email ID needs to be mentioned.

		String to1 = toEmail;
		if (to1 == null)
			to1 = handler.getEmailId();
		// Sender's email ID needs to be mentioned
		// ;//"bvbi.mobile@gmail.com";

		// Assuming you are sending email from localhost

		// Get system properties
		Properties properties = System.getProperties();
		properties.setProperty("mail.user", handler.getEmailId());
		properties.setProperty("mail.password", handler.getPassword());
		// Setup mail server
		properties.put("mail.smtp.host", handler.getSmptHost());
		properties.put("mail.smtp.port", handler.getSmtpPort());
		properties.put("mail.debug", (handler.isDebug()) ? "true" : "false");
		properties.put("mail.smtp.auth", (handler.isAuth()) ? "true" : "false");
		properties.put("mail.smtp.starttls.enable", (handler.isEnableTls()) ? "true" : "false");
		properties.put("mail.smtp.EnableSSL.enable", (handler.isEnableSSL()) ? "true" : "false");
		if (handler.isEnableSSL())
			properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.port", handler.getSmtpPort());
		properties.setProperty("mail.smtp.socketFactory.port", handler.getSmtpPort());
		// Get the default Session object.
		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(handler.getEmailId(), handler.getPassword());
			}
		});
		session.setDebug(true);
		// Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(handler.getEmailId()));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to1));
			// Set Subject: header field
			message.setSubject(packageName);

			// Send the actual HTML message, as big as you like
			message.setContent(Emessage, "text/html");

			// message.setText(Emessage);
			Transport transport = session.getTransport("smtp");
			// transport.connect(host, from, pass);

			message.saveChanges();
			Transport.send(message);
			transport.close();
			// Send message
			// Transport transport = session.getTransport("smtps");
			// transport.send(message);
			// Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			throw new SoccratesException(SoccratesExceptionCode.MAIL_NOT_SET);
		}
	}

	/**
	 * Sends an email receipt to special email address, intended to be used for
	 * sending receipts to admins or users whenever a new test deck is uploaded, or
	 * results for a test deck become available.
	 *
	 * @param subject
	 *            : the subject (title) of the email to be sent
	 * @param receipt
	 *            : the body of the email to be sent
	 * @param session
	 *            the session
	 */
	// public static void sendEmailReceipt(String subject, String receipt,
	// org.hibernate.Session session) { }

}
