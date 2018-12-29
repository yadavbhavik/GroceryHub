<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Show Shop Request</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h1 align="center">Shop Request Details</h1><hr/>
			<table class="table table-hover">
      				<tr>
        				<th>Seller Name</th>
        				<th>Seller Mobile No</th>
        				<th>Alternative Mobile No</th>
        				<th>Email</th>
        				<th>Seller Address</th>
        				<th>Shop Name</th>
        				<th>Shop Address</th>
        				<th>Shop Category</th>
        				<th>Shop Mobile No</th>
        				<th>Shop Payment Method</th>
        				<th>Aadharcard No</th>
        				<th>Aadharcard Image</th>
        				<th>GST No</th>
        				<th>Pancard No</th>
        				<th>Pancard Image</th>
        				<th>Bank Account No</th>
        				<th>Seller Bank Holder Name</th>
        				<th>IFSC No</th>
        				<th>Status</th>
      				</tr>
      				
      				<%
      					try
      					{
      						Class.forName("com.mysql.jdbc.Driver");
      						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "");
      						Statement stmt = conn.createStatement();
      						String sql = "select * from sellerregistration";
      						ResultSet rs = stmt.executeQuery(sql);
      						while(rs.next())
      						{
      				%>
      	
      					<tr onclick="buttontrack(this)">
      						<td><%=rs.getString("sellername") %></td>
      						<td><%=rs.getString("sellermobileno") %></td>
      						<td><%=rs.getString("alternativemobileno") %></td>
      						<td><%=rs.getString("email") %></td>
      						<td><%=rs.getString("selleraddress") %></td>
      						<td><%=rs.getString("shopname") %></td>
      						<td><%=rs.getString("shopaddress") %></td>
      						<td><%=rs.getString("shopcategory") %></td>
      						<td><%=rs.getString("shopmobileno") %></td>
      						<td><%=rs.getString("shoppaymentmethod") %></td>
      						<td><%=rs.getString("aadharcardno") %></td>
      						<td><%=rs.getBlob("aadharcardimage") %></td>
      						<td><%=rs.getString("gstno") %></td>
      						<td><%=rs.getString("pancardno") %></td>
      						<td><%=rs.getBlob("pancardimage") %></td>
      						<td><%=rs.getString("bankaccountno") %></td>
      						<td><%=rs.getString("sellerbankholdername") %></td>
      						<td><%=rs.getString("ifscno") %></td>
      						<td><%=rs.getString("status") %></td>
      						<td><button class="btn btn-primary">Verified</button></td>
      					</tr>
      					
      				<% 
      					}
      						conn.close();
      					}
      					catch(Exception e)
      					{
      						e.printStackTrace();
      					}
      				%>
      				<script type="text/javascript">
      					 var id;
						function buttontrack(x) 
						{
							//var id = x.rowIndex;	
							//var url = "Shop_verify_by_admin?id="+id;
							//window.location.href = url;
							
							$(document).ready(function(){
								 id = x.rowIndex;
								$.ajax({
									type:"POST",
									url:"Shop_verify_by_admin",
									data:{sellerid:id,},
									success:function(responseText){
										var sellermob = JSON.parse(responseText);
										console.log(responseText);
										$("#modalmobile").html(sellermob.sellermobileno);
						
									}
								})
							});
							$("#myModal").modal("show");
						}
						
						$(document).on("click","#btnsend",function(){
							$.ajax({
								type:"POST",
								url:"send_login_details",
								data:{sellerid:id,},
								success:function(responseText){
									alert(responseText);
								}
							})
							$("#myModal").modal("hide");
							
						});
					</script>
					
      			</table>
      		</div>
      		
				  <!-- Modal -->
				  <div class="modal fade" id="myModal" role="dialog">
				    <div class="modal-dialog">
				    
				      <!-- Modal content-->
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Send Username And Password</h4>
				        </div>
				        <div class="modal-body">
				          <label>Seller Mobile No:</label><span>&nbsp;</span><label id="modalmobile"></label>
				        </div>
				        <div class="modal-footer">
				        	<input type="button" class="btn btn-info btn-lg" name="btnsend" id="btnsend" value="Send"> 
				          <button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">Close</button>
				        </div>
				      </div>
				      
				    </div>
				  </div>
				      		
      		</div>
  
		     	
</body>
</html>