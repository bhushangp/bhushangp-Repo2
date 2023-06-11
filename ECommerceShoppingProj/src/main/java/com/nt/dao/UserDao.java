package com.nt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nt.model.User;

public class UserDao {
	private static final String SELECT_USER = "SELECT * FROM SYSTEM.USERS WHERE EMAIL=? AND PASSWORD=?";

	private Connection con;
	private ResultSet rs = null;

	public UserDao( Connection con) {
		this.con=con;
	}
	
	public User getUserDetails(String uemail, String pwd) {
		User us = null;
		try (PreparedStatement ps = con.prepareStatement(SELECT_USER)) {

			ps.setString(1, uemail);
			ps.setString(2, pwd);

			rs = ps.executeQuery();
			if (rs.next()) {
				
				us = new User();

				us.setUid(rs.getInt("u_id"));
				us.setUname(rs.getString("uname"));
				us.setEmail(rs.getString("email"));
				us.setPassword(rs.getString("password"));
				//System.out.println(us);
				}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return us;
	}
}
