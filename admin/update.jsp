<%@ page import="com.db.Database" %>
<html>
     <%
          String result =(String) request.getAttribute("result");
          HttpSession sessionVar = (HttpSession)request.getSession(false);
          String user = (String) sessionVar.getAttribute("user");
          if (user!=null && user.equals("admin")){
     %>
	<style>
		#drop
		{
			
		}
	</style>

	<body>
			<form onsubmit="return check()" action='http://localhost:8080/webapp/admin/admincrud' method='post'>
				<input type='hidden' name='choice' value='2'>
				<div class="inside-container">
				 	<label>select the  quantity </label>
					<input name='quantity'  id='quantity' type='number' min=1 max=100>
				</div>
					        

				<div class="inside-container">
					<label>Enter the expiry date</label>
					<input  name="expiryDate" id='expiry' type="date" name="eday"> 
				</div>

				<label>Select Item :</label>
				<div id='drop'>
					<%
						Database obj = new Database();
						out.println(obj.viewItems());
					%>
				</div>
				<input type='submit' name='submit' value='Update'>
			</form>

			<script>

				function check()
				{
					quantity = document.getElementById('quantity').value;
					expiryDate = document.getElementById('expiry').value;
					selectedValue = document.getElementsByTagName('select')[0].value;

					if (quantity=="")
						quantity='-1';

					if(quantity=='-1' && expiryDate==''){
						alert('Choose for update!');
						return false;
					}
					return true;
				}
				
			</script> 

	</body>
     <%
          }else{
               out.println("<h1>You are not admin</h1>");
     %>
          <h3>Go to <a href="http://localhost:8080/webapp/login.html">Login</a></h3>
     <%
          }
     %>
</html>