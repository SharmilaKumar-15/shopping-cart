package com.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


@WebServlet("/logout")
public class Logout extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		session.invalidate();
		res.sendRedirect("http://localhost:8080/webapp/login.html");
	}
}