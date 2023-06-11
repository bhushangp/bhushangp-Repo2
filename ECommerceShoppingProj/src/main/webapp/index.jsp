<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.nt.model.*"%>
<%@page import="com.nt.connection.*"%>
<%@page import="com.nt.dao.*"%>
<%@page import="java.util.*"%>

<%
ProductDao pd = null;
try {
	pd = new ProductDao(DbCon.getDbCon());
} catch (Exception e) {
%>
<%
e.printStackTrace();
}
%>
<%
List<Product> plist = pd.getProductDetails();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Shopping Cart!</title>
<%@include file="includes/header.jsp"%>
</head>
<body>

	<%@include file="includes/navbar.jsp"%>




	<div class="container">
		<!-- <div class="card w-100 mx-auto my-5"> -->
		<div class="card-header my-3">
			<h5>All Products</h5>
		</div>

		<div class="row">
			<%
			for (Product p : plist) {
				
			%>
			<div class="col-md-3 mx-auto my-3">

				<div class="card" style="width: 17rem;">

					<img class="card-img-top" src="product-images/<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getPname()%></h5>
						<h6 class="price">
							Price: $<%=p.getPrice()%></h6>
						<h6 class="category">
							Category:<%=p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="AddToCart?id=<%=p.getPid()%>" class="btn btn-dark">Add to Cart</a> <a
								href="Order?id=<%=p.getPid()%>&quantity=1" class="btn btn-primary">Buy Now</a>
						</div>

					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>