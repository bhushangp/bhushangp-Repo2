<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.nt.model.*"%>	
	
	
	
	<% 
	User user=(User)request.getSession().getAttribute("user");
	if(user!=null){
		response.sendRedirect("index.jsp");
	}%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>


<% String msg=(String)request.getAttribute("msg");
	if(msg=="no"){%>
		<h2 style="color:red;text-align:center">Enter valid Email / Password</h2>
	<% }%>



	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="LoginServlet" method="post">

					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="email" placeholder="Enter your email"
							required>
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="pwd" placeholder="**********" required>
					</div>
					<div class="text-center">
						<input type="submit" class="btn btn-primary" name="email"
							placeholder="Enter your email" required>
					</div>


				</form>
			</div>
		</div>
	</div>




	<%@include file="includes/footer.jsp"%>
</body>
</html>
