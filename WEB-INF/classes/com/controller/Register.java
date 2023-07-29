package com.controller;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.model.Shopping;

@WebServlet("/register")
public class Register extends HttpServlet 
{
		
		public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException 
		{
			Shopping obj=new Shopping();
            PrintWriter out = resp.getWriter();
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			String emailid=req.getParameter("emailid");
	        String phonenumber=req.getParameter("phonenumber");
			boolean fun=obj.registerUser(username,password,emailid,phonenumber);
	        if(fun){
	        	resp.sendRedirect("./login.html");
	        }else
		        out.println("User Already Exsistss");

	}



}
	