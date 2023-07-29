
<html>
     <%
          String result =(String) request.getAttribute("result");
          HttpSession sessionVar = (HttpSession)request.getSession(false);
          String user = (String) sessionVar.getAttribute("user");
          if (user!=null && user.equals("admin")){
     %>
   <style>
   	      display:flex;
   	      flex:center;

          
          form{
          	display: flex;

          }
          .Outside-container{
          	   
          	   display:flex;
          	   padding:4px;
          	   margin:auto;
          	   border:1px solid black;
          }
          .inside-container{
          	   display: flex;
          	   flex:1;

          }


   </style>


		    <body>
		    	<form action='http://localhost:8080/webapp/admin/admincrud' method="post">
					<div class="Outside-container">
						<input type='hidden' name='choice' value='1'>
				        <div class="inside-container">
							<label>Enter the itemname </label>
						    <input type="text" placeholder="item Name" name="itemname" id="itemName">
					    </div>

				        <div class="inside-container">
				        	<label>select the  quantity </label>
				        	<input id='quantity' name='quantity' type='number' min=1 max=100>
				        </div>

				        <div class="inside-container">
				            <label>Enter the price</label>
				            <input type="text" name='price' placeholder="Type here" id="price">
				        </div>

				        <div class="inside-container">
				             <label>Enter the expiry date</label>
				             <input  id="expiry" type="date" name="expiryDate">
				             <input type='submit' id='name' name='submit' value='Submit'>  
				        </div>

				    </div>
				</form>
<!-- 	<script>
		function clickId()
		{
			var itemName = document.getElementById('itemName').value;
			var itemQuantity = document.getElementById('quantity').value;
			var price=document.getElementById('price').value;
			var expiryDate = document.getElementById('expiry').value;
            console.log(expiryDate);
			data = {
					'choice':'1',
					'itemname':itemName,
					'quantity':itemQuantity,
					'price':price,
					'expiryDate':expiryDate
				   }
				url = 'http://localhost:8080/webapp/purchase';
				query=""
				for (key in data) {
					query += encodeURIComponent(key)+"="+encodeURIComponent(data[key])+"&";
				}
				query = query.slice(0,-1);
				http = new XMLHttpRequest();
				http.open('POST',url,true);
				http.setRequestHeader('Content-Type','application/x-www-form-urlencoded')

				http.onload=function(){
					alert(this.responseText);
					display();
				}
				http.send(query);
			alert("added sucessfully")
			location.reload()
		}
	</script> -->

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
