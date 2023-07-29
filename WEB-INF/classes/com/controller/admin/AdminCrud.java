package com.controller.admin;

import com.db.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin/admincrud")
public class AdminCrud extends HttpServlet{
		
	    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
	    try{
	        HttpSession sessionVar = (HttpSession)req.getSession(false);
		    String user = (String) sessionVar.getAttribute("user");
		    if (user!=null && user.equals("admin")){
				Integer ch = Integer.parseInt(req.getParameter("choice"));
				PrintWriter out=res.getWriter();
				Database obj=new Database();

				switch(ch){

					case 1:
						String itemName =(String) req.getParameter("itemname");
						int itemQuantity=Integer.parseInt(req.getParameter("quantity"));
						int price=Integer.parseInt(req.getParameter("price"));
						String expiryDate=(String)req.getParameter("expiryDate");
						String result = obj.insertItems(itemName,itemQuantity,price,expiryDate);
						req.setAttribute("result",result);
						RequestDispatcher rd=req.getRequestDispatcher("./admin.jsp");
						rd.forward(req, res);  
						break;

					case 2:
						String quantityString=req.getParameter("quantity");
					    int quantity=-1;
					    if(!quantityString.equals(""))
					    	quantity=Integer.parseInt(quantityString);
					    expiryDate=(String)req.getParameter("expiryDate");
					    itemName=(String)req.getParameter("itemName");
						result = obj.updateItems(quantity,expiryDate,itemName);
						req.setAttribute("result",result);
						rd=req.getRequestDispatcher("./admin.jsp");
						rd.forward(req, res); 
						break;

					case 3:
						itemName = (String) req.getParameter("itemName");
						result = obj.deleteItem(itemName);
						req.setAttribute("result", result);
						rd=req.getRequestDispatcher("./admin.jsp");
						rd.forward(req, res);					

					// case 4:
					// 	out.println(obj.generateBill(req.getParameter("user")));
					// 	break;
				}
			}else{
				RequestDispatcher rd = req.getRequestDispatcher("./../login.html");
				rd.forward(req,res);
			}
		}catch(NullPointerException e){
			RequestDispatcher rd = req.getRequestDispatcher("./../login.html");
			rd.forward(req,res);	
		}
	}
}