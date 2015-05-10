
/*
  @author T139 Group C
 */

/*
  Modified By : Arnabi Das and Sravanthi Adepu
  Reason : Password encryption has been implemented
  Date : 7 March,2012
 */
package com.tcs.ilp.telecomstore.module3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tcs.ilp.telecomstore.module3.dao.RetailerDAO;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;
import com.tcs.ilp.telecomstore.module3.log4j.Log4j;
import com.tcs.ilp.telecomstore.module3.model.LoginModel;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger _appLogger=Log4j.get_appLogger();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getSession().invalidate();
		request.getRequestDispatcher("/pages/Logout.jsp").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CryptoUtils ut=new CryptoUtils();
		String pwd1;
		String userid=request.getParameter("uid");
		String user_id=userid.trim();
		request.getSession().setAttribute("uid",user_id);//storing the userid in session
		String password=request.getParameter("pwd");
		try{
		pwd1 = ut.encryptPassword(password);
		System.out.println(pwd1);
		int uid=Integer.parseInt(user_id);
		LoginModel ob=null;
		ob=new LoginModel();
		
		String message=ob.validateRetailer(uid,pwd1); //validating the retailer
		
		   if(("success".equals(message)))
		   {
		
			request.getRequestDispatcher("/pages/Menu.jsp").forward(request, response);//For valid user menu will be displayed
			
		   }
		   if("error".equals(message))
		   {
		
		request.getRequestDispatcher("/pages/LoginError.jsp").forward(request, response);//For invalid users invalid login page will be displayed
		    }
	   }catch(UserExistsException e){
		   _appLogger.error("Exception" +e.getMessage());
		   //request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
		  
		   
	   } catch (Exception e) {
		// TODO Auto-generated catch block
		   _appLogger.error("Exception" +e.getMessage());
		   //  request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
	}

	}
}

