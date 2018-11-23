package com.soccrates.middlertier.mail;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class MailSettingEntity.
 */
@Entity
@Table(name = "mailsetting")
public class MailSettingEntity {

	/** The mail id. */
	private long mailId;

	/** The smpt host. */
	private String emailId, password, smtpPort, smtpSocketFactoryPort, smptSocketFactoryClass, smptHost;

	/** The send mails. */
	private boolean debug, auth, enableSSL, socketFactoryFallBack, sendMails, enableTls;

	
	/**
	 * Gets the mail id.
	 *
	 * @return the mail id
	 */
	@Id
	public long getMailId() {
		return mailId;
	}

	/**
	 * Sets the mail id.
	 *
	 * @param mailId
	 *            the new mail id
	 */
	public void setMailId(long mailId) {
		this.mailId = mailId;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId
	 *            the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnableTls() {
		return enableTls;
	}

	public void setEnableTls(boolean enableTls) {
		this.enableTls = enableTls;
	}

	/**
	 * Gets the smtp port.
	 *
	 * @return the smtp port
	 */
	public String getSmtpPort() {
		return smtpPort;
	}

	/**
	 * Sets the smtp port.
	 *
	 * @param smtpPort
	 *            the new smtp port
	 */
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * Gets the smtp socket factory port.
	 *
	 * @return the smtp socket factory port
	 */
	public String getSmtpSocketFactoryPort() {
		return smtpSocketFactoryPort;
	}

	/**
	 * Sets the smtp socket factory port.
	 *
	 * @param smtpSocketFactoryPort
	 *            the new smtp socket factory port
	 */
	public void setSmtpSocketFactoryPort(String smtpSocketFactoryPort) {
		this.smtpSocketFactoryPort = smtpSocketFactoryPort;
	}

	/**
	 * Gets the smpt socket factory class.
	 *
	 * @return the smpt socket factory class
	 */
	public String getSmptSocketFactoryClass() {
		return smptSocketFactoryClass;
	}

	/**
	 * Sets the smpt socket factory class.
	 *
	 * @param smptSocketFactoryClass
	 *            the new smpt socket factory class
	 */
	public void setSmptSocketFactoryClass(String smptSocketFactoryClass) {
		this.smptSocketFactoryClass = smptSocketFactoryClass;
	}

	/**
	 * Checks if is debug.
	 *
	 * @return true, if is debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * Sets the debug.
	 *
	 * @param debug
	 *            the new debug
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * Checks if is auth.
	 *
	 * @return true, if is auth
	 */
	public boolean isAuth() {
		return auth;
	}

	/**
	 * Sets the auth.
	 *
	 * @param auth
	 *            the new auth
	 */
	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	/**
	 * Checks if is enable SSL.
	 *
	 * @return true, if is enable SSL
	 */
	public boolean isEnableSSL() {
		return enableSSL;
	}

	/**
	 * Sets the enable SSL.
	 *
	 * @param enableSSL
	 *            the new enable SSL
	 */
	public void setEnableSSL(boolean enableSSL) {
		this.enableSSL = enableSSL;
	}

	/**
	 * Checks if is socket factory fall back.
	 *
	 * @return true, if is socket factory fall back
	 */
	public boolean isSocketFactoryFallBack() {
		return socketFactoryFallBack;
	}

	/**
	 * Sets the socket factory fall back.
	 *
	 * @param socketFactoryFallBack
	 *            the new socket factory fall back
	 */
	public void setSocketFactoryFallBack(boolean socketFactoryFallBack) {
		this.socketFactoryFallBack = socketFactoryFallBack;
	}

	/**
	 * Gets the smpt host.
	 *
	 * @return the smpt host
	 */
	public String getSmptHost() {
		return smptHost;
	}

	/**
	 * Sets the smpt host.
	 *
	 * @param smptHost
	 *            the new smpt host
	 */
	public void setSmptHost(String smptHost) {
		this.smptHost = smptHost;
	}

	/**
	 * Checks if is send mails.
	 *
	 * @return true, if is send mails
	 */
	public boolean isSendMails() {
		return sendMails;
	}

	/**
	 * Sets the send mails.
	 *
	 * @param sendEmails
	 *            the new send mails
	 */
	public void setSendMails(boolean sendEmails) {
		this.sendMails = sendEmails;
	}

}
