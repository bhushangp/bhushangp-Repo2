package com.nt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.model.Cart;

/**
 * Servlet implementation class RemoveCartServlet
 */
@WebServlet("/RemoveCart")
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		int id=Integer.parseInt(request.getParameter("id"));
		
		List<Cart> list = (List<Cart>) request.getSession().getAttribute("cartlist");
		
		if(list!=null) {
			for(Cart c:list) {
				if(c.getPid()==id) {
					list.remove(list.indexOf(c));
					break;	
				}
			}
			response.sendRedirect("cart.jsp");
		}else {response.sendRedirect("cart.jsp");}
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
