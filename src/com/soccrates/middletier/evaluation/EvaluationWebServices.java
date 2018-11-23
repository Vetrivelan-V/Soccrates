package com.soccrates.middletier.evaluation;

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

@Path("rs/evaluation_ws")
public class EvaluationWebServices {

	/** The logger. */
	private static Logger logger = UtilityLogger.getLog(EvaluationWebServices.class.getSimpleName());

	EvaluationEnityHandler templateEnityHandler = new EvaluationEnityHandler();

	@POST
	@Path("create_evaluation")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public EvaluationBO createTemplate(EvaluationBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.createEvaluation(userBO);
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
	@Path("create_superevaluation")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public SuperEvaluationBO createSuperEvaluation(SuperEvaluationBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.createSuperEvaluation(userBO);
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
	@Path("update_superevaluation")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public SuperEvaluationBO updateSuperEvaluation(SuperEvaluationBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.updateSuperEvaluation(userBO);
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
	@Path("update_evaluation")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public EvaluationBO updateEvaluation(EvaluationBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "updateTempalte()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.updateEvaluation(userBO);
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
	@Path("getSuperEvaluation")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public SuperEvaluationListBO getEvaluation(SuperEvaluationListBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.getEvaluation(userBO);
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
	@Path("getEvaluationById")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public SuperEvaluationBO getEvaluationById(SuperEvaluationBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = templateEnityHandler.getEvaluationById(userBO);
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
}
