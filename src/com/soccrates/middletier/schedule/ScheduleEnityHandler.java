package com.soccrates.middletier.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.team.TeamEntity;
import com.soccrates.middletier.user.UserEntity;
import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.SoccratesObject;
import com.soccrates.middletier.util.UtilityLogger;
import com.soccrates.middletier.util.UtilitySession;

public class ScheduleEnityHandler {
	private static Logger logger = UtilityLogger.getLog(ScheduleEnityHandler.class.getSimpleName());

	public ScheduleBO createSchedule(ScheduleBO scheduleBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createTeam()", scheduleBo);
			ScheduleEntity scheduleEnity = new ScheduleEntity();
			scheduleEnity.setCreatedDate(new Date());
			scheduleEnity.setPlaceName(scheduleBo.getPlaceName());
			scheduleEnity.setScore(scheduleBo.getScore());
			scheduleEnity.setScheduleType(scheduleBo.getScheduleType());
			scheduleEnity.setTitleName(scheduleBo.getTitleName());
			scheduleEnity.setComments(scheduleBo.getComments());
			scheduleEnity.setUniformColor(scheduleBo.getUniformColor());
			if (scheduleBo.getCoachId() > 0) {

				UserEntity coachEntity = (UserEntity) session.get(UserEntity.class, scheduleBo.getCoachId());
				if (coachEntity != null && coachEntity.getUserType() == ApplicationConstants.USERTYPE_COACH) {
					scheduleEnity.setCoach(coachEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}
			if (scheduleBo.getTeamId() > 0) {

				TeamEntity teamEntity = (TeamEntity) session.get(TeamEntity.class, scheduleBo.getTeamId());
				if (teamEntity != null) {
					scheduleEnity.setTeamEntity(teamEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
			}

			session.save(scheduleEnity);
			session.getTransaction().commit();
			scheduleBo.setScheduleId(scheduleEnity.getScheduleId());
//			session.beginTransaction();
//			if (scheduleBo.getReminderBOs() != null) {
//				for (int i = 0; i < scheduleBo.getReminderBOs().size(); i++) {
//					ReminderBO reminderBO = scheduleBo.getReminderBOs().get(i);
//					ReminderEntity reminderEntity = new ReminderEntity();
//					reminderEntity.setAlertType(reminderBO.getAlertType());
//					if (reminderBO.getEndDate() != null)
//						reminderEntity.setEndDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndDate()));
//					if (reminderBO.getEndRepeatDate() != null)
//						reminderEntity.setEndRepeatDate(
//								ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndRepeatDate()));
//					if (reminderBO.getRepeatDate() != null)
//						reminderEntity
//								.setRepeatDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getRepeatDate()));
//					reminderEntity.setRepeatType(reminderBO.getRepeatType());
//					try {
//						if (reminderBO.getScheduledDate() != null)
//							reminderEntity.setScheduledDate(
//									ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getScheduledDate()));
//						else
//							throw new SoccratesException(SoccratesExceptionCode.DATENOTSET);
//					} catch (Exception e) {
//						// TODO: handle exception
//						throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);
//					}
//					reminderEntity.setSecondAlert(reminderBO.getSecondAlert());
//					if (reminderBO.getStartDate() != null)
//						reminderEntity
//								.setStartDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getStartDate()));
//					reminderEntity.setUserId(scheduleEnity.getCoach());
//					reminderEntity.setScheduleEnity(scheduleEnity);
//					session.save(reminderEntity);
//					scheduleBo.getReminderBOs().get(i).setReminderId(reminderEntity.getReminderId());
////							reminderEntities.add(reminderEntity);
//
//				}
////						scheduleEnity.setReminderEntity(reminderEntities);
//
//			} else {
//				throw new SoccratesException(SoccratesExceptionCode.REMINDERNOTSET);
//			}
//
//			session.getTransaction().commit();

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + scheduleBo.getScheduleType() + " already exist, please try with",
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
		return scheduleBo;

	}
	
	public ReminderBO createReminder(ReminderBO reminderBO) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createTeam()", reminderBO);
//			ScheduleEntity scheduleEnity = new ScheduleEntity();
//			scheduleEnity.setCreatedDate(new Date());
//			scheduleEnity.setPlaceName(scheduleBo.getPlaceName());
//			scheduleEnity.setScore(scheduleBo.getScore());
//			scheduleEnity.setScheduleType(scheduleBo.getScheduleType());
//			scheduleEnity.setTitleName(scheduleBo.getTitleName());
//			scheduleEnity.setComments(scheduleBo.getComments());
//			scheduleEnity.setUniformColor(scheduleBo.getUniformColor());
			if (reminderBO.getScheduleId() > 0) {

				ScheduleEntity coachEntity = (ScheduleEntity) session.get(ScheduleEntity.class, reminderBO.getScheduleId());
				if (coachEntity != null )
					{
					ReminderEntity reminderEntity = new ReminderEntity();
				reminderEntity.setAlertType(reminderBO.getAlertType());
				if (reminderBO.getEndDate() != null)
					reminderEntity.setEndDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndDate()));
				if (reminderBO.getEndRepeatDate() != null)
					reminderEntity.setEndRepeatDate(
							ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndRepeatDate()));
				if (reminderBO.getRepeatDate() != null)
					reminderEntity
							.setRepeatDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getRepeatDate()));
				if (reminderBO.getEndRepeatDate() != null)
					reminderEntity
							.setEndRepeatDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getRepeatDate()));
				reminderEntity.setRepeatType(reminderBO.getRepeatType());
				try {
					if (reminderBO.getScheduledDate() != null)
						reminderEntity.setScheduledDate(
								ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getScheduledDate()));
					else
						throw new SoccratesException(SoccratesExceptionCode.DATENOTSET);
				} catch (Exception e) {
					// TODO: handle exception
					throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);
				}
				reminderEntity.setSecondAlert(reminderBO.getSecondAlert());
				if (reminderBO.getStartDate() != null)
					reminderEntity
							.setStartDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getStartDate()));
				
				
				
				
				if (reminderBO.getUserId() > 0) {

					UserEntity user = (UserEntity) session.get(UserEntity.class, reminderBO.getUserId());
					if (user != null ) {
						reminderEntity.setUserId(user);
					} else {
						throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
					}

				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}
