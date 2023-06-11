<%@page import="com.nt.dao.*"%>
<%@page import="com.nt.connection.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.*" %>
	<%@page import="com.nt.model.*" %>
	
	<% 
	User user=(User)request.getSession().getAttribute("user");
	List<Order> order=null;
	if(user!=null){
		OrderDao oDao= new OrderDao(DbCon.getDbCon());
		 order=oDao.getUserOrders(user.getUid());
		
	}
	
	else if(user==null){
		response.sendRedirect("login.jsp");
	}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Page</title>
<%@include file="includes/header.jsp"%>
<style type="text/css">
.table tbody td{
	vertical-align:middle;
}
.btn-incre, .btn-decre{
	box-shadow:none;
	font-size:25px;
}
</style>
	

</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">
			<h5>All Products</h5>
		</div>
		<table class="table ">
			<thead>
				<tr>
					<th>Date</th>
					<th>Name</th>
					<th>Category</th>
					<th>Quantity</th>
					<th>Price</th>		
					<th>Cancel</th>
				</tr>
			</thead>
			<%if(order!=null) {%>
			<tbody>
			<%for(Order o:order){%>
				
				
			
			<tr>
			<td><%=o.getDate() %></td>
			<td><%=o.getPname() %></td>
			<td><%=o.getCategory() %></td>
			<td><%=o.getQuantity() %></td>
			<td>$<%=o.getPrice() %></td>
			<td><a class="btn btn-sm btn-danger"href="RemoveOrder?id=<%=o.getOid()%>">Cancel</a></td>
			</tr>
			<%} %>
			</tbody>
<%} %>
		</table>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>