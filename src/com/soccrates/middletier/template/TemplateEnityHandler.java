package com.soccrates.middletier.template;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.user.UserEntity;
import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.UtilityLogger;
import com.soccrates.middletier.util.UtilitySession;

public class TemplateEnityHandler {

	private static Logger logger = UtilityLogger.getLog(TemplateEnityHandler.class.getSimpleName());

	public TemplateBO createTemplate(TemplateBO templateBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "templateBO()", templateBO);
			TemplateEntity templateEntity = new TemplateEntity();
			if (templateBO.getTemplateName() != null && templateBO.getTemplateName().trim().length() > 0)
				templateEntity.setTemplateName(templateBO.getTemplateName());
			else
				throw new SoccratesException(SoccratesExceptionCode.TEMPLATENAMECANNOTBENULL);

			if (templateBO.getCoachId() > 0) {

				UserEntity coachEntity = (UserEntity) session.get(UserEntity.class, templateBO.getCoachId());
				if (coachEntity != null && coachEntity.getUserType() == ApplicationConstants.USERTYPE_COACH) {
					templateEntity.setCoach(coachEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}
			session.save(templateEntity);
			session.getTransaction().commit();
			templateBO.setTemplateId(templateEntity.getTemplateId());

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + templateBO.getTemplateName() + " already exist, please try with",
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

	public CategoryBO createCategory(CategoryBO categoryBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "templateBO()", categoryBO);

			CategoryEntity categoryEntity = new CategoryEntity();
			if (categoryBO.getCategoryName() != null && categoryBO.getCategoryName().trim().length() > 0)
				categoryEntity.setCategoryName(categoryBO.getCategoryName());
			else
				throw new SoccratesException(SoccratesExceptionCode.CATEGORYNAMENOTSET);

			if (categoryBO.getParentId() > 0) {
				CategoryEntity parentCategory = (CategoryEntity) session.get(CategoryEntity.class,
						categoryBO.getParentId());
				if (parentCategory != null) {
					categoryEntity.setParentId(parentCategory);
					categoryEntity.setTemplateId(parentCategory.getTemplateId());
					session.save(categoryEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.CATEGORYPARENTNOTFOUND);
				}
			} else if (categoryBO.getTemplateId() > 0) {

				TemplateEntity templateEntity = (TemplateEntity) session.get(TemplateEntity.class,
						categoryBO.getTemplateId());
				if (templateEntity != null) {
					// templateEntity.getCriteria().add(categoryEntity);
					categoryEntity.setTemplateId(templateEntity);
					session.save(categoryEntity);
					session.update(templateEntity);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.TEMPLATEISNOTFOUND);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
			}

			session.getTransaction().commit();
			categoryBO.setCategoryId(categoryEntity.getCategoryId());

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			throw new SoccratesException(
					"Team Name : " + categoryBO.getCategoryName() + " already exist, please try with",
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

		return categoryBO;

	}

	public TemplateBO getTemplateById(TemplateBO templateBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "templateBO()", templateBO);
			if (templateBO.getTemplateId() > 0) {
				TemplateEntity templateEntity = (TemplateEntity) session.get(TemplateEntity.class,
						templateBO.getTemplateId());
				if (templateEntity != null) {
					templateBO.setTemplateName(templateEntity.getTemplateName());

					org.hibernate.Query query = session
							.createQuery("from CategoryEntity where templateId.templateId =:templateId and parentId is null");
					query.setLong("templateId", templateEntity.getTemplateId());

					@SuppressWarnings("unchecked")
					ArrayList<CategoryEntity> categoryEntities = (ArrayList<CategoryEntity>) query.list();
					ArrayList<CategoryBO> categoryBOs = new ArrayList<CategoryBO>();

					if (categoryEntities != null) {
						for (int i = 0; i < categoryEntities.size(); i++) {
							CategoryBO categoryBO = new CategoryBO();
							categoryBO.setCategoryId(categoryEntities.get(i).getCategoryId());
							categoryBO.setCategoryName(categoryEntities.get(i).getCategoryName());
							ArrayList<CategoryBO> subCategories = new ArrayList<CategoryBO>();
							for (int j = 0; j < categoryEntities.get(i).getSubCategories().size(); j++) {
								CategoryBO subCategory = new CategoryBO();
								subCategory.setParentId(categoryBO.getCategoryId());
								subCategory.setCategoryId(
										categoryEntities.get(i).getSubCategories().get(j).getCategoryId());
								subCategory.setCategoryName(
										categoryEntities.get(i).getSubCategories().get(j).getCategoryName());
								subCategories.add(subCategory);
							}
							categoryBO.setSubCategories(subCategories);
							categoryBOs.add(categoryBO);
						}
					}
					templateBO.setCriteria(categoryBOs);
				} else {
					throw new SoccratesException(SoccratesExceptionCode.CATEGORYPARENTNOTFOUND);
				}
				session.saveOrUpdate(templateEntity);
				session.getTransaction().commit();
			} else
				throw new SoccratesException(SoccratesExceptionCode.TEMPLATEISNOTFOUND);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + templateBO.getTemplateName() + " already exist, please try with",
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

	public CategoryBO updateCategory(CategoryBO categoryBO) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "templateBO()", categoryBO);
			if (categoryBO.getCategoryId() > 0) {
				CategoryEntity categoryEntity = (CategoryEntity) session.get(CategoryEntity.class,
						categoryBO.getCategoryId());
				if (categoryEntity != null) {
					if (categoryBO.getCategoryName() != null && categoryBO.getCategoryName().trim().length() > 0)
						categoryEntity.setCategoryName(categoryBO.getCategoryName());
					else
						throw new SoccratesException(SoccratesExceptionCode.CATEGORYNAMENOTSET);

				} else {
					throw new SoccratesException(SoccratesExceptionCode.CATEGORYPARENTNOTFOUND);
				}
				session.saveOrUpdate(categoryEntity);
				session.getTransaction().commit();
			} else
				throw new SoccratesException(SoccratesExceptionCode.CATEGORYIDNOTSET);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + categoryBO.getCategoryName() + " already exist, please try with",
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
		return categoryBO;
	}

