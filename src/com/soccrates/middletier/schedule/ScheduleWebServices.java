package com.soccrates.middletier.schedule;

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

@Path("rs/schedule")
public class ScheduleWebServices {

	/** The logger. */
	private static Logger logger = UtilityLogger.getLog(ScheduleWebServices.class.getSimpleName());

	ScheduleEnityHandler teamEntityHandler = new ScheduleEnityHandler();

	@POST
	@Path("update_schedule")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleBO updateTeam(ScheduleBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.updateSchedule(userBO);
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
	@Path("update_rsvp")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public RsvpBO updateRsvp(RsvpBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.updateRsvp(userBO);
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
	@Path("create_rsvp")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public RsvpBO createRsvp(RsvpBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.createRsvp(userBO);
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
	@Path("create_schedule")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleBO createSchedule(ScheduleBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.createSchedule(userBO);
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
	@Path("create_reminder")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public ReminderBO createReminder(ReminderBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.createReminder
					(userBO);
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
	@Path("update_reminder")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public ReminderBO updateReminder(ReminderBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.updateReminder
					(userBO);
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
	@Path("getschedulebyid")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleBO getScheduleById(ScheduleSearchBO search) {
		ScheduleBO userBO = new ScheduleBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.getScheduleById(search);
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
	@Path("getrsvpschedulebyid")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleBO getRSVPScheduleById(ScheduleSearchBO search) {
		ScheduleBO userBO = new ScheduleBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.getScheduleById(search);
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
	@Path("getScheduleByDateUserId")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleListBO getScheduleByDateUserId(ScheduleSearchBO search) {
		ScheduleListBO userBO = new ScheduleListBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.getScheduleByDateUserId(search);
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
