package com.tcs.ilp.telecomstore.module3.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LanguageController
 */
public class LanguageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanguageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
		String lang = (String) request.getParameter("Action");
		Locale locale = null;
		if (lang.equals("French")) {
			locale = Locale.FRANCE;
		}
		if (lang.equals("English")) {
			locale = Locale.US;
		}
		HttpSession session= request.getSession();
		session.setAttribute("myLocale", locale);
		ResourceBundle bundle = ResourceBundle.getBundle("Message", locale);

		for (Enumeration e = bundle.getKeys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String s = bundle.getString(key);
			session.setAttribute(key, s);
		}
		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
		rd.forward(request, response);

	}
	}


