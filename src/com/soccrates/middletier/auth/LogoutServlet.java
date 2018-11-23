package com.soccrates.middletier.auth;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
// TODO: Auto-generated Javadoc
/**
 * The Class LogoutServlet.
 */
public class LogoutServlet extends HttpServlet { 

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config)throws ServletException
	{ 

	} 

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet (HttpServletRequest req , HttpServletResponse res )throws IOException , ServletException
	{
		System.out.println("Logginout");

		//  doPost(req, res);
		req.getSession().invalidate();
		System.out.println("inside");
		res.getWriter().append("Logout Sucessfull");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost( HttpServletRequest req , HttpServletResponse res )throws IOException , ServletException
	{ 

		System.out.println("Logginout");
		//   res.setContentType("text/html;charset=UTF-8");
		//          HttpSession session=req.getSession(true);
		//   session=req.getSession(true);
		//   
		//  
		//
		//
		//     
		//  String strWhatToDo        = (req.getParameter("hidWhatToDo")!=null)?req.getParameter("hidWhatToDo"):""; 
		//  
		//
		//  
		//  
		//   if(strWhatToDo.equalsIgnoreCase("logout"))
		//  {   

		req.getSession().invalidate();
		System.out.println("inside");
		//   
		//   } 
		//   

	} 


}