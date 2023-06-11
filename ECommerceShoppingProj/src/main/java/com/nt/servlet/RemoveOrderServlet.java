package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.connection.DbCon;
import com.nt.dao.OrderDao;


@WebServlet("/RemoveOrder")
public class RemoveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw= response.getWriter();
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			if(id!=0) {
				OrderDao oDao=null;
				try {
					 oDao=new OrderDao(DbCon.getDbCon());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				oDao.cancelOrder(id);
				
			}
			response.sendRedirect("orders.jsp");
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
