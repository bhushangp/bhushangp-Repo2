package com.nt.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {

		private static Connection con=null;
		
		public static Connection getDbCon() throws SQLException, ClassNotFoundException {
			if(con==null) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "bhushan");
				
			}
			return con;
		}
}
