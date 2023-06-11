package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.connection.DbCon;
import com.nt.dao.OrderDao;
import com.nt.model.Cart;
import com.nt.model.Order;
import com.nt.model.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/Order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		int pid=Integer.parseInt(request.getParameter("id"));
		int pquantity=Integer.parseInt(request.getParameter("quantity"));
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		long mil = System.currentTimeMillis();
		Date date=new Date(mil);
		
		User user=(User) request.getSession().getAttribute("user");
		
		
		Order od= new Order();
		od.setPid(pid);
		od.setOid(pid);
		od.setUid(user.getUid());
		od.setQuantity(pquantity);
		od.setDate(sdf.format(date));
		
		OrderDao oDao=null;
		try {
			 oDao=new OrderDao(DbCon.getDbCon());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean result=oDao.insertOrder(od);
		
		
		if(result) {
			List<Cart> list = (List<Cart>) request.getSession().getAttribute("cartlist");
			
			if(list!=null) {
				for(Cart c:list) {
					if(c.getPid()==pid) {
						list.remove(list.indexOf(c));
						break;	
					}
				}
				response.sendRedirect("orders.jsp");
			}else {response.sendRedirect("cart.jsp");}
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