//				reminderEntity.setUserId(coachEntity.getCoach());
				reminderEntity.setScheduleEnity(coachEntity);
//				coachEntity.getReminderEntity().add(reminderEntity);
			
//				session.update(coachEntity);
session.save(reminderEntity);
				session.getTransaction().commit();
				reminderBO.setReminderId(reminderEntity.getReminderId());
//						reminderEntities.add(reminderEntity);
//
				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}
			 

			 
		
//			session.beginTransaction();
//			if (scheduleBo.getReminderBOs() != null) {
//				for (int i = 0; i < scheduleBo.getReminderBOs().size(); i++) {
//					ReminderBO reminderBO = scheduleBo.getReminderBOs().get(i);
//					ReminderEntity reminderEntity = new ReminderEntity();
//					reminderEntity.setAlertType(reminderBO.getAlertType());
//					if (reminderBO.getEndDate() != null)
//						reminderEntity.setEndDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndDate()));
//					if (reminderBO.getEndRepeatDate() != null)
//						reminderEntity.setEndRepeatDate(
//								ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndRepeatDate()));
//					if (reminderBO.getRepeatDate() != null)
//						reminderEntity
//								.setRepeatDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getRepeatDate()));
//					reminderEntity.setRepeatType(reminderBO.getRepeatType());
//					try {
//						if (reminderBO.getScheduledDate() != null)
//							reminderEntity.setScheduledDate(
//									ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getScheduledDate()));
//						else
//							throw new SoccratesException(SoccratesExceptionCode.DATENOTSET);
//					} catch (Exception e) {
//						// TODO: handle exception
//						throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);
//					}
//					reminderEntity.setSecondAlert(reminderBO.getSecondAlert());
//					if (reminderBO.getStartDate() != null)
//						reminderEntity
//								.setStartDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getStartDate()));
//					reminderEntity.setUserId(scheduleEnity.getCoach());
//					reminderEntity.setScheduleEnity(scheduleEnity);
//					session.save(reminderEntity);
//					scheduleBo.getReminderBOs().get(i).setReminderId(reminderEntity.getReminderId());
////							reminderEntities.add(reminderEntity);
//
//				}
////						scheduleEnity.setReminderEntity(reminderEntities);
//
//			} else {
//				throw new SoccratesException(SoccratesExceptionCode.REMINDERNOTSET);
//			}
//
//			session.getTransaction().commit();

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			throw new SoccratesException(
					"Team Name : " + reminderBO.getAlertType() + " already exist, please try with",
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
		return reminderBO;

	}

	public ReminderBO updateReminder(ReminderBO reminderBO) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createTeam()", reminderBO);
