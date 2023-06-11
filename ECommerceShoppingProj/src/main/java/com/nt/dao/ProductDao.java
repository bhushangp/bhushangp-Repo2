package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nt.model.Cart;
import com.nt.model.Product;

public class ProductDao {
	private static final String GET_PRODUCT="SELECT * FROM  PRODUCTS";
	private static final String GET_PRODUCT_BY_ID="SELECT * FROM  PRODUCTS WHERE PID=?";
	Connection con=null;
	
	public ProductDao(Connection con) {
		this.con=con;
	}

		public List<Product> getProductDetails() {
			
			List<Product> list= new ArrayList<Product>();
			
			try(PreparedStatement ps=con.prepareStatement(GET_PRODUCT);
				ResultSet rs=ps.executeQuery()){
				
				while(rs.next()) {
					Product pt=new Product();
					
					pt.setPid(rs.getInt("pid"));
					pt.setPname(rs.getString("pname"));
					pt.setPrice(rs.getInt("price"));
					pt.setCategory(rs.getString("category"));
					pt.setImage(rs.getString("image"));
					
					list.add(pt);
					
				}
			}
			catch(Exception e) {e.printStackTrace();}
			
			
			return list;	
		}
		public List<Cart> getCartProducts(List<Cart> cartlist){
			List<Cart> list= new ArrayList<Cart>();
			
			try(PreparedStatement ps=con.prepareStatement(GET_PRODUCT_BY_ID)){
				
				for(Cart p:cartlist) {
					ps.setInt(1, p.getPid());
					ResultSet rs=ps.executeQuery();

					while(rs.next()) {
						Cart ct=new Cart();
						
						ct.setPid(rs.getInt("pid"));
						ct.setPname(rs.getString("pname"));
						ct.setPrice(rs.getInt("price")*p.getQuantity());
						ct.setCategory(rs.getString("category"));
						ct.setQuantity(p.getQuantity());
						
						list.add(ct);
						
					}
				}
					
				}
				catch(Exception e) {e.printStackTrace();}
			
			return list;
			
		}
public Product getProduct(int id) {
			
			Product pt= null;
			
			try(PreparedStatement ps=con.prepareStatement(GET_PRODUCT_BY_ID)){
				
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					 pt=new Product();
					
					pt.setPid(rs.getInt("pid"));
					pt.setPname(rs.getString("pname"));
					pt.setPrice(rs.getInt("price"));
					pt.setCategory(rs.getString("category"));
					pt.setImage(rs.getString("image"));

					
				}
			}
			catch(Exception e) {e.printStackTrace();}
			
			
			return pt;	
		}
}
