

<%@ page import="com.db.Database" %>
<%@ page import="java.util.*" %>

	<%
		HttpSession sessionUser = request.getSession(false);
		String userType = (String) sessionUser.getAttribute("user");
		if(sessionUser!=null && userType!=null && userType.equals("user")){
			String userName = (String) sessionUser.getAttribute("username");
			String userId = (String) sessionUser.getAttribute("userId");
			out.println("<script>alert('Welcome "+userName+"');</script>");
	%>
<html>
	<style>
          #container{
          	display: flex;
          	flex-direction: column;
          	position: fixed;
          	top:0;
          	bottom: 0;
          	left: 0;
          	right: 0;
          }
          .Outside-container{
          	   display:flex;
          	   flex:1;
          	   border-left: 2px solid black;
          	   flex-direction: row;
          }
          .inside-container{
          	   display: flex;
          	   flex:1;
          	   flex-direction: column;
          	   align-items: center;
          }
          .inside-container > div{
          		flex:1;
          }
          table,tbody,tr,th,td{
          	border:2px solid black;
          }
          table{
          	display: flex;
          	width:80%;
          }
          tbody{
          	display: flex;
          	flex:1;
          	flex-direction: column;
          }
          tr{
          	display: flex;
          	flex:1;
          	flex-direction: row;
          }
          tr:nth-child(1){
          	background: #93dcea;
          }
          th,td{
          	flex:1;
          }

          #idlist, #pricelist{
          	display: none;
          }
   </style>
   
  
   <body>
   		<div id='container'>
	        <div class="Outside-container">
	        	<div class="inside-container">
	        		<div>
	        		</div>
	        		<div>
		                <label>Select Item :</label>
						<div id='drop'>
							<%
								Database obj = new Database();
								out.println(obj.viewItems());

							%>
						</div>
					</div>
					<div>
						<label>Quantity</label>
						<div>
							<input type='number' id='quantity'>
						</div>
					</div>
					<div>
						<button onclick="addFunction()">Add</button>
					</div>
					<div>
					</div>
			</div>
			<div class="Outside-container" style="display: block;">
				<br><br>
				<div style="display: flex;flex-direction:column;align-items: center">
					<form action='./user/purchase' method='post'>
						<input  onclick='create()' type="submit" name="purchase" value="Purchase">
<!-- 						<input type='hidden' name='billamt' value='100'>
						<input type='hidden' name='length' value='1'>
						<input type='hidden' name='id0' value='5'> -->
					</form>
 					<button onclick="window.location.href = 'http://localhost:8080/webapp/user/purchasehistory.jsp'">View Purchase History</button>
				</div>
				<div style="display: flex;justify-content:center;">
					<h3>Total Price : Rs. 0</h3>
				</div>
				<br>
				<div style="display: flex;justify-content: center">
					<table>
						<tr>
							<th>Item Id </th>
							<th>Item Name</th>
							<th>Quantity</th>
							<th>Price</th>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<script>
			array = []
			item_id_list=[]
			quanity_array = []
			total_price = 0
		
			function addFunction(){
				select_tag = document.querySelector('#drop > select');
				item_name = select_tag.value;
				if(item_name==''){
					alert('Must Select Item name');
					return;
				}
				item_id_pos= select_tag.innerText.split('\n').indexOf(item_name)+1;
				query = '#idlist > div:nth-child('+item_id_pos+')';
				item_id = document.querySelector(query).innerText;

				query = '#pricelist > div:nth-child('+item_id_pos+')';
				item_price = document.querySelector(query).innerText;

				quantity = document.querySelector('#quantity').value;
				if (quantity==''){
					alert('Must Enter Quantity value');
					return;
				}
				if(array.includes(item_id_pos)){
					index = array.indexOf(item_id_pos);
					quanity_array[index]=parseInt(quantity)+parseInt(quanity_array[index]);
					query = '#container > div > div.Outside-container > div:nth-child(6) > table > tbody > tr:nth-child('+(index+2)+') > td:nth-child(3)'
					document.querySelector(query).innerText=quanity_array[index];
					item_price = parseInt(item_price)*parseInt(quanity_array[index]);
					query = '#container > div > div.Outside-container > div:nth-child(6) > table > tbody > tr:nth-child('+(index+2)+') > td:nth-child(4)'
					document.querySelector(query).innerText=item_price;
				}else{
					item_id_list.push(item_id);
					item_price = parseInt(item_price)*parseInt(quantity);
					array.push(item_id_pos);
					quanity_array.push(quantity);
					index = array.indexOf(item_id_pos);
					table_body = document.querySelector('#container > div > div.Outside-container > div:nth-child(6) > table > tbody');
					row_tag = '<table><tr><td>'+item_id+'</td><td>'+item_name+'</td><td>'+quanity_array[index]+'</td><td>'+item_price+'</td></tr></table>';
					parser = new DOMParser();
					doc = parser.parseFromString(row_tag,'text/html').getElementsByTagName('tr')[0];
					console.log(doc);
					table_body.appendChild(doc);
				}
				total =0
				for(i=0;i<array.length;i++){
					query='#container > div > div.Outside-container > div:nth-child(6) > table > tbody > tr:nth-child('+(i+2)+') > td:nth-child(4)';
					total+=parseInt(document.querySelector(query).innerText);
					query='#container > div > div.Outside-container > div:nth-child(4) > h3';
				}
				total_price=total;
				document.querySelector(query).innerText='Total Price : Rs.'+total;


			}
		</script>
		<script>
			function create(){
				query='#container > div > div.Outside-container > div:nth-child(3) > form';
				form=document.querySelector(query);


				input_tag = document.createElement('input');
				input_tag.setAttribute('type','hidden');
				input_tag.setAttribute('name','billamt');
				input_tag.setAttribute('value',''+total_price);
				form.appendChild(input_tag);

				input_tag = document.createElement('input');
				input_tag.setAttribute('type','hidden');
				input_tag.setAttribute('name','length');
				input_tag.setAttribute('value',''+array.length);
				form.appendChild(input_tag);

				for(i=0;i<array.length;i++){

					input_tag = document.createElement('input');
					input_tag.setAttribute('type','hidden');
					input_tag.setAttribute('name','id'+i);
					input_tag.setAttribute('value',''+item_id_list.at(i));
					form.appendChild(input_tag);

					input_tag = document.createElement('input');
					input_tag.setAttribute('type','hidden');
					input_tag.setAttribute('name','quantity'+i);
					input_tag.setAttribute('value',''+quanity_array.at(i));
					form.appendChild(input_tag);
				}
			}
		</script>
	</body>
	<%
		}else{
	%>
			<h3>Please Login <a href="http://localhost:8080/webapp/login.html">click</a></h3>
	<%
	}
	%>
</html>