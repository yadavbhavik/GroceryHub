<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script><title>Insert title here</title>
  
<title>Seller Login</title>
</head>
	<body>
		
		<form action="Seller_login_check">
			<div class="container" style="margin-top:20px">
			<div class="row">
			<div class="col-lg-4"><img alt="Logo" src="Image\GroceryHub.png" style="width:250px;height:100px"></img></div>
			<div class="col-lg-4"></div>
			<div class="col-lg-4" style="margin-top:60px"><ul align="center"><a href="Header.jsp">Home</a></ul></div>
			</div>
				<div class="row">
					<div class="col-lg-4"></div>
						<div class="col-lg-4">
							<h1 align="center">Seller Login</h1>
						</div>
				</div>
				<div class="row">
					<div class="col-lg-4"></div>
						<div class="col-lg-4">
							<div class="form-group">
								<label align="center">Enter user Name:</label>
								<input type="text" class="form-control" name="selleruname" id="selleruname" align="center">
							</div>
						</div>
				</div>
				<div class="row">
					<div class="col-lg-4"></div>
						<div class="col-lg-4">
							<div class="form-group">
								<label align="center">Enter password:</label>
								<input type="text" class="form-control" name="sellerpwd" id="sellerpwd" align="center">
							</div>
						</div>
				</div>
				<div class="row">
					<div class="col-lg-4"></div>
					<div class="col-lg-4">
						<div class="form-group">
							<button type="submit" class="form-control btn btn-primary" >Login</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4"></div>
					<div class="col-lg-4">
						<div class="form-group">
							<a href="Seller_registraction.jsp">Signup?</a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4"></div>
					<div class="col-lg-4">
						<div class="form-group">
							<a href="Seller_forgotpassword.jsp">Forgot password?</a>
						</div>
					</div>
				</div>
			</div>
		</form>	
	</body>
</html>