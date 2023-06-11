<%@page import="com.nt.dao.ProductDao"%>
<%@page import="com.nt.connection.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.*" %>
	<%@page import="com.nt.model.*" %>
	
	<% 
	User user=(User)request.getSession().getAttribute("user");
	if(user==null){
		response.sendRedirect("login.jsp");
	}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Page</title>
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
	
	<% List<Cart> pcartlist=(List<Cart>)request.getSession().getAttribute("cartlist"); %>
	<% ProductDao pDao=new ProductDao(DbCon.getDbCon()); 
		List<Cart> pcart=pDao.getCartProducts(pcartlist);
		int sum=0;
		if(pcart!=null){
		for(Cart c:pcart){
			sum+=c.getPrice()*c.getQuantity();
		}
		}
	%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="d-flex my-3">
			<h4>Total Price: $<%=sum %></h4>
			<a class="mx-3 btn btn-primary" href="CheckOut"><h6>Check Out</h6></a>
		</div>
		<table class="table ">
			<thead>
				<tr>
					<th>Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Buy Now</th>
					<th>Cancel</th>
				</tr>
			</thead>
			<%if(pcart!=null) {%>
			<tbody>
			<%for(Cart c:pcart){%>
				
				
			
			<tr>
			<td><%=c.getPname() %></td>
			<td><%=c.getCategory() %></td>
			<td>$<%=c.getPrice() %></td>
			<td>
			<form action="Order" method="post" class="form-inline">
			<input type="hidden"  name="id"  value="<%=c.getPid()%>" class="form-input">
			<div class="form-group d-flex justify-content-between w-50" >
			<a class="btn btn-sm btn-decre" href="IncreDecre?action=dec&id=<%=c.getPid()%>"><i class="fas fa-minus-square"></i></a>
			<input type="text" name="quantity" class="form-control w-50" value="<%=c.getQuantity() %>" readonly>
			<a class="btn btn-sm btn-incre" href="IncreDecre?action=inc&id=<%=c.getPid()%>"><i class="fas fa-plus-square"></i></a>
			</div>
			<button type="submit " class="btn btn-sm btn-primary w-20" >Buy</button>
			</form>
			</td>
			<td><a class="btn btn-sm btn-danger"href="RemoveCart?id=<%=c.getPid()%>">Remove</a></td>
			</tr>
			<%} %>
			</tbody>
<%} %>
		</table>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>