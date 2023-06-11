package com.nt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.connection.DbCon;
import com.nt.dao.UserDao;
import com.nt.model.User;

@WebServlet("/LoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");

		UserDao ud = null;
		HttpSession session = null;
		try {
			ud = new UserDao(DbCon.getDbCon());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		User user = ud.getUserDetails(email, pwd);

		if (user != null) {
			session = req.getSession();
			session.setAttribute("user", user);
			res.sendRedirect("index.jsp");
		} else {
			String msg = "no";
			req.setAttribute("msg", msg);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(req, res);
		}

	}

}