//			ScheduleEntity scheduleEnity = new ScheduleEntity();
//			scheduleEnity.setCreatedDate(new Date());
//			scheduleEnity.setPlaceName(scheduleBo.getPlaceName());
//			scheduleEnity.setScore(scheduleBo.getScore());
//			scheduleEnity.setScheduleType(scheduleBo.getScheduleType());
//			scheduleEnity.setTitleName(scheduleBo.getTitleName());
//			scheduleEnity.setComments(scheduleBo.getComments());
//			scheduleEnity.setUniformColor(scheduleBo.getUniformColor());
			if (reminderBO.getReminderId() > 0) {

				ReminderEntity reminderEntity = (ReminderEntity) session.get(ReminderEntity.class, reminderBO.getReminderId());
				if (reminderEntity != null )
					{
					 
				reminderEntity.setAlertType(reminderBO.getAlertType());
				if (reminderBO.getEndDate() != null)
					reminderEntity.setEndDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndDate()));
				if (reminderBO.getEndRepeatDate() != null)
					reminderEntity.setEndRepeatDate(
							ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndRepeatDate()));
				if (reminderBO.getRepeatDate() != null)
					reminderEntity
							.setRepeatDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getRepeatDate()));
				reminderEntity.setRepeatType(reminderBO.getRepeatType());
				if (reminderBO.getEndRepeatDate() != null)
					reminderEntity
							.setEndRepeatDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getRepeatDate()));
				
				try {
					if (reminderBO.getScheduledDate() != null)
						reminderEntity.setScheduledDate(
								ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getScheduledDate()));
					else
						throw new SoccratesException(SoccratesExceptionCode.DATENOTSET);
				} catch (Exception e) {
					// TODO: handle exception
					throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);
				}
				reminderEntity.setSecondAlert(reminderBO.getSecondAlert());
				if (reminderBO.getStartDate() != null)
					reminderEntity
							.setStartDate(ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getStartDate()));
//				reminderEntity.setUserId(coachEntity.getCoach());
//				reminderEntity.setScheduleEnity(coachEntity);
				session.save(reminderEntity);
				session.getTransaction().commit();
