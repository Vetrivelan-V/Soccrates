package com.soccrates.middletier.user;

import java.nio.charset.Charset;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.soccrates.middletier.util.SoccratesExceptionCode;

@Path("rs/user_ws")
public class UserWebServicesCreate {

	UserWebServices userWebserice = new UserWebServices();

	@POST
	@Path("create_user")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO createUser(UserBO userBO) {
		return userWebserice.createUser(userBO);

	}

	@POST
	@Path("check_loginId")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO checkLoginId(UserBO userBO) {
		return userWebserice.checkLoginId(userBO);
	}

	@POST
	@Path("check_emailId")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO checkEmailId(UserBO userBO) {
		return userWebserice.checkEmailId(userBO);
	}
	
	@POST
	@Path("login")
	@Consumes("application/json; charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBO login(UserBO userBO , @Context HttpServletRequest request) {

		request.getHeader("Authorization");
		String userName = "";
		String password = "";
		final String authorization = request.getHeader("Authorization");

		if (authorization != null && authorization.startsWith("Basic")) {
			// Authorization: Basic base64credentials
			String base64Credentials = authorization.substring("Basic".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			// credentials = username:password
			final String[] values = credentials.split(":", 2);
			userName = values[0];
			password = values[1];
		}
		if(userName==null ||password==null)
		{
			userBO.setException(true);
			userBO.setExceptionId(SoccratesExceptionCode.INVALIDUSERNAMEORPASSWORD);
//			userBO.setMessage(SoccratesExceptionCode.);
			return userBO;
		}
		userBO.setLoginId(userName);
		userBO.setPassword(password);
		return userWebserice.login(userBO);
	}

}
