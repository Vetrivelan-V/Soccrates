package com.soccrates.middletier.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.soccrates.middletier.user.UserBO;
import com.soccrates.middletier.user.UserWebServices;
import com.soccrates.middletier.util.UtilityLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class BasicLoginModule.
 */
public class BasicLoginModule implements LoginModule {

	/** The logger. */
	private static Logger logger = UtilityLogger.getLog(BasicLoginModule.class.getSimpleName());

	/** The handler. */
	private CallbackHandler handler;

	/** The subject. */
	private Subject subject;

	/** The user principal. */
	private UserPrincipal userPrincipal;

	/** The role principal. */
	private RolePrincipal rolePrincipal;

	/** The login. */
	private String login;

	/** The user groups. */
	// private String loginpassword;
	private List<String> userGroups;

	/** The is exception. */
	String isException;

	/** The message. */
	String message;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.security.auth.spi.LoginModule#login()
	 */
	@Override
	public boolean login() throws LoginException {
		Callback[] callbacks = new Callback[2];
		logger.info("LoginBO");
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);
		UserBO loginBO = new UserBO();
		try {
			this.handler.handle(callbacks);
			String name = ((NameCallback) callbacks[0]).getName();
			String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());

			System.out.println("in login try....." + name + "...." + password);

			loginBO.setLoginId(name);
			loginBO.setPassword(password);
			UserWebServices userWebServices = new UserWebServices();
			loginBO = userWebServices.login(loginBO);
			if (!loginBO.isException()) {

				System.out.println("in true case");
				this.login = name;
				this.userGroups = new ArrayList<String>();
				this.userGroups.add("admin");
				System.out.println("before return");
				return true;
			}
			System.out.println("before exit");
			return true;
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Exception", e);
			throw new LoginException(e.getMessage());
		} catch (UnsupportedCallbackException e) {
			logger.log(Level.SEVERE, "Exception", e);
			throw new LoginException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	@Override
	public boolean commit() throws LoginException {
		this.userPrincipal = new UserPrincipal(this.login);
		this.subject.getPrincipals().add(this.userPrincipal);
		if ((this.userGroups != null) && (this.userGroups.size() > 0)) {
			for (String groupName : this.userGroups) {
				this.rolePrincipal = new RolePrincipal(groupName);
				this.subject.getPrincipals().add(this.rolePrincipal);
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	@Override
	public boolean logout() throws LoginException {
		logger.log(Level.INFO, "Seesion Invalidated");
		this.subject.getPrincipals().remove(this.userPrincipal);
		this.subject.getPrincipals().remove(this.rolePrincipal);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject,
	 * javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
	 */
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		// TODO Auto-generated method stub
		this.handler = callbackHandler;
		this.subject = subject;
	}
}