//				reminderBO.setReminderId(reminderEntity.getReminderId());
//						reminderEntities.add(reminderEntity);
//
				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}
			 

		 
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + reminderBO.getAlertType() + " already exist, please try with",
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
		return reminderBO;

	}


	public ScheduleBO updateSchedule(ScheduleBO scheduleBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createTeam()", scheduleBo);
			ScheduleEntity scheduleEnity = null;
			if (scheduleBo.getScheduleId() > 0)
				scheduleEnity = (ScheduleEntity) session.get(ScheduleEntity.class, scheduleBo.getScheduleId());
			else {
				throw new SoccratesException(SoccratesExceptionCode.SCHEDULENOTFOUND);
			}
			if (scheduleEnity != null) {
//				scheduleEnity.setCreatedDate(new Date());
				scheduleEnity.setPlaceName(scheduleBo.getPlaceName());
				scheduleEnity.setScheduleType(scheduleBo.getScheduleType());
				scheduleEnity.setScore(scheduleBo.getScore());
				scheduleEnity.setUniformColor(scheduleBo.getUniformColor());
				scheduleEnity.setTitleName(scheduleBo.getTitleName());
				scheduleEnity.setComments(scheduleBo.getComments());
				if (scheduleBo.getCoachId() > 0) {
					UserEntity coachEntity = (UserEntity) session.get(UserEntity.class, scheduleBo.getCoachId());
					if (coachEntity != null && coachEntity.getUserType() == ApplicationConstants.USERTYPE_COACH) {
						scheduleEnity.setCoach(coachEntity);
					} else {
						throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
					}

				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}
				if (scheduleBo.getTeamId() > 0) {
					TeamEntity teamEntity = (TeamEntity) session.get(TeamEntity.class, scheduleBo.getTeamId());
					if (teamEntity != null) {
						scheduleEnity.setTeamEntity(teamEntity);
					} else {
						throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
					}

				} else {
					throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
				}

				if (scheduleBo.getReminderBOs() != null) {
					for (int i = 0; i < scheduleBo.getReminderBOs().size(); i++) {
						if (scheduleBo.getReminderBOs().get(i).getReminderId() > 0) {
							for (int j = 0; j > scheduleEnity.getReminderEntity().size(); j++) {

								ReminderBO reminderBO = scheduleBo.getReminderBOs().get(i);

								ReminderEntity reminderEntity = scheduleEnity.getReminderEntity().get(j);
								if (reminderEntity.getReminderId() == reminderBO.getReminderId()) {
									reminderEntity.setAlertType(reminderBO.getAlertType());
									reminderEntity.setSecondAlert(reminderBO.getSecondAlert());
									reminderEntity.setUserId(scheduleEnity.getCoach());
									reminderEntity.setScheduleEnity(scheduleEnity);
									reminderEntity.setRepeatType(reminderBO.getRepeatType());

									if (reminderBO.getEndDate() != null)
										reminderEntity.setEndDate(
												ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getEndDate()));
									if (reminderBO.getEndRepeatDate() != null)
										reminderEntity.setEndRepeatDate(ApplicationConstants.DATE_WITH_TIME
												.parse(reminderBO.getEndRepeatDate()));
									if (reminderEntity.getRepeatDate() != null)
										reminderEntity.setRepeatDate(
												ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getRepeatDate()));
									try {
										if (reminderBO.getScheduledDate() != null)
											reminderEntity.setScheduledDate(ApplicationConstants.DATE_WITH_TIME
													.parse(reminderBO.getScheduledDate()));
										else
											throw new SoccratesException(SoccratesExceptionCode.DATENOTSET);
									} catch (Exception e) {
										// TODO: handle exception
										throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);
									}
									if (reminderBO.getStartDate() != null)
										reminderEntity.setStartDate(
												ApplicationConstants.DATE_WITH_TIME.parse(reminderBO.getStartDate()));

								}
								session.update(reminderEntity);

							}
						} else {
							throw new SoccratesException(SoccratesExceptionCode.REMINDERNOTSET);
						}
					}

				}
				session.update(scheduleEnity);
				session.getTransaction().commit();

			} else {
				throw new SoccratesException(SoccratesExceptionCode.SCHEDULENOTFOUND);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + scheduleBo.getScheduleType() + " already exist, please try with",
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
		return scheduleBo;

	}

	public ScheduleListBO getScheduleByDateUserId(ScheduleSearchBO scheduleSearchBO) throws SoccratesException {
		ScheduleListBO scheduleListBO = new ScheduleListBO();
		Session session = UtilitySession.getSession();
		session.beginTransaction();

		try {
			Query query = null;

			long userId = 0;
			Date queryDate, startDate, endDate;

			if (scheduleSearchBO.getCoachId() > 0 || scheduleSearchBO.getPlayerId() > 0)
				if (scheduleSearchBO.getCoachId() > 0)
					userId = scheduleSearchBO.getCoachId();
				else
					userId = scheduleSearchBO.getPlayerId();
			else
				throw new SoccratesException(SoccratesExceptionCode.USERNOTFOUND);

			if (scheduleSearchBO.getStartDate() != null && scheduleSearchBO.getStartDate().trim().length() > 0)
				startDate = ApplicationConstants.DATE_WITH_TIME.parse(scheduleSearchBO.getStartDate());
			else
				throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);

			if (scheduleSearchBO.getEndDate() != null && scheduleSearchBO.getEndDate().trim().length() > 0)
				endDate = ApplicationConstants.DATE_WITH_TIME.parse(scheduleSearchBO.getEndDate());
			else
				throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);
			String query_str = " from ScheduleEntity where ";
			if (scheduleSearchBO.getPlayerId() > 0) {
				query_str += " teamEntity.teamId IN ( select team.teamId from TeamPlayerEntity where player.userId = :userId) and  ";
			}
			if (scheduleSearchBO.getCoachId() > 0)
				query_str += " coach.userId = :userId and ";
			query_str += " scheduleId IN (select scheduleEnity.scheduleId from ReminderEntity where ";
		
