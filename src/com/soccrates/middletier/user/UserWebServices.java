package com.soccrates.middletier.user;

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

@Path("rs/webservice/user_ws")
public class UserWebServices {

	/** The logger. */
	private static Logger logger = UtilityLogger.getLog(UserWebServices.class.getSimpleName());

	UserEntityHandler userEnityHandler = new UserEntityHandler();

	@POST
	@Path("create_user")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO createUser(UserBO userBO) {

		try {
			logger.entering(this.getClass().getName(), "createUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			userBO = userEnityHandler.createUser(userBO);
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
	@Path("check_loginId")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO checkLoginId(UserBO userBO) {

		try {
			logger.entering(this.getClass().getName(), "checkUser()", new Object[] { userBO });
			logger.info("userBO" + userBO);

			if (userEnityHandler.checkUniqueLoginId(userBO) > 0) {
				userBO.setMessage(SoccratesExceptionCode.USERALREADYPRESENT_STRING);
//				userBO.setException(true);
			} else
				userBO.setMessage(SoccratesExceptionCode.USERNOTPRESENT_STRING);

		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		}
		return userBO;
	}

	@POST
	@Path("check_emailId")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO checkEmailId(UserBO userBO) {

		try {
			logger.entering(this.getClass().getName(), "checkEmailId()", new Object[] { userBO });

			logger.info("userBO" + userBO);

			if (userEnityHandler.checkUniqueEmailId(userBO.getEmailId()) > 0) {
				throw new SoccratesException(SoccratesExceptionCode.EMAILIDPRESENT);
			} else
				userBO.setMessage(SoccratesExceptionCode.EMAILIDEMPTY_STRING);

		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		}
		return userBO;
	}

	@POST
	@Path("login")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO login(UserBO userBO) {

		try {
			logger.entering(this.getClass().getName(), "checkEmailId()", new Object[] { userBO });

			logger.info("userBO" + userBO);

			userBO = userEnityHandler.login(userBO);

		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		}
		return userBO;
	}

	/**
	 * Method to getUserList.
	 *
	 * @param searchBO the search BO
	 * @return the user list
	 */
	// @POST
	// @Path("user_list")
	// @Consumes("application/json; charset=utf-8")
	// @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	// public UserList getUserList(UserSearchBO searchBO) {
	// UserList userList = new UserList();
	// try {
	//
	// userList = (UserList) userController.executeList(searchBO.getCallType(),
	// searchBO, false);
	//
	// } catch (SoccratesException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// userList.setListException(true);
	// userList.setListExceptionMessage(e.getMessage());
	// logger.log(Level.SEVERE, "Exception", e);
	// userList.setListExceptionId(e.getExceptionId());
	// }
	// return userList;
	//
	// }

	// @POST
	// @Path("reset_password")
	// @Consumes("application/json; charset=utf-8")
	// @Produces(MediaType.APPLICATION_JSON)
	// public ChangePasswordBO reset_password(ChangePasswordBO taxometryBO) {
	//
	// try {
	// logger.entering(this.getClass().getName(), "send_password_link()", new
	// Object[] { taxometryBO });
	// // authorize.authorize(
	// // changePasswordBO.getId(),
	// // (ApplicationPermissionConstant.CHANGE_PASSWORD)
	// // );
	//
	// // taxometryBO.setMessage(emailId);
	// // logger.info("changePasswordBO" + emailId);
	//
	// taxometryBO = (ChangePasswordBO)
	// userController.executeAction(ApplicationPermissionConstant.RESET_PASSWORD,
	// taxometryBO, ApplicationConstants.CACHE_REQUIRED_FALSE);
	//
	// } catch (TaxometryException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// logger.log(Level.SEVERE, "Exception", e);
	// taxometryBO.setExceptionId(e.getExceptionId());
	// taxometryBO.setIsException(true);
	// taxometryBO.setMessage(e.getMessage());
	//
	// }
	// return taxometryBO;
	//
	// }
	@POST
	@Path("update_user")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO updateUser(UserBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "updateUser()", new Object[] { userBO });
			logger.info("id" + userBO.getUserId());
			userBO = (UserBO) userEnityHandler.updateUser(userBO);
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			userBO.setException(true);
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setMessage(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			userBO.setException(true);
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userBO.setMessage(e.getMessage());

		}
		return userBO;

	}

	@POST
	@Path("get_user")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO getUser(UserBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "getUser()", new Object[] { userBO });

			logger.info("userBO" + userBO);

			userBO = (UserBO) userEnityHandler.getUserById(userBO);

		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());

		}
		return userBO;

	}

	/**
	 * Method to delete user.
	 *
	 * @param userBO the user BO
	 * @return the user BO
	 */
	@POST
	@Path("delete_user")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO deleteUser(UserBO userBO) {
		try {
			logger.entering(this.getClass().getName(), "deleteUser()", new Object[] { userBO });

			logger.info("userBO" + userBO);

			if (userEnityHandler.deleteUser(userBO)) {

			} else {
				throw new SoccratesException(SoccratesExceptionCode.INVALIDUSERNAMEORPASSWORD);

			}

		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());
			return userBO;
		}
		return userBO;

	}

	@POST
	@Path("getListOfPlayersForCoach")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserListBO getListOfPlayersForCoach(UserBO userBO) {
		UserListBO userListBO = new UserListBO();
		try {
			logger.entering(this.getClass().getName(), "deleteUser()", new Object[] { userBO });

			logger.info("userBO" + userBO);
			if (userBO.getCoachId() > 0)
				userListBO.setUserBOs(userEnityHandler.getListOfPlayersForCoach(userBO));

			else {
				throw new SoccratesException(SoccratesExceptionCode.INVALIDUSERNAMEORPASSWORD);

			}

		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(e.getExceptionId());
			userBO.setException(true);
			userBO.setMessage(e.getMessage());
			return userListBO;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, "Exception", e);
			userBO.setExceptionId(SoccratesExceptionCode.UNHANDLEDEXCEPION);
			userBO.setException(true);
			userBO.setMessage(e.getMessage());
			return userListBO;
		}
		return userListBO;

	}
	/**
	 * Change password.
	 *
	 * @param changePasswordBO the change password BO
	 * @return the change password BO
	 */
	// @POST
	// @Path("change_password")
	// @Consumes("application/json; charset=utf-8")
	// @Produces(MediaType.APPLICATION_JSON)
	// public ChangePasswordBO changePassword(ChangePasswordBO changePasswordBO) {
	// try {
	// logger.entering(this.getClass().getName(), "changePassword()", new Object[] {
	// changePasswordBO });
	// // authorize.authorize(
	// // changePasswordBO.getId(),
	// // (ApplicationPermissionConstant.CHANGE_PASSWORD)
	// // );
	//
	// logger.info("changePasswordBO" + changePasswordBO);
	//
	// changePasswordBO = (ChangePasswordBO) userController.executeAction(
	// ApplicationPermissionConstant.CHANGE_PASSWORD, changePasswordBO,
	// ApplicationConstants.CACHE_REQUIRED_FALSE);
	//
	// } catch (TaxometryException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// logger.log(Level.SEVERE, "Exception", e);
	// changePasswordBO.setExceptionId(e.getExceptionId());
	// changePasswordBO.setIsException(true);
	// changePasswordBO.setMessage(e.getMessage());
	//
	// }
	// return changePasswordBO;
	//
	// }

	// @POST
	// @Path("send_password_link")
	// @Consumes("application/json; charset=utf-8")
	// @Produces(MediaType.APPLICATION_JSON)
	// public TaxometryBO sendPasswordLink(TaxometryBO taxometryBO) {
	//
	// try {
	// logger.entering(this.getClass().getName(), "send_password_link()", new
	// Object[] { taxometryBO });
	// // authorize.authorize(
	// // changePasswordBO.getId(),
	// // (ApplicationPermissionConstant.CHANGE_PASSWORD)
	// // );
	//
	// // taxometryBO.setMessage(emailId);
	// // logger.info("changePasswordBO" + emailId);
	//
	// taxometryBO = (TaxometryBO) userController.executeAction(
	// ApplicationPermissionConstant.SEND_CHANGE_PASSWORD_LINK, taxometryBO,
	// ApplicationConstants.CACHE_REQUIRED_FALSE);
	//
	// } catch (TaxometryException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// logger.log(Level.SEVERE, "Exception", e);
	// taxometryBO.setExceptionId(e.getExceptionId());
	// taxometryBO.setIsException(true);
	// taxometryBO.setMessage(e.getMessage());
	//
	// }
	// return taxometryBO;
	//
	// }

}
