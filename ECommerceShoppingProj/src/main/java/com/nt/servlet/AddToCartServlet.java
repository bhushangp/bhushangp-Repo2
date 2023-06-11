package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.model.Cart;
import com.nt.model.Product;

@WebServlet("/AddToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			
			int id=Integer.parseInt(request.getParameter("id"));
			
		List<Cart> cartlist=new ArrayList<Cart>();

		List<Cart> clist=(List<Cart>) request.getSession().getAttribute("cartlist");
		
		Cart ct=new Cart();
		ct.setPid(id);
		ct.setQuantity(1);
		//pw.println(cartlist);
		
		if(request.getSession().getAttribute("user")!=null) {
			if(clist==null) {		
				cartlist.add(ct);
				request.getSession().setAttribute("cartlist", cartlist);
				//RequestDispatcher rd=request.getRequestDispatcher("/cart.jsp");
				//rd.forward(request, response);
				response.sendRedirect("cart.jsp");
			}
			else {
				cartlist=clist;
				boolean exist=false;
				for(Cart c:cartlist) {
					if(c.getPid()== id) {
						exist=true;
						pw.println("<h2 style='color:red;text-align:center'>Product already in the cart</h2>");
						pw.println("<a href='cart.jsp'><h2 style='color:blue;text-align:center'>Go Back to Cart</h2></a>");
					}	
				}
				if(!exist) {
					//ct.setPid(id);
					cartlist.add(ct);
					request.getSession().setAttribute("cartlist", cartlist);
					response.sendRedirect("index.jsp");
				}
			}
			for(Cart c:cartlist) {
				pw.println(c.getPid());
			}
		}
		else {response.sendRedirect("login.jsp");}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