//			else
//				query_str+=  " userId.userId = :userId and ";
			query_str += " startDate between :startDate and :endDate )  ";
//			query_str += " 1=1)";
			query = session.createQuery(query_str);
//			if(scheduleSearchBO.getCoachId()>0)
			query.setLong("userId", userId);
//			if(scheduleSearchBO.getTeamId()>0)
//			query.setLong("teamId", scheduleSearchBO.getTeamId());
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);

			@SuppressWarnings("unchecked")
			List<ScheduleEntity> scheduleEntities = query.list();
			ArrayList<ScheduleBO> scheduleBOs = new ArrayList<>();
			for (ScheduleEntity scheduleEntity : scheduleEntities) {
				ScheduleBO scheduleBO = new ScheduleBO();
//				scheduleBO.setCoachId(scheduleEntity.getCoach().getCreatedBy().getUserId());
				scheduleBO.setPlaceName(scheduleEntity.getPlaceName());
				scheduleBO.setCreatedDate(ApplicationConstants.US_DATE_FORMAT.format(scheduleEntity.getCreatedDate()));
				scheduleBO.setScheduleId(scheduleEntity.getScheduleId());
				scheduleBO.setScheduleType(scheduleEntity.getScheduleType());
				scheduleBO.setScore(scheduleBO.getScore());
				scheduleBO.setTitleName(scheduleEntity.getTitleName());
				scheduleBO.setTeamId(scheduleEntity.getTeamEntity().getTeamId());
				scheduleBO.setComments(scheduleEntity.getComments());
				scheduleBO.setUniformColor(scheduleEntity.getUniformColor());
				List<ReminderBO> reminderBOs = new ArrayList<>();
				if (scheduleSearchBO.isLoadReminder())
				{
					String query_strReminder = " from  ReminderEntity where scheduleEnity.scheduleId = :schdeuleId";
					Query queryReminder = session.createQuery(query_strReminder);
					queryReminder.setLong("schdeuleId", scheduleBO.getScheduleId());
					List<ReminderEntity> reminderEnities = queryReminder.list();
					for (int i = 0; i < reminderEnities.size(); i++) 
					{

						ReminderEntity reminderEntity = reminderEnities.get(i);
//					if (reminderEntity.getUserId().getUserId() == scheduleSearchBO.getCoachId()
//							|| reminderEntity.getUserId().getUserId() == scheduleSearchBO.getPlayerId()||reminderEntity.getUserId().getCreatedBy()==null) 
						{
							ReminderBO reminderBO = new ReminderBO();
							reminderBO.setReminderId(reminderEntity.getReminderId());
							reminderBO.setAlertType(reminderEntity.getAlertType());
							if (reminderEntity.getEndDate() != null)
								reminderBO.setEndDate(
										ApplicationConstants.DATE_WITH_TIME.format(reminderEntity.getEndDate()));
							if (reminderEntity.getRepeatDate() != null)
								reminderBO.setRepeatDate(
										ApplicationConstants.DATE_WITH_TIME.format(reminderEntity.getRepeatDate()));
							reminderBO.setRepeatType(reminderEntity.getRepeatType());
							try {
								if (reminderEntity.getScheduledDate() != null)
									reminderBO.setScheduledDate(ApplicationConstants.DATE_WITH_TIME
											.format(reminderEntity.getScheduledDate()));

							} catch (Exception e) {
								// TODO: handle exception
								throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);
							}
							reminderBO.setSecondAlert(reminderEntity.getSecondAlert());
							if (reminderEntity.getStartDate() != null)
								reminderBO.setStartDate(
										ApplicationConstants.DATE_WITH_TIME.format(reminderEntity.getStartDate()));
							reminderBO.setUserId(reminderEntity.getUserId().getUserId());
							reminderBOs.add(reminderBO);
						}
					}
				scheduleBO.setReminderBOs(reminderBOs);
				}
				
				List<RsvpBO> rsvpBOs = new ArrayList<>();
		
				if (scheduleSearchBO.isLoadRsvp())
				{
					String query_strReminder = " from  RSVPEntity where scheduleEntity.scheduleId = :schdeuleId";
					Query queryReminder = session.createQuery(query_strReminder);
					queryReminder.setLong("schdeuleId", scheduleBO.getScheduleId());
					List<RSVPEntity> rsvpEntities = queryReminder.list();
					
					for (int i = 0; i < rsvpEntities.size(); i++) {
						RsvpBO rsvpBO = new RsvpBO();
						rsvpBO.copy(rsvpEntities.get(i));
						rsvpBOs.add(rsvpBO);
					}
				scheduleBO.setRsvpBOs(rsvpBOs);
				scheduleBOs.add(scheduleBO);
				}
			}

			scheduleListBO.setScheduleBOs(scheduleBOs);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + scheduleSearchBO.getScheduleId() + " already exist, please try with",
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

		return scheduleListBO;
	}

	public ScheduleBO getScheduleById(ScheduleSearchBO scheduleSearchBO) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		ScheduleBO scheduleBO = new ScheduleBO();
		scheduleBO.setScheduleId(scheduleBO.getScheduleId());
		try {

			if (scheduleSearchBO.getScheduleId() > 0) {
				ScheduleEntity scheduleEntity = (ScheduleEntity) session.get(ScheduleEntity.class,
						scheduleSearchBO.getScheduleId());
				if (scheduleEntity == null) {
					throw new SoccratesException(SoccratesExceptionCode.SCHEDULENOTFOUND);
				} else {
					UserEntity coach = scheduleEntity.getCoach();
					scheduleBO.setScheduleId(scheduleEntity.getScheduleId());
					scheduleBO.setCoachId(coach.getUserId());
					scheduleBO.setCreatedDate(
							ApplicationConstants.DATE_WITH_TIME.format(scheduleEntity.getCreatedDate()));
					scheduleBO.setPlaceName(scheduleEntity.getPlaceName());
					scheduleBO.setScore(scheduleEntity.getScore());
					scheduleBO.setScheduleType(scheduleEntity.getScheduleType());
					scheduleBO.setTeamId(scheduleEntity.getTeamEntity().getTeamId());
					scheduleBO.setTitleName(scheduleEntity.getTitleName());
					scheduleBO.setComments(scheduleEntity.getComments());
					scheduleBO.setUniformColor(scheduleEntity.getUniformColor());
//					scheduleBO.set
					List<ReminderBO> reminderBOs = new ArrayList<>();
					for (int i = 0; i < scheduleEntity.getReminderEntity().size(); i++) {

						ReminderEntity reminderEntity = scheduleEntity.getReminderEntity().get(i);
//						if (reminderEntity.getUserId().getUserId() == scheduleSearchBO.getCoachId()
//								|| reminderEntity.getUserId().getUserId() == scheduleSearchBO.getPlayerId()) 
						{
							ReminderBO reminderBO = new ReminderBO();
							reminderBO.setAlertType(reminderEntity.getAlertType());
							if (reminderEntity.getEndDate() != null)
								reminderBO.setEndDate(
										ApplicationConstants.DATE_WITH_TIME.format(reminderEntity.getEndDate()));
							if (reminderEntity.getRepeatDate() != null)
								reminderBO.setRepeatDate(
										ApplicationConstants.DATE_WITH_TIME.format(reminderEntity.getRepeatDate()));
							reminderBO.setRepeatType(reminderEntity.getRepeatType());
							try {
								if (reminderEntity.getScheduledDate() != null)
									reminderBO.setScheduledDate(ApplicationConstants.DATE_WITH_TIME
											.format(reminderEntity.getScheduledDate()));

							} catch (Exception e) {
								// TODO: handle exception
								throw new SoccratesException(SoccratesExceptionCode.DATEFORMATEXCEPTION);
							}

							

							
							reminderBO.setSecondAlert(reminderEntity.getSecondAlert());
							if (reminderEntity.getStartDate() != null)
								reminderBO.setStartDate(
										ApplicationConstants.DATE_WITH_TIME.format(reminderEntity.getStartDate()));
							reminderBO.setUserId(reminderEntity.getUserId().getUserId());
							reminderBOs.add(reminderBO);
						}
					}
					List<RsvpBO> rsvpBOs = new ArrayList<>();
					if (scheduleSearchBO.isLoadRsvp())
						for (int i = 0; i < scheduleEntity.getRsvpEntity().size(); i++) {
							RsvpBO rsvpBO = new RsvpBO();
							rsvpBO.copy(scheduleEntity.getRsvpEntity().get(i));
							rsvpBOs.add(rsvpBO);
						}
					scheduleBO.setRsvpBOs(rsvpBOs);
					scheduleBO.setReminderBOs(reminderBOs);
				}
			}
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + scheduleBO.getScheduleId() + " already exist, please try with",
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
		return scheduleBO;

	}

	public RsvpBO createRsvp(RsvpBO scheduleBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {
			logger.entering(this.getClass().getName(), "createTeam()", scheduleBo);
			RSVPEntity scheduleEnity = new RSVPEntity();
			scheduleEnity.setCreatedDate(new Date());
			scheduleEnity.setRsvpType(scheduleBo.getRsvpType());
			scheduleEnity.setAttendance(scheduleBo.getAttendance());
			scheduleEnity.setCreatedDate(new Date());

			if (scheduleBo.getUserId() > 0) {

				UserEntity coachEntity = (UserEntity) session.get(UserEntity.class, scheduleBo.getUserId());
				if (coachEntity != null) {
					scheduleEnity.setPlayerId(coachEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.PLAYERNOTFOUND);
			}
			if (scheduleBo.getScheduleId() > 0) {

				ScheduleEntity teamEntity = (ScheduleEntity) session.get(ScheduleEntity.class,
						scheduleBo.getScheduleId());
				if (teamEntity != null) {
					scheduleEnity.setScheduleEntity(teamEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.SCHEDULENOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.SCHEDULENOTFOUND);
			}

			session.save(scheduleEnity);
			session.getTransaction().commit();
			scheduleBo.setRsvpId(scheduleEnity.getRsvpId());

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + scheduleBo.getUserId() + " already exist, please try with",
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
		return scheduleBo;

	}

	public RsvpBO updateRsvp(RsvpBO scheduleBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createTeam()", scheduleBo);
			RSVPEntity scheduleEnity = null;
			if (scheduleBo.getScheduleId() > 0)
				scheduleEnity = (RSVPEntity) session.get(RSVPEntity.class, scheduleBo.getRsvpId());
			else {
				throw new SoccratesException(SoccratesExceptionCode.SCHEDULENOTFOUND);
			}
			if (scheduleEnity != null) {
				scheduleEnity.setCreatedDate(new Date());
				scheduleEnity.setRsvpType(scheduleBo.getRsvpType());
				scheduleEnity.setAttendance(scheduleBo.getAttendance());
				scheduleEnity.setCreatedDate(new Date());

				session.update(scheduleEnity);
				session.getTransaction().commit();

			} else {
				throw new SoccratesException(SoccratesExceptionCode.RSVPNOTFOUND);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + scheduleBo.getRsvpId() + " already exist, please try with",
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
		return scheduleBo;

	}

}
