package com.soccrates.middletier.team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.soccrates.middlertier.mail.SendMail;
import com.soccrates.middletier.address.AddressEntity;
import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.user.UserBO;
import com.soccrates.middletier.user.UserEntity;
import com.soccrates.middletier.user.UserEntityHandler;
import com.soccrates.middletier.user.UserListBO;
import com.soccrates.middletier.util.AESencrp;
import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.UtilityLogger;
import com.soccrates.middletier.util.UtilitySession;

public class TeamEnityHandler {
	private static Logger logger = UtilityLogger.getLog(TeamEnityHandler.class.getSimpleName());

	public TeamBO createTeam(TeamBO userBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createTeam()", userBo);
			TeamEntity teamEntity = new TeamEntity();
			teamEntity.setAgeGroup(userBo.getAgeGroup());
			teamEntity.setGender(userBo.getGender());
			teamEntity.setTeamName(userBo.getTeamName());
			if (userBo.getTeamAddress() != null) {
				boolean addressPresent = false;
				AddressEntity addressEntity = new AddressEntity();
				if (userBo.getTeamAddress().getZipCode() != null
						&& userBo.getTeamAddress().getZipCode().trim().length() > 0)
					addressPresent = true;

				if (addressPresent) {
					addressEntity.copy(userBo.getTeamAddress());
					// session.save(addressEntity);
					teamEntity.setTeamAddress(addressEntity);
				}

			}
			if (userBo.getCoachId() > 0) {

				UserEntity coachEntity = (UserEntity) session.get(UserEntity.class, userBo.getCoachId());
				if (coachEntity != null && coachEntity.getUserType() == ApplicationConstants.USERTYPE_COACH) {
					teamEntity.setCoach(coachEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}
			session.save(teamEntity);
			session.getTransaction().commit();
			userBo.setTeamId(teamEntity.getTeamId());
			if (teamEntity.getTeamAddress() != null)
				userBo.getTeamAddress().setAddressId(teamEntity.getTeamAddress().getAddressId());

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + userBo.getTeamName() + " already exist, please try with",
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

	public TeamBO getTeamById(TeamSearchBO userBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		TeamBO teamBO = new TeamBO();
		try {

			if (userBo.getTeamId() > 0) {
				TeamEntity teamEntity = (TeamEntity) session.get(TeamEntity.class, userBo.getTeamId());
				if (teamEntity == null) {
					throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
				} else {
					UserEntity coach = teamEntity.getCoach();
					UserBO coachBO = new UserBO();
					coachBO.copy(coach, coach.getCompanyId().getSettingsId().getUrlPath());
					teamBO.setCoachId(coach.getUserId());
					teamBO.copy(teamEntity, coach.getCompanyId().getSettingsId().getUrlPath());
				}
			} else {
				throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + userBo.getTeamName() + " already exist, please try with",
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
		return teamBO;

	}

	public TeamListBO getPlayerTeamList(TeamSearchBO teamSearchBO) throws SoccratesException {
		TeamListBO teamListBO = new TeamListBO();
		ArrayList<TeamBO> teamBOs = new ArrayList<TeamBO>();

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			if (teamSearchBO.getPlayerId() > 0) {

				String listString = " from TeamPlayerEntity as r where player.userId =:userId";

				org.hibernate.Query query = session.createQuery(listString);
				query.setLong("userId", teamSearchBO.getPlayerId());
				@SuppressWarnings("unchecked")
				List<TeamPlayerEntity> retailers = query.list();

				for (TeamPlayerEntity data : retailers) {
					TeamBO teamBO = new TeamBO();
					teamBO.copy(data.getTeam(), data.getTeam().getCoach().getCompanyId().getSettingsId().getUrlPath());
					teamBOs.add(teamBO);
				}

				teamListBO.setTeamList(teamBOs);

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Coach Name : " + teamSearchBO.getCoachId() + " Not Present",
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
	public TeamListBO getTeamForCoachList(TeamSearchBO teamSearchBO) throws SoccratesException {
		TeamListBO teamListBO = new TeamListBO();
		ArrayList<TeamBO> teamBOs = new ArrayList<TeamBO>();

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {
			if (teamSearchBO.getCoachId() > 0) {
				String listString = " from TeamEntity as r where coach.userId =:userId";
				org.hibernate.Query query = session.createQuery(listString);
				query.setLong("userId", teamSearchBO.getCoachId());
				@SuppressWarnings("unchecked")
				List<TeamEntity> retailers = query.list();
				for (TeamEntity data : retailers) {
					TeamBO teamBO = new TeamBO();
					teamBO.copy(data, data.getCoach().getCompanyId().getSettingsId().getUrlPath());
					teamBOs.add(teamBO);
				}

				teamListBO.setTeamList(teamBOs);

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Coach Name : " + teamSearchBO.getCoachId() + " Not Present",
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


	public TeamListBO getTeamList(TeamSearchBO teamSearchBO) throws SoccratesException {
		TeamListBO teamListBO = new TeamListBO();
		ArrayList<TeamBO> teamBOs = new ArrayList<TeamBO>();

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			if (teamSearchBO.getCoachId() > 0) {

				String listString = " from TeamEntity as r where coach.userId =:userId";

				org.hibernate.Query query = session.createQuery(listString);
				query.setLong("userId", teamSearchBO.getCoachId());
				@SuppressWarnings("unchecked")
				List<TeamEntity> retailers = query.list();

				for (TeamEntity data : retailers) {
					TeamBO teamBO = new TeamBO();
					teamBO.copy(data, data.getCoach().getCompanyId().getSettingsId().getUrlPath());
					teamBOs.add(teamBO);
				}

				teamListBO.setTeamList(teamBOs);

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Coach Name : " + teamSearchBO.getCoachId() + " Not Present",
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
	public UserListBO getTeamPlayeList(TeamSearchBO teamSearchBO) throws SoccratesException {
		UserListBO userListBO = new UserListBO();
		ArrayList<UserBO> teamBOs = new ArrayList<UserBO>();

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			if (teamSearchBO.getTeamId() > 0) {

				String listString = " from TeamPlayerEntity as r where team.teamId =:teamId";

				org.hibernate.Query query = session.createQuery(listString);
				query.setLong("teamId", teamSearchBO.getTeamId());
				@SuppressWarnings("unchecked")
				List<TeamPlayerEntity> retailers = query.list();

				for (TeamPlayerEntity data : retailers) {
					UserBO teamBO = new UserBO();
					teamBO.copy(data.getPlayer(), data.getTeam().getCoach().getCompanyId().getSettingsId().getUrlPath());
//					teamBO.copy(data, data.getCoach().getCompanyId().getSettingsId().getUrlPath());
					teamBOs.add(teamBO);
				}

				userListBO.setUserBOs(teamBOs);

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Coach Name : " + teamSearchBO.getCoachId() + " Not Present",
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
		return userListBO;

	}

	public TeamPlayerBO inviteTeamPlayer(TeamPlayerBO teamPlayer) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			if (teamPlayer.getTeamId() > 0) {
				if (teamPlayer.getEmailId() != null && teamPlayer.getEmailId().trim().length() > 0) {
					TeamEntity teamEntity = (TeamEntity) session.get(TeamEntity.class, teamPlayer.getTeamId());
					if (teamEntity == null) {
						throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
					}
					TeamPlayerEntity teamPlayerEntity = new TeamPlayerEntity();
					teamPlayerEntity.setTeam(teamEntity);
					teamPlayerEntity.setActivated(false);
					teamPlayerEntity.setAcceptedInvitation(false);
					// Invite Players
					UserEntityHandler userEntityHandler = new UserEntityHandler();
					UserBO userBo = new UserBO();
					userBo.setLoginId(teamPlayer.getEmailId());
					userBo.setEmailId(teamPlayer.getEmailId());
					userBo.setFirstName(teamPlayer.getFirstName());
					userBo.setLastName(teamPlayer.getLastName());
					long userId = 0;
					try {
						userId = userEntityHandler.checkUniqueLoginId(userBo);
					} catch (SoccratesException e) {
						e.printStackTrace();
					}
					if (userId == 0) {
						UserEntity player = new UserEntity();
						player.setActive(false);
						player.setCompanyId(teamEntity.getCoach().getCompanyId());
						player.setCreatedBy(teamEntity.getCoach());
						player.setCreationDate(new Date());
						player.setEmailIdValid(false);
						player.setUserType(ApplicationConstants.USERTYPE_PLAYER);
						player.setPassword(AESencrp.encrypt("Welcome@123"));
						player.setEmailId(teamPlayer.getEmailId());
						player.setLoginId(teamPlayer.getEmailId());
						player.setFirstName(teamPlayer.getFirstName());
						player.setLastName(teamPlayer.getLastName());
						teamPlayerEntity.setPlayer(player);
						session.save(player);
						teamPlayer.setPlayerId(player.getUserId());
						String mailContent = (teamEntity.getCoach().getCompanyId().getSettingsId()
								.getCoachRegistrationContent());
						if (mailContent != null) {
							mailContent = mailContent.replace("#{name}",
									((userBo.getFirstName() != null) ? userBo.getFirstName() : "Soccrates User") + " "
											+ ((userBo.getLastName() != null) ? userBo.getLastName() : ""));
							mailContent = mailContent.replace("#{loginId}", player.getLoginId());
							mailContent = mailContent.replace("#{password}", "Welcome@123");
							mailContent = mailContent.replace("#{admin}", "Soccrates Admin");

							try {
								if (mailContent != null && mailContent.trim().length() > 0)
									SendMail.sendExceptionMail("Thank you for Registering on Soccrates", mailContent,
											userBo.getEmailId(), UtilitySession.getSession());
								;
							} catch (Exception e) {
								userBo.setMessage("User created successfully Mail not set");
							}
						}

					} else {

						UserEntity player = (UserEntity) session.get(UserEntity.class, userId);
						if (player != null && player.getUserType() == ApplicationConstants.USERTYPE_PLAYER)
							teamPlayerEntity.setPlayer(player);
						else {
							throw new SoccratesException(SoccratesExceptionCode.CANNOTTAGACOACH);
						}
					}

					session.save(teamPlayerEntity);
					teamPlayer.setTeamPlayerId(teamPlayerEntity.getTeamPlayerId());

					session.getTransaction().commit();
				} else {
					throw new SoccratesException(SoccratesExceptionCode.EMAILIDEMPTY);
				}
			} else {
				throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			throw new SoccratesException("Player Already added in Team", SoccratesExceptionCode.PLAYERTAGGEDTOTEAM);

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
		return teamPlayer;

	}

	 
}
