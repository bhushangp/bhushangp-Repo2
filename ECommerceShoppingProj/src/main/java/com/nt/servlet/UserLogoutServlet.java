package com.nt.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLogoutServlet
 */
@WebServlet("/LogoutServlet")
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("user")!=null){
			
			request.getSession().removeAttribute("user");
			response.sendRedirect("index.jsp");
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
