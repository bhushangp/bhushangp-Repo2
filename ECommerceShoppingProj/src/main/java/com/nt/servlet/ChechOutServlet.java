package com.nt.servlet;

import java.io.IOException;
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


@WebServlet("/CheckOut")
public class ChechOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			User user=(User) request.getSession().getAttribute("user");
			List<Cart> pcart=(List<Cart>) request.getSession().getAttribute("cartlist");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			long mil = System.currentTimeMillis();
			Date date= new Date(mil);
	
			if(user!=null && pcart!=null) {
				for(Cart c: pcart) {
					
					Order od=new Order();
					od.setOid(c.getPid());
					od.setUid(user.getUid());
					od.setPid(c.getPid());
					od.setQuantity(c.getQuantity());
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
					 		if(!result) break;
						}
				pcart.clear();
				response.sendRedirect("orders.jsp");
				
			}else {
				if(user==null)response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
