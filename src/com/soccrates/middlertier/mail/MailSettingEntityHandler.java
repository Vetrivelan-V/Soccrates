package com.soccrates.middlertier.mail;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.UtilityLogger;
 

// TODO: Auto-generated Javadoc
/**
 * The Class MailSettingEntityHandler.
 */
public class MailSettingEntityHandler {

	/** The logger. */
	private static Logger logger = UtilityLogger.getLog(MailSettingEntityHandler.class.getSimpleName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.core.mail.MailSettingEntityHandlerInterface#modifyMailSetting(org.
	 * hibernate.Session, com.core.mail.ws.MailSettingBO)
	 */ 
	public MailSettingBO modifyMailSetting(Session session, MailSettingBO mailSettingBO) throws SoccratesException {
		// TODO Auto-generated method stub
		try {
			long l = 1;
			boolean newOne = false;
			MailSettingEntity mailSettingEntity = (MailSettingEntity) session.get(MailSettingEntity.class, l);
			if (mailSettingEntity == null) {
				newOne = true;
				mailSettingEntity = new MailSettingEntity();
			}
			mailSettingEntity.setAuth(mailSettingBO.isAuth());
			mailSettingEntity.setDebug(mailSettingBO.isDebug());
			mailSettingEntity.setEmailId(mailSettingBO.getEmailId());
			mailSettingEntity.setEnableSSL(mailSettingBO.isEnableSSL());
			mailSettingEntity.setSmptHost(mailSettingBO.getSmptHost());
			mailSettingEntity.setSendMails(mailSettingBO.isSendMails());
			mailSettingEntity.setEnableTls(mailSettingBO.isEnableTls());
			if (mailSettingBO.getPassword() != null) {
				// try {
				mailSettingEntity.setPassword((mailSettingBO.getPassword()));
				// } catch (Exception e) {
				// // TODO Auto-generated catch block
				// logger.log(Level.SEVERE, "Uncaught exception", e);
				// throw new TaxometryException(e.getMessage());
				//
				// }
			}
			mailSettingEntity.setSmptSocketFactoryClass(mailSettingBO.getSmptSocketFactoryClass());
			mailSettingEntity.setSmtpPort(mailSettingBO.getSmtpPort());
			mailSettingEntity.setSocketFactoryFallBack(mailSettingBO.isSocketFactoryFallBack());
			mailSettingEntity.setSmtpSocketFactoryPort(mailSettingBO.getSmtpSocketFactoryPort());
			if (newOne) {
				mailSettingEntity.setMailId(1);
				session.save(mailSettingEntity);
			} else {
				session.update(mailSettingEntity);
			}
		} catch (HibernateException e) {
			e.printStackTrace();

			logger.log(Level.SEVERE, "Uncaught exception", e);
			throw new SoccratesException(SoccratesExceptionCode.HIBERNATEEXCEPION);

		}

		return mailSettingBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.core.mail.MailSettingEntityHandlerInterface#viewMailSetting(org.
	 * hibernate.Session)
	 */ 
	public MailSettingBO viewMailSetting(Session session,long id) throws SoccratesException {
		MailSettingBO mailSettingBO = new MailSettingBO();
		// TODO Auto-generated method stub
		try {
//			long l = 1;
			MailSettingEntity mailSettingEntity = (MailSettingEntity) session.get(MailSettingEntity.class, id);
			if (mailSettingEntity != null) {
				mailSettingBO.setAuth(mailSettingEntity.isAuth());
				mailSettingBO.setDebug(mailSettingEntity.isDebug());
				mailSettingBO.setEmailId(mailSettingEntity.getEmailId());
				mailSettingBO.setEnableSSL(mailSettingEntity.isEnableSSL());
				mailSettingBO.setSmptHost(mailSettingEntity.getSmptHost());
				mailSettingBO.setSendMails(mailSettingEntity.isSendMails());
				mailSettingBO.setEnableTls(mailSettingEntity.isEnableTls());
				if (mailSettingEntity.getPassword() != null) {
					// try {
					mailSettingBO.setPassword((mailSettingEntity.getPassword()));
					// } catch (Exception e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					//
					// logger.log(Level.SEVERE, "Uncaught exception", e);
					// }
				}
				mailSettingBO.setSmptSocketFactoryClass(mailSettingEntity.getSmptSocketFactoryClass());
				mailSettingBO.setSmtpPort(mailSettingEntity.getSmtpPort());
				mailSettingBO.setSocketFactoryFallBack(mailSettingEntity.isSocketFactoryFallBack());
				mailSettingBO.setSmtpSocketFactoryPort(mailSettingEntity.getSmtpSocketFactoryPort());
			} else {
				throw new SoccratesException(SoccratesExceptionCode.MAIL_NOT_SET);
			}

		} catch (HibernateException e) {
			e.printStackTrace();

			logger.log(Level.SEVERE, "Uncaught exception", e);
			throw new SoccratesException(SoccratesExceptionCode.HIBERNATEEXCEPION);

		}
		logger.info("" + mailSettingBO);
		return mailSettingBO;

	}

}