	public TemplateBO updateTemplate(TemplateBO templateBO) throws SoccratesException {

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "templateBO()", templateBO);
			if (templateBO.getTemplateId() > 0) {
				TemplateEntity templateEntity = (TemplateEntity) session.get(TemplateEntity.class,
						templateBO.getTemplateId());
				if (templateEntity != null) {
					if (templateBO.getTemplateName() != null && templateBO.getTemplateName().trim().length() > 0)
						templateEntity.setTemplateName(templateBO.getTemplateName());
					else
						throw new SoccratesException(SoccratesExceptionCode.CATEGORYNAMENOTSET);

				} else {
					throw new SoccratesException(SoccratesExceptionCode.CATEGORYPARENTNOTFOUND);
				}
				session.saveOrUpdate(templateEntity);
				session.getTransaction().commit();
			} else
				throw new SoccratesException(SoccratesExceptionCode.TEMPLATEISNOTFOUND);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + templateBO.getTemplateName() + " already exist, please try with",
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

	public TemplateListBO getTemplateListForCoach(TemplateBO userBO) throws SoccratesException {
		TemplateListBO templateListBO = new TemplateListBO();
		
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "templateBO()", userBO);
			if (userBO.getCoachId() > 0) {
				String listString = " from TemplateEntity as r where coach.userId =:userId";
				org.hibernate.Query query = session.createQuery(listString);
				query.setLong("userId", userBO.getCoachId());
				@SuppressWarnings("unchecked")
				List<TemplateEntity> retailers = query.list();
				ArrayList<TemplateBO>templateBOs= new ArrayList<>();
				for (TemplateEntity data : retailers) {
					TemplateBO teamBO = new TemplateBO();
					teamBO.copy(data);
					templateBOs.add(teamBO);
				}

				templateListBO.setTemplateBOs(templateBOs);

			} else
				throw new SoccratesException(SoccratesExceptionCode.TEMPLATEISNOTFOUND);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"Team Name : " + userBO.getTemplateName() + " already exist, please try with",
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

		return templateListBO;
	}

}
