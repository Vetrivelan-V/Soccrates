package com.soccrates.middletier.message;

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

@Path("rs/message_ws")
public class MessageWebServices {
//
//	/** The logger. */
	private static Logger logger = UtilityLogger.getLog(MessageWebServices.class.getSimpleName());
//
	MessageEnityHandler teamEntityHandler = new MessageEnityHandler();
//
	@POST
	@Path("create_message")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBO createTeam(MessageBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = teamEntityHandler.createMessage(userBO);
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
//
	@POST
	@Path("getmessagebyid")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBO getTeamById(MessageBO teamBO) {

//		MessageBO teamBO = new TeamBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamBO });
			logger.info("userBO" + teamBO);

			teamBO = teamEntityHandler.getMessageById(teamBO);
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
//
	@POST
	@Path("getMessageList")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public MessageListBO getMessageListInbox(MessageSearchBO teamSeachBO) {
		MessageListBO teamListBO = new MessageListBO();
		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamSeachBO });
			logger.info("userBO" + teamSeachBO);

			teamListBO = teamEntityHandler.getMessageList(teamSeachBO);
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
 
//	@POST
//	@Path("getplayerteamlist")
//	@Consumes("application/json; charset=utf-8")
//	@Produces(MediaType.APPLICATION_JSON)
//	public TeamListBO getPlayerTeamList(TeamSearchBO teamSeachBO) {
//		TeamListBO teamListBO = new TeamListBO();
//		try {
//			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamSeachBO });
//			logger.info("userBO" + teamSeachBO);
//
//			teamListBO = teamEntityHandler.getPlayerTeamList(teamSeachBO);
//		} catch (SoccratesException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.log(Level.SEVERE, "Exception", e);
//			teamListBO.setExceptionId(e.getExceptionId());
//			teamListBO.setException(true);
//			teamListBO.setMessage(e.getMessage());
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.log(Level.SEVERE, "Exception", e);
//			e.printStackTrace();
//			teamListBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
//			teamListBO.setException(true);
//			teamListBO.setMessage(e.getMessage());
//
//		}
//		return teamListBO;
//	}
//	
//	@POST
//	@Path("inviteplayer")
//	@Consumes("application/json; charset=utf-8")
//	@Produces(MediaType.APPLICATION_JSON)
//	public TeamPlayerBO invitePlayer(TeamPlayerBO teamPlayer) {
////		TeamListBO teamListBO = new TeamListBO();
//		try {
//			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamPlayer });
//			logger.info("userBO" + teamPlayer);
//
//			teamPlayer = teamEntityHandler.inviteTeamPlayer(teamPlayer);
//		} catch (SoccratesException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.log(Level.SEVERE, "Exception", e);
//			teamPlayer.setExceptionId(e.getExceptionId());
//			teamPlayer.setException(true);
//			teamPlayer.setMessage(e.getMessage());
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.log(Level.SEVERE, "Exception", e);
//			e.printStackTrace();
//			teamPlayer.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
//			teamPlayer.setException(true);
//			teamPlayer.setMessage(e.getMessage());
//
//		}
//		return teamPlayer;
//	}
//	@POST
//	@Path("getteamplayerlist")
//	@Consumes("application/json; charset=utf-8")
//	@Produces(MediaType.APPLICATION_JSON)
//	public UserListBO getTeamPlayeList(TeamSearchBO teamSearchBO) {
//		UserListBO userListBO = new UserListBO();
//		try {
//			logger.entering(this.getClass().getName(), "createUser()", new Object[] { teamSearchBO });
//			logger.info("userBO" + teamSearchBO);
//
//			userListBO = teamEntityHandler.getTeamPlayeList(teamSearchBO);
//		} catch (SoccratesException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.log(Level.SEVERE, "Exception", e);
//			userListBO.setExceptionId(e.getExceptionId());
//			userListBO.setException(true);
//			userListBO.setMessage(e.getMessage());
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.log(Level.SEVERE, "Exception", e);
//			e.printStackTrace();
//			userListBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
//			userListBO.setException(true);
//			userListBO.setMessage(e.getMessage());
//
//		}
//		return userListBO;
//	}
//
//
}
