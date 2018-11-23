package com.soccrates.middletier.evaluation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.team.TeamEntity;
import com.soccrates.middletier.template.CategoryEntity;
import com.soccrates.middletier.template.TemplateEntity;
import com.soccrates.middletier.user.UserEntity;
import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.UtilityLogger;
import com.soccrates.middletier.util.UtilitySession;

public class EvaluationEnityHandler {

	private static Logger logger = UtilityLogger.getLog(EvaluationEnityHandler.class.getSimpleName());

	public EvaluationBO createEvaluation(EvaluationBO templateBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createEvaluation()", templateBO);
			EvaluationEntity evaluationEntity = new EvaluationEntity();
			evaluationEntity.setCreatedDate(new Date());
			evaluationEntity.seteValue(templateBO.geteValue());
//			evaluationEntity.setPublished(templateBO.isPublished());
//			evaluationEntity.setComments(templateBO.getComments());
			if (templateBO.getSuper_evaluationId() > 0) {

				SuperEvaluationEntity coachEntity = (SuperEvaluationEntity) session.get(SuperEvaluationEntity.class,
						templateBO.getSuper_evaluationId());
				if (coachEntity != null) {
					evaluationEntity.setSuperEvaluationEntity(coachEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.SUPEREVALUATIONNOTSET);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}
			if (templateBO.getCategoryId() > 0) {

				CategoryEntity categoryEntity = (CategoryEntity) session.get(CategoryEntity.class,
						templateBO.getCategoryId());
				if (categoryEntity != null) {
					evaluationEntity.setCategoryEntity(categoryEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.CATEGORYIDNOTSET);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}
			session.save(evaluationEntity);
			session.getTransaction().commit();
			templateBO.setEvaluationId(evaluationEntity.getEvaluationId());

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + templateBO.geteValue() + " already exist, please try with",
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

		return templateBO;

	}

	public SuperEvaluationBO createSuperEvaluation(SuperEvaluationBO templateBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createEvaluation()", templateBO);
			SuperEvaluationEntity evaluationEntity = new SuperEvaluationEntity();
			evaluationEntity.setCreationDate(new Date());
			evaluationEntity.setPublished(templateBO.isPublished());
			if (templateBO.isPublished()) {
				evaluationEntity.setPublishedDate(new Date());
				templateBO.setPublishedDate(
						ApplicationConstants.DATE_WITH_TIME.format(evaluationEntity.getPublishedDate()));

			}
			evaluationEntity.setComments(templateBO.getComments());
			if (templateBO.getCoachId() > 0) {

				UserEntity coachEntity = (UserEntity) session.get(UserEntity.class, templateBO.getCoachId());
				if (coachEntity != null && coachEntity.getUserType() == ApplicationConstants.USERTYPE_COACH) {
					evaluationEntity.setCoach(coachEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}
			if (templateBO.getPlayerId() > 0) {

				UserEntity playEntity = (UserEntity) session.get(UserEntity.class, templateBO.getPlayerId());
				if (playEntity != null && playEntity.getUserType() == ApplicationConstants.USERTYPE_PLAYER) {
					evaluationEntity.setPlayer(playEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.PLAYERNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.PLAYERNOTFOUND);
			}
			if (templateBO.getTeamId() > 0) {

				TeamEntity teamEntity = (TeamEntity) session.get(TeamEntity.class, templateBO.getTeamId());
				if (teamEntity != null) {
					evaluationEntity.setTeamEntity(teamEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.TEAMNOTFOUND);
			}
			if (templateBO.getTemplateId() > 0) {

				TemplateEntity playEntity = (TemplateEntity) session.get(TemplateEntity.class,
						templateBO.getTemplateId());
				if (playEntity != null) {
					evaluationEntity.setTemplateId(playEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.TEMPLATEISNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.TEMPLATEISNOTFOUND);
			}

			session.save(evaluationEntity);
			session.getTransaction().commit();
			templateBO.setSuper_evaluationId(evaluationEntity.getSuper_evaluationId());

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + templateBO.getCoachId() + " already exist, please try with",
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

		return templateBO;

	}

	public SuperEvaluationListBO getEvaluation(SuperEvaluationListBO templateBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {
//			if (templateBO.getTemplateId() == 0)
//				throw new SoccratesException(SoccratesExceptionCode.TEMPLATEISNOTFOUND);
//			boolean start = false;
			String queryStr = "from SuperEvaluationEntity  where teamEntity.teamId =:teamId ";
			if (templateBO.getCoachId() > 0) {
//				 
				queryStr += "and  coach.userId =:coachId ";
			}
			if (templateBO.getPlayerId() > 0) {
				queryStr += " and player.userId =:playerId ";
			}
			if (templateBO.getPublishedSearch() > 0) {
				queryStr += " and published =:published ";
			}
			if (templateBO.isPublished()) {
				queryStr += "  order by creationDate ";
			} else
				queryStr += "  order by publishedDate ";
			org.hibernate.Query query = session.createQuery(queryStr);
//			query.setLong("templateId", templateBO.getTemplateId());
			if (templateBO.getCoachId() > 0) {
				query.setLong("coachId", templateBO.getCoachId());
			}
			if (templateBO.getPlayerId() > 0) {
				query.setLong("playerId", templateBO.getPlayerId());
			}
			 
			if (templateBO.getTeamId() > 0) {
				query.setLong("teamId", templateBO.getTeamId());
			}
			if (templateBO.getPublishedSearch() > 0) {
				query.setBoolean("published", templateBO.isPublished());
			}

			@SuppressWarnings("unchecked")
			ArrayList<SuperEvaluationEntity> superEvaluations = (ArrayList<SuperEvaluationEntity>) query.list();
			ArrayList<SuperEvaluationBO> categoryBOs = new ArrayList<SuperEvaluationBO>();
			if (superEvaluations != null && superEvaluations.size() > 0) {
				for (int i = 0; i < superEvaluations.size(); i++) {
					SuperEvaluationBO superEvaluationBO = new SuperEvaluationBO();
					superEvaluationBO.copy(superEvaluations.get(i));
					ArrayList<EvaluationBO>evaluationBOs= new ArrayList<>();
					for (EvaluationEntity evaluationEntity : superEvaluations.get(i).getEvaluationEntities()) {
						EvaluationBO evaluationBO= new EvaluationBO();
						evaluationBO.copy(evaluationEntity);
						evaluationBOs.add(evaluationBO);
					}
					superEvaluationBO.setEvaluationBOs(evaluationBOs);
					categoryBOs.add(superEvaluationBO);
				}

			}
			templateBO.setSuperEvaluationBOs(categoryBOs);

			logger.entering(this.getClass().getName(), "createEvaluation()", templateBO);

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + templateBO.getCoachId() + " already exist, please try with",
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

		return templateBO;

	}

	public SuperEvaluationBO updateSuperEvaluation(SuperEvaluationBO templateBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createEvaluation()", templateBO);
			SuperEvaluationEntity evaluationEntity = (SuperEvaluationEntity) session.get(SuperEvaluationEntity.class,
					templateBO.getSuper_evaluationId());

			if (evaluationEntity != null) {
				evaluationEntity.setComments(templateBO.getComments());
				evaluationEntity.setPublished(templateBO.isPublished());
				if (templateBO.isPublished()) {
					evaluationEntity.setPublishedDate(new Date());
					templateBO.setPublishedDate(
							ApplicationConstants.DATE_WITH_TIME.format(evaluationEntity.getPublishedDate()));
				}

				session.update(evaluationEntity);
				session.getTransaction().commit();
//				templateBO.se(evaluationEntity.getEvaluationId());
			} else
				throw new SoccratesException(SoccratesExceptionCode.EVALUATIONIDNOTSET);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + templateBO.getSuper_evaluationId() + " already exist, please try with",
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

		return templateBO;

	}

	public EvaluationBO updateEvaluation(EvaluationBO templateBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createEvaluation()", templateBO);
			EvaluationEntity evaluationEntity = (EvaluationEntity) session.get(EvaluationEntity.class,
					templateBO.getEvaluationId());

			if (evaluationEntity != null) {
				evaluationEntity.seteValue(templateBO.geteValue());
//				evaluationEntity.setPublished(templateBO.isPublished());
				if (templateBO.getCategoryId() > 0) {
					CategoryEntity categoryEntity = (CategoryEntity) session.get(CategoryEntity.class,
							templateBO.getCategoryId());
					if (categoryEntity != null) {
						evaluationEntity.setCategoryEntity(categoryEntity);
					} else {
						throw new SoccratesException(SoccratesExceptionCode.CATEGORYIDNOTSET);
					}

				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}
				session.save(evaluationEntity);
				session.getTransaction().commit();
				templateBO.setEvaluationId(evaluationEntity.getEvaluationId());
			} else
				throw new SoccratesException(SoccratesExceptionCode.EVALUATIONIDNOTSET);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException("Team Name : " + templateBO.geteValue() + " already exist, please try with",
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

		return templateBO;

	}

	public SuperEvaluationBO getEvaluationById(SuperEvaluationBO userBO) throws SoccratesException {
		// TODO Auto-generated method stub
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createEvaluation()", userBO);
			SuperEvaluationEntity evaluationEntity = (SuperEvaluationEntity) session.get(SuperEvaluationEntity.class,
					userBO.getSuper_evaluationId());
			if (evaluationEntity == null)
				throw new SoccratesException(SoccratesExceptionCode.EVALUATIONIDNOTSET);
			else {
				userBO.copy(evaluationEntity);
				List<EvaluationBO> evaluationBOs = new ArrayList<>();
				for (EvaluationEntity iterable_element : evaluationEntity.getEvaluationEntities()) {
					EvaluationBO evaluationBO = new EvaluationBO();
					evaluationBO.copy(iterable_element);

					evaluationBOs.add(evaluationBO);
				}
				userBO.setEvaluationBOs(evaluationBOs);
			}

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + userBO.getSuper_evaluationId() + " already exist, please try with",
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
		return userBO;
	}
}
