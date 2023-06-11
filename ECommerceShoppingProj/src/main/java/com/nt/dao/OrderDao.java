package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nt.model.Order;
import com.nt.model.Product;

public class OrderDao {

	private static final String INSERT_ORDER = "INSERT INTO SYSTEM.ORDERS(O_ID,U_ID,QUANTITY,ODATE,P_ID) VALUES(OID_SEQ.NEXTVAL,?,?,?,?)";
	private static final String GET_ORDER = "SELECT * FROM SYSTEM.ORDERS WHERE U_ID=? ORDER BY ORDERS.O_ID DESC";
	private static final String CANCEL_ORDER = "DELETE FROM SYSTEM.ORDERS WHERE O_ID=?";

	Connection con = null;

	public OrderDao(Connection con) {
		this.con = con;
	}

	public boolean insertOrder(Order order) {
		boolean result = false;
		try (PreparedStatement ps = con.prepareStatement(INSERT_ORDER)) {

			ps.setInt(1, order.getUid());
			ps.setInt(2, order.getQuantity());
			ps.setString(3, order.getDate());
			ps.setInt(4, order.getPid());

			ps.executeUpdate();
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Order> getUserOrders(int id) {
		List<Order> olist = new ArrayList<Order>();

		try (PreparedStatement ps = con.prepareStatement(GET_ORDER)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order od = new Order();

				ProductDao pDao = new ProductDao(con);
				Product p = pDao.getProduct(rs.getInt("P_ID"));

				od.setPid(rs.getInt("P_ID"));
				od.setOid(rs.getInt("O_ID"));
				od.setDate(rs.getString("ODATE"));
				od.setPname(p.getPname());
				od.setCategory(p.getCategory());
				od.setQuantity(rs.getInt("QUANTITY"));
				od.setPrice(p.getPrice() * rs.getInt("QUANTITY"));
				olist.add(od);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist;
	}

	public void cancelOrder(int id) {
		try (PreparedStatement ps = con.prepareStatement(CANCEL_ORDER)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
