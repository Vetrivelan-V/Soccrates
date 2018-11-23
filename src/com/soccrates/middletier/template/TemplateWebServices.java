package com.soccrates.middletier.template;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.UtilityLogger;

@Path("rs/template_ws")
public class TemplateWebServices {

	/** The logger. */
	private static Logger logger = UtilityLogger.getLog(TemplateWebServices.class.getSimpleName());

	TemplateEnityHandler templateEnityHandler = new TemplateEnityHandler();

	@POST
	@Path("create_template")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TemplateBO createTemplate(TemplateBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.createTemplate(userBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			userBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		}
		return userBO;

	}
	@POST
	@Path("template_list")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TemplateListBO getTemplateListForCoach(TemplateBO userBO) {
		TemplateListBO templateListBO= new TemplateListBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			templateListBO = templateEnityHandler.getTemplateListForCoach(userBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			templateListBO.setExceptionId(e.getExceptionId());
			templateListBO.setException(true);
			templateListBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			templateListBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			templateListBO.setException(true);
			templateListBO.setMessage(e.getMessage());

		}
		return templateListBO;

	}

	@POST
	@Path("gettemplatebyid")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TemplateBO getTemplateById(TemplateBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.getTemplateById(userBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			userBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		}
		return userBO;

	}
	@POST
	@Path("update_template")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TemplateBO updateTemplate(TemplateBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "updateTempalte()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.updateTemplate(userBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			userBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		}
		return userBO;

	}

	@POST
	@Path("create_category")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public CategoryBO createCategory(CategoryBO templateBO) {

		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { templateBO });
			logger.info("userBO" + templateBO);

			templateBO = templateEnityHandler.createCategory(templateBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			templateBO.setExceptionId(e.getExceptionId());
			templateBO.setException(true);
			templateBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			templateBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			templateBO.setException(true);
			templateBO.setMessage(e.getMessage());

		}
		return templateBO;
	}

	@POST
	@Path("update_category")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public CategoryBO updateCategory(CategoryBO templateBO) {

		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { templateBO });
			logger.info("userBO" + templateBO);

			templateBO = templateEnityHandler.updateCategory(templateBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			templateBO.setExceptionId(e.getExceptionId());
			templateBO.setException(true);
			templateBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			templateBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			templateBO.setException(true);
			templateBO.setMessage(e.getMessage());

		}
		return templateBO;
	}

}
