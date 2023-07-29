<%@ page import="com.db.Database" %>

<html>
     <%
          String result =(String) request.getAttribute("result");
          HttpSession sessionVar = (HttpSession)request.getSession(false);
          String user = (String) sessionVar.getAttribute("user");
          if (user!=null && user.equals("admin")){
     %>
	<body>
		<form action='http://localhost:8080/webapp/admin/admincrud' method="post">
			<input type='hidden' name='choice' value='3'>
			<label>Select Item :</label>
			<div id='drop'>
				<%
					Database obj = new Database();
					out.println(obj.viewItems());
				%>
			</div>

			<input type='submit' name='submit' value='Delete'>
		</form>
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