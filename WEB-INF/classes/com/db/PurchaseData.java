package com.db;

import java.sql.*;
import java.util.*;

public class PurchaseData{
	Connection conn;
	public static int billNumber=1;
	public static int invoiceNumber=1000;
	PreparedStatement stmt;
	public PurchaseData(){
		try{
			this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/application","sharmila", "sharmi");
       	}catch(Exception e){
       		System.out.println(e);
       	}
	}

	public String purchaseProduct(Integer userId, int billAmt, ArrayList<Integer> itemIdList, ArrayList<Integer> quantityList){
		try{
			String query;
			query = "insert into bill values(?,?,?,?)";
			this.stmt = this.conn.prepareStatement(query);
			this.stmt.setInt(1,PurchaseData.billNumber);
			PurchaseData.billNumber++;
			this.stmt.setInt(2,userId);
			this.stmt.setInt(3,PurchaseData.invoiceNumber);
			this.stmt.setInt(4,billAmt);
			this.stmt.executeUpdate();

			for(int i=0;i<itemIdList.size();i++){
				int itemid = itemIdList.get(i), quantity=quantityList.get(i);
				query = "insert into invoice values(?,?,?)";
				this.stmt = this.conn.prepareStatement(query);
				this.stmt.setInt(1,PurchaseData.invoiceNumber);
				this.stmt.setInt(2,itemid);
				this.stmt.setInt(3,quantity);
				this.stmt.executeUpdate();
			}
			PurchaseData.invoiceNumber++;
			return "Purchased Successfully!";
		}catch(Exception e){
			e.printStackTrace();
			return "Purchase Failed!";
		}
	}

	public ArrayList<ArrayList<Integer>> getUserBill(Integer userId){
		ArrayList<ArrayList<Integer>> bill = new ArrayList<ArrayList<Integer>>();

		try{
			String query;
			query = "select * from bill where userid=?;";
			this.stmt = this.conn.prepareStatement(query);
			this.stmt.setInt(1,userId);
			ResultSet rs = this.stmt.executeQuery();
			while(rs.next()){
				ArrayList<Integer> tempBill = new ArrayList<>();
				tempBill.add(rs.getInt(1));
				tempBill.add(rs.getInt(3));
				tempBill.add(rs.getInt(4));
				bill.add(tempBill);
			}
			return bill;
		}catch(Exception e){
			e.printStackTrace();
			return bill;
		}
	}

	public ArrayList<ArrayList<String>> getInvoice(Integer invoicenum){
		ArrayList<ArrayList<String>> invoice = new ArrayList<ArrayList<String>>();
		try{
			String query;
			query = " select t1.itemname, t2.quantity from items t1, invoice t2 where t1.itemid=t2.itemid and t2.invoicenum=?;";
			this.stmt = this.conn.prepareStatement(query);
			this.stmt.setInt(1,invoicenum);
			ResultSet rs = this.stmt.executeQuery();
			while(rs.next()){
				ArrayList<String> tempBill = new ArrayList<>();
				tempBill.add(rs.getString(1));
				tempBill.add(""+rs.getInt(2));
				invoice.add(tempBill);				
			}
			return invoice;
		}catch(Exception e){
			e.printStackTrace();
			return invoice;
		}
	}
}