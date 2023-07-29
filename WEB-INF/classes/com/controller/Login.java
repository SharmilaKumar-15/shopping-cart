package com.controller;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import com.model.Shopping;

@WebServlet("/login")
public class Login extends HttpServlet{
	
	private boolean checkUser(String user, String password){
		try{
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/application","sharmila", "sharmi");
            String query = "select username, password from users where username=? and password=?;";
        	PreparedStatement stmt = conn.prepareStatement(query);
        	stmt.setString(1,user);
        	stmt.setString(2,password);
        	ResultSet rs = stmt.executeQuery();
        	System.out.println(""+rs);
        	if(rs.next())
        		return true;
        	else
        		return false;
		}catch(Exception e){
			System.out.println(e+"");
			return false;
	     }
	 }
	
	private int getUserId(String user, String pass){
		try{
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/application","sharmila", "sharmi");
            String query = "select id from users where username=? and password=?;";
        	PreparedStatement stmt = conn.prepareStatement(query);
        	stmt.setString(1,user);
        	stmt.setString(2,pass);
        	ResultSet rs = stmt.executeQuery();
        	System.out.println(""+rs);
        	if(rs.next())
        		return rs.getInt(1);
        	else
        		return -1;			
		}catch(Exception e){
			return -1;
		}
	}
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
		Shopping obj=new Shopping();
		PrintWriter out = resp.getWriter();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String emailid=req.getParameter("emailid");
		HttpSession session = req.getSession();
        if(username.equals("Admin") && password.equals("Admin")){
        	session.setAttribute("user","admin");
        	RequestDispatcher rd = req.getRequestDispatcher("./admin/admin.jsp");
       		rd.forward(req, resp);
       		//resp.sendRedirect("./admin/admin.jsp");

        }else if(checkUser(username, password)){
			session.setAttribute("user","user");
			session.setAttribute("username",username);
			session.setAttribute("userId",""+getUserId(username, password));

			RequestDispatcher rd=req.getRequestDispatcher("./user/user.jsp");
			rd.forward(req, resp);

		}
	}
}