<%@ page import='com.db.Database' %>

<html>
     <%
          String result =(String) request.getAttribute("result");
          HttpSession sessionVar = (HttpSession)request.getSession(false);
          String user = (String) sessionVar.getAttribute("user");
          if (user!=null && user.equals("admin")){
     %>
		<head>
			<link rel="stylesheet" type="text/css" href="http://localhost:8080/webapp/view.css">
		</head>
		<body>
			<script src='http://localhost:8080/webapp/selectionItem.js' ></script>
			<div id='full-header'>
				<div class='header'></div>
				<div class='header'></div>
				<div class='header'>
					<select id='itemname'>
						<option>select</option>
						<option>Equals</option>
						<option>Not Equals</option>
						<option>Contains</option>
						<option>Not Contains</option>
					</select>
					<input type='text'>
					<input type='text' style="visibility: hidden;">
				</div>

				<div class='header'>
					<select id='quantity'>
						<option>select</option>
						<option>Equals</option>
						<option>Not Equals</option>
						<option>Greater Than</option>
						<option>Lesser Than</option>
						<option>Between</option>
					</select>
					<input type='number'>
					<input type='number'>
				</div>

				<div class='header'>
					<select id='price'>
						<option>select</option>
						<option>Equals</option>
						<option>Not Equals</option>
						<option>Greater Than</option>
						<option>Lesser Than</option>
						<option>Between</option>
					</select>
					<input type='number'>
					<input type='number'>
				</div>

				<div class='header'>
					<select id='date'>
						<option>select</option>
						<option>Equals</option>
						<option>Before</option>
						<option>After</option>
					</select>
					<input type="date">
					<input type="number" style="visibility: hidden;">
				</div>
				<div class='header'>
					<button id='search'>Search</button>
				</div>
			</div>

			<br><br>
			<div id='content-body'>
				<div class='content' style="flex:1">
				</div>
				<div class="content" style="flex:5">
					<%
						Database obj = new Database();
						out.println(obj.displayItems());
					%>
				</div>
				<div class='content' style="flex:1">
				</div>			

			<script>
				function table_to_array(table_id) {
	                    myData = document.getElementById(table_id).rows
	                    my_liste = []
	                    for (var i = 0; i < myData.length; i++) {
	                            el = myData[i].children;
	                            my_el = []
	                            for (var j = 0; j < el.length; j++) {
	                                    my_el.push(el[j].innerText);
	                            }
	                            my_liste.push(my_el);

	                    }
	                    return my_liste;
	            }

	            function array_to_table(array){
	            	tableVar = '<table id="items"><tbody><tr><th>itemid</th><th>itemname</th><th>quantity</th><th>price</th><th>Expiry Date</th></tr>';
	            	for(let i of array){
	            		tableVar+='<tr>';
	            		for(let j of i){
	            			tableVar+='<td>'+j+'</td>';
	            		}
	            		tableVar+='</tr>';
	            	}
	            	tableVar+='</tbody></table>';
	            	parser = new DOMParser();
	            	doc = parser.parseFromString(tableVar, 'text/html');
	            	return doc.getElementById('items');
	            }

	            const array = table_to_array('items');
	     		array.shift();
	     		console.log(array);
			</script>
			<script src='http://localhost:8080/webapp/selectionFunction.js'></script>
		</body>
     <%
          }else{
               out.println("<h1>Please Login</h1>");
     %>
          <h3>Go to <a href="http://localhost:8080/webapp/login.html">Login</a></h3>
     <%
          }
     %>
</html>
