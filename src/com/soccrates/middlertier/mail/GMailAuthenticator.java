package com.soccrates.middlertier.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
// TODO: Auto-generated Javadoc

/**
 * The Class GMailAuthenticator.
 */
public class GMailAuthenticator extends Authenticator {

	 private String userName = null;
	 private String password = null;

	 public GMailAuthenticator(String userName, String password) {
	  this.userName = userName;
	  this.password = password;

	 }

	 @Override
	 public PasswordAuthentication getPasswordAuthentication() {
	  return new PasswordAuthentication(userName, password);
	 }
    


}
