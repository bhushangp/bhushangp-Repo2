package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.model.Cart;

@WebServlet("/IncreDecre")
public class IncreDecreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));

		List<Cart> list = (List<Cart>) request.getSession().getAttribute("cartlist");
		for (Cart c : list) {
			if (action != null ) {
				if (action.equals("inc") && c.getQuantity()>=1) {
					if (c.getPid() == id) {
						int inc = c.getQuantity() + 1;
						c.setQuantity(inc);
						response.sendRedirect("cart.jsp");
					}
				} else if (action.equals("dec") && c.getQuantity()>1) {
					if (c.getPid() == id) {
						int dec = c.getQuantity() - 1;
						c.setQuantity(dec);
						response.sendRedirect("cart.jsp");
					}else {break;}
					
				}
				
			}
		}
	

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
