package com.soccrates.middletier.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.team.TeamEntity;
import com.soccrates.middletier.user.UserEntity;
import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.UtilityLogger;
import com.soccrates.middletier.util.UtilitySession;

public class MessageEnityHandler {
	private static Logger logger = UtilityLogger.getLog(MessageEnityHandler.class.getSimpleName());

//
	public MessageBO createMessage(MessageBO userBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createTeam()", userBo);
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setCreatedDate(new Date());
			messageEntity.copy(userBo);
			userBo.setCreatedDate(ApplicationConstants.US_DATE_FORMAT.format(new Date()));
			if (userBo.getTeamId() > 0) {

				TeamEntity coachEntity = (TeamEntity) session.get(TeamEntity.class, userBo.getTeamId());
				if (coachEntity != null) {
					messageEntity.setTeamEntity(coachEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
				}

			}
			if(userBo.getSenderId()>0)
			{
				UserEntity coachEntity = (UserEntity) session.get(UserEntity.class, userBo.getSenderId());
				if (coachEntity != null) {
					messageEntity.setSenderId(coachEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
				}
			}
			else
			{
				throw new SoccratesException(SoccratesExceptionCode.SENDERIDORRECEVIERIDNOTSET);
			}
				List<RecipientEntity> recipientEntities = new ArrayList<>();
			for (RecipientBO iterable_element : userBo.getRecipientList()) {
				if (iterable_element.getToId() > 0) {
					UserEntity e = (UserEntity) session.get(UserEntity.class, iterable_element.getToId());
					if (e != null) {
						RecipientEntity recipientEntity = new RecipientEntity();
						recipientEntity.setMessageId(messageEntity);
						recipientEntity.setToId(e);
//						recipientEntity.set
						session.save(recipientEntity);
//						userBo.getRecipientList().
						recipientEntities.add(recipientEntity);
					}

					else
						throw new SoccratesException(SoccratesExceptionCode.USERNOTFOUND);
				}
			}
			messageEntity.setRecipientList(recipientEntities);
			session.save(messageEntity);
			session.getTransaction().commit();
			userBo.setMessageId(messageEntity.getMessageId());

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + userBo.getSubject() + " already exist, please try with",
					SoccratesExceptionCode.USERNOTFOUND);

		} catch (HibernateException exception) {
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);

		} catch (Exception e) {

			e.printStackTrace();
			throw new SoccratesException(e.getMessage(), SoccratesExceptionCode.UNHANDLEDEXCEPION);
		} finally {
			logger.exiting(this.getClass().getName(), "createUser()");
			if (session.isOpen()) {
				session.close();
			}
		}
		return userBo;

	}

	public MessageBO getMessageById(MessageBO userBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createTeam()", userBo);

			if (userBo.getMessageId() > 0) {

				MessageEntity coachEntity = (MessageEntity) session.get(MessageEntity.class, userBo.getMessageId());
				if (coachEntity != null) {
					userBo.copy(coachEntity);
					List<RecipientBO> recipientBOs = new ArrayList<>();
					for (RecipientEntity iterable_element : coachEntity.getRecipientList()) {
						RecipientBO recipientBO = new RecipientBO();
						recipientBO.copy(iterable_element);
						recipientBOs.add(recipientBO);
					}
					userBo.setRecipientList(recipientBOs);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
				}

			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + userBo.getSubject() + " already exist, please try with",
					SoccratesExceptionCode.USERNOTFOUND);

		} catch (HibernateException exception) {
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);

		} catch (Exception e) {

			e.printStackTrace();
			throw new SoccratesException(e.getMessage(), SoccratesExceptionCode.UNHANDLEDEXCEPION);
		} finally {
			logger.exiting(this.getClass().getName(), "createUser()");
			if (session.isOpen()) {
				session.close();
			}
		}
		return userBo;

	}

	public MessageListBO getMessageList(MessageSearchBO teamSeachBO) throws SoccratesException {
		
		MessageListBO teamListBO = new MessageListBO();
		ArrayList<MessageBO> teamBOs = new ArrayList<MessageBO>();

		Session session = UtilitySession.getSession();
		session.beginTransaction();long userId=0;
		
		try {
			String listString;
			
//			boolean senderIdset=false;
			if (teamSeachBO.getSenderId() > 0) {
				 listString = " from MessageEntity as r where senderId.userId =:userId";
//				senderIdset=true;
				userId=teamSeachBO.getSenderId();
			}
			else if(teamSeachBO.getReceiverId()>0)
			{ listString = " from MessageEntity as r where messageId IN (select messageId from RecipientEntity where toId.userId =:userId)";
				userId=teamSeachBO.getReceiverId();
			}
			else
				throw new SoccratesException(SoccratesExceptionCode.SENDERIDORRECEVIERIDNOTSET);
			
				org.hibernate.Query query = session.createQuery(listString);
				query.setLong("userId",userId);
				@SuppressWarnings("unchecked")
				List<MessageEntity> retailers = query.list();
				for (MessageEntity data : retailers) {
					MessageBO teamBO = new MessageBO();
					teamBO.copy(data);
					teamBOs.add(teamBO);
				}

				teamListBO.setMessageBOs(teamBOs);

			  

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Coach Name : " + userId + " Not Present",
					SoccratesExceptionCode.USERNOTFOUND);

		} catch (HibernateException exception) {
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);

		} catch (Exception e) {

			e.printStackTrace();
			throw new SoccratesException(e.getMessage(), SoccratesExceptionCode.UNHANDLEDEXCEPION);
		} finally {
			logger.exiting(this.getClass().getName(), "createUser()");
			if (session.isOpen()) {
				session.close();
			}
		}
		return teamListBO;

	}
	
}
