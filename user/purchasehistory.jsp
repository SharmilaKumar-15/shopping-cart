<%@ page import="com.db.PurchaseData" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<style>
		#history{
			display: flex;
			flex-direction: column;
			border: 3px solid black;
		}

		#history > tbody{
			display: flex;
			flex-direction: column;
			border: 2px solid black;
		}
		#history > tbody >tr{
			flex:1;
			display: flex;
			border: 1px solid black;
			align-items: center;
		}
		#history > tbody >tr>td{
			flex:1;
			border: 1px solid black;
			display: flex;
			justify-content: center;
		}
		#history > tbody > tr> th{
			flex:1;
			border: 1px solid black;
		}

		#container{
			
			top:0;
			bottom: 0;
			left: 0;
			right: 0;
			display: flex;
			margin: auto;
			justify-content: center;
		}
		.invoice{
			display: flex;
			margin-top: auto;
			margin-bottom: auto;
		}
	</style>
</head>
<body>
	<%
		PurchaseData obj = new PurchaseData();
		String userId =(String) request.getSession().getAttribute("userId");
		System.out.println(userId);
		ArrayList<ArrayList<Integer>> userBill= obj.getUserBill(Integer.parseInt(userId));
		ArrayList<ArrayList<ArrayList<String>>> userPurchase=new ArrayList<ArrayList<ArrayList<String>>>();
		for(ArrayList<Integer> i : userBill){
			int invoice = i.get(1);
			ArrayList<ArrayList<String>> tempInvoice = obj.getInvoice(invoice);
			userPurchase.add(tempInvoice);
		}
		//out.println(userBill);
		//out.println(userPurchase);
	%>
		<center><h1>Purchase History</h1></center>
		<table id='history'>
			<tr>
				<th>Bill Number</th>
				<th>Invoice Number</th>
				<th>Quantity</th>
			</tr>
	<%
		int index=0;
		for(ArrayList<Integer> tempBill:userBill){
	%>
			<tr>
				<td><%=tempBill.get(0)%></td>
				<td><button onclick="show('invoice'+<%=index%>)"><%=tempBill.get(1)%></button></td>
 				<td><%=tempBill.get(2)%></td>
			</tr>

	<%
			index++;
		}
	%>
		</table>
		<div id='container'>
	<%
		index=0;
		for(ArrayList<ArrayList<String>> outerInvoice : userPurchase){
			out.println("<table id='invoice"+index+"' class='invoice'>");
	%>
			<tr>
				<th>Item Name</th>
				<th>Quantity</th>
			<tr>
	<%
			for(ArrayList<String> innerInvoice : outerInvoice){
		%>
				<tr>
					<td><%=innerInvoice.get(0)%></td>
					<td><%=innerInvoice.get(1)%></td>
				</tr>
		<%
			}
	%>
	<%
			out.println("</table>");
			out.println("<script>document.getElementById('invoice"+index+"').style.display='none'</script>");
			index++;
		}

	%>
	</div>
	<script>
		function show(id){
			document.getElementById(id).style.display='block';
		}
	</script>
</body>
</html>