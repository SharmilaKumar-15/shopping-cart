package com.controller.user;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.db.PurchaseData;

@WebServlet("/user/purchase")
public class Purchase extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			PrintWriter out = res.getWriter();
			HttpSession sessionUser = req.getSession(false);
			String userType = (String) sessionUser.getAttribute("user");
			if(sessionUser!=null && userType!=null && userType.equals("user")){
				String userName = (String) sessionUser.getAttribute("username");
				Integer userId = Integer.parseInt((String) sessionUser.getAttribute("userId"));

				int billAmt = Integer.parseInt(req.getParameter("billamt"));
				int purchaseSize = Integer.parseInt(req.getParameter("length"));

				ArrayList<Integer> itemIdList = new ArrayList<>();
				ArrayList<Integer> quantityList = new ArrayList<>();

				for(int i=0;i<purchaseSize;i++){
					String idParam = "id"+i, quantityParam="quantity"+i;
					int itemId = Integer.parseInt(req.getParameter(idParam));
					int quantity = Integer.parseInt(req.getParameter(quantityParam));
					itemIdList.add(itemId);
					quantityList.add(quantity);
				}

				PurchaseData purchaseData = new PurchaseData();
				String result = purchaseData.purchaseProduct(userId, billAmt, itemIdList, quantityList);
				out.println(result);
			}else{
				RequestDispatcher rd = req.getRequestDispatcher("./../login.html");
				rd.forward(req,res);
			}
		}catch(Exception e){
			e.printStackTrace();
			RequestDispatcher rd = req.getRequestDispatcher("./../login.html");
			rd.forward(req,res);
		}
	}
}