package com.soccrates.middletier.team;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soccrates.middletier.user.UserListBO;
import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.UtilityLogger;

@Path("rs/team_ws")
public class TeamWebServices {

	/** The logger. */
	private static Logger logger = UtilityLogger.getLog(TeamWebServices.class.getSimpleName());

	TeamEnityHandler teamEntityHandler = new TeamEnityHandler();

	@POST
	@Path("create_team")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamBO createTeam(TeamBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.createTeam(userBO);
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
	@Path("getteambyid")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamBO getTeamById(TeamSearchBO teamSeachBO) {

		TeamBO teamBO = new TeamBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamSeachBO });
			logger.info("userBO" + teamSeachBO);

			teamBO = teamEntityHandler.getTeamById(teamSeachBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			teamBO.setExceptionId(e.getExceptionId());
			teamBO.setException(true);
			teamBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			teamBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			teamBO.setException(true);
			teamBO.setMessage(e.getMessage());

		}
		return teamBO;
	}
	 
	@POST
	@Path("getteamlist")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamListBO getTeamList(TeamSearchBO teamSeachBO) {
		TeamListBO teamListBO = new TeamListBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamSeachBO });
			logger.info("userBO" + teamSeachBO);

			teamListBO = teamEntityHandler.getTeamList(teamSeachBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			teamListBO.setExceptionId(e.getExceptionId());
			teamListBO.setException(true);
			teamListBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			teamListBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			teamListBO.setException(true);
			teamListBO.setMessage(e.getMessage());

		}
		return teamListBO;
	}
	@POST
	@Path("getplayerteamlist")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamListBO getPlayerTeamList(TeamSearchBO teamSeachBO) {
		TeamListBO teamListBO = new TeamListBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamSeachBO });
			logger.info("userBO" + teamSeachBO);

			teamListBO = teamEntityHandler.getPlayerTeamList(teamSeachBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			teamListBO.setExceptionId(e.getExceptionId());
			teamListBO.setException(true);
			teamListBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			teamListBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			teamListBO.setException(true);
			teamListBO.setMessage(e.getMessage());

		}
		return teamListBO;
	}
	@POST
	@Path("getcoachteamlist")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamListBO getCoachTeamList(TeamSearchBO teamSeachBO) {
		TeamListBO teamListBO = new TeamListBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamSeachBO });
			logger.info("userBO" + teamSeachBO);

			teamListBO = teamEntityHandler.getTeamForCoachList(teamSeachBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			teamListBO.setExceptionId(e.getExceptionId());
			teamListBO.setException(true);
			teamListBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			teamListBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			teamListBO.setException(true);
			teamListBO.setMessage(e.getMessage());

		}
		return teamListBO;
	}
	
	@POST
	@Path("inviteplayer")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamPlayerBO invitePlayer(TeamPlayerBO teamPlayer) {
//		TeamListBO teamListBO = new TeamListBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamPlayer });
			logger.info("userBO" + teamPlayer);

			teamPlayer = teamEntityHandler.inviteTeamPlayer(teamPlayer);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			teamPlayer.setExceptionId(e.getExceptionId());
			teamPlayer.setException(true);
			teamPlayer.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			teamPlayer.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			teamPlayer.setException(true);
			teamPlayer.setMessage(e.getMessage());

		}
		return teamPlayer;
	}
	@POST
	@Path("getteamplayerlist")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserListBO getTeamPlayeList(TeamSearchBO teamSearchBO) {
		UserListBO userListBO = new UserListBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamSearchBO });
			logger.info("userBO" + teamSearchBO);

			userListBO = teamEntityHandler.getTeamPlayeList(teamSearchBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userListBO.setExceptionId(e.getExceptionId());
			userListBO.setException(true);
			userListBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Exception", e);
			e.printStackTrace();
			userListBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userListBO.setException(true);
			userListBO.setMessage(e.getMessage());

		}
		return userListBO;
	}


}
