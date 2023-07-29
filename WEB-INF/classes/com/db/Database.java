package com.db;

import java.sql.*;
import java.util.*;

public class Database{
		Connection conn;
		PreparedStatement stmt;


	public Database(){
		try{
			this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/application","sharmila", "sharmi");
       	}catch(Exception e){
       		System.out.println(e);
       	}

	}
	public String insertItems(String product,int quantity,int price,String expiryDate){
  

  		try{
  			String sql = "INSERT INTO items(ITEMNAME,QUANTITY, PRICE,DATE) VALUES(?,?,?,to_date(?,'YYYY-MM-DD'))";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,product);
            stmt.setInt(2,quantity);
            stmt.setInt(3,price);
            stmt.setString(4,expiryDate);
            stmt.executeUpdate();
            return "Added Successfully";
	    }catch(Exception e){
	    	System.out.println("error code");
	    	e.printStackTrace();
	    	return "Insert Failed!";
	    }

	}

	

	public String displayItems(){
		String result = "";

		try{
			String query="select * from items;";
			this.stmt = this.conn.prepareStatement(query);
			ResultSet rs = this.stmt.executeQuery(); 
		    result="<table id='items'><tr><th>itemid</th><th>itemname</th><th>quantity</th><th>price</th><th>Expiry Date</th></tr>";
			while (rs.next()){
				result+="<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getDate(5)+"</td>";
			}
			result+="</table>";
			return result;
		}catch(Exception e){
			return "<table></table>";
		}
	}

	public int getItemId(String itemName){
		try{
			System.out.println(itemName);
			String query = "select itemid from items where itemName=?";
			this.stmt = this.conn.prepareStatement(query);
			this.stmt.setString(1,itemName);
			ResultSet rs = this.stmt.executeQuery();
			if(rs.next()){
				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			}else{
				return -1;
			}
		}catch(Exception  e){
			System.out.println(e);
			return 0;
		}
	}
	
    public String viewItems()
    {
    	String query="SELECT * FROM ITEMS;";
    	String resultId = "<div id='idlist'>";
    	String resultPrice = "<div id='pricelist'>";
    	String result="<select name='itemName'>";
		try{
			this.stmt = this.conn.prepareStatement(query);
			ResultSet rs = this.stmt.executeQuery();
			
			while (rs.next()){
				result+="<option>"+rs.getString("ITEMNAME")+"</option>";
				resultId+="<div>"+rs.getInt(1)+"</div>";
				resultPrice+="<div>"+rs.getInt(4)+"</div>";
			}
			resultId+="</div>";
			resultPrice+="</div>";
			result+="</select>";
			return resultId+resultPrice+result;
		}catch(Exception e){
			return "<select>"+e+"</select>";
		}
    }


    public String updateItems(int quantity,String expiryDate,String itemName){
    	try{
    		String query;
    		if (quantity!=-1 && !expiryDate.equals("")){
		    	query = "update items set quantity=(select quantity from items where itemName=?)+?,date=to_date(?,'YYYY-MM-DD') where itemName=?;";
	    		this.stmt=this.conn.prepareStatement(query);
	    		this.stmt.setString(1,itemName);
	    		this.stmt.setInt(2,quantity);
	    		this.stmt.setString(3,expiryDate);
	    		this.stmt.setString(4,itemName);

    		}
    		else if (quantity!=-1){
    			query = "update items set quantity=(select quantity from items where itemName=?)+? where itemName=?";
	    		this.stmt=this.conn.prepareStatement(query);
	    		this.stmt.setString(1,itemName);
	    		this.stmt.setInt(2,quantity);
	    		this.stmt.setString(3,itemName);
    		}
    		else{
    			query = "update items set date=to_date(?,'YYYY-MM-DD') where itemName=?";
	    		this.stmt=this.conn.prepareStatement(query);
	    		this.stmt.setString(1,expiryDate);
	    		this.stmt.setString(2,itemName);
    		}
    		this.stmt.execute();
    		return "Updated Successfully!";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "Updation Failed!";
    	}
       
    }

    public String deleteItem(String itemName){
    	try{
    		String query = "delete from items where itemname=?";
    		this.stmt=this.conn.prepareStatement(query);
    		this.stmt.setString(1,itemName);
    		this.stmt.execute();
    		return "Deleted Successfully!";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "Deletion Failed!";
    	}
    }

    /*

	public String generateBill(String user){
		String query="SELECT * FROM ITEMS;";
		String result="<select>";
		try{
			this.stmt = this.conn.prepareStatement(query);
			ResultSet rs = this.stmt.executeQuery();
			while (rs.next()){
				result+="<option>"+rs.getString("ITEMNAME")+"</option>";
			}
			result+="</select>";
			return result;
		}catch(Exception e){
			return "<select></select>";
		}
	}

	public String addBill(String user, String itemname ,int quantity){
		boolean flag=false;
		try{
			String sql = "select quantity from products  where itemname=?;";
			this.stmt = this.conn.prepareStatement(sql);
            this.stmt.setString(1,itemname);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int quantity2=rs.getInt(1);
            System.out.println("***"+quantity2);
            if(quantity>0 && quantity2>=quantity){
         
					String query = String.format("insert into purchasedetails values((select id from users where username='%s'),'%s',%d,(select %d*price from products where itemname='%s'))", user, itemname, quantity,quantity, itemname);
					this.stmt = this.conn.prepareStatement(query);
					this.stmt.executeUpdate();
					query=String.format("update products set quantity=(select quantity from products where itemname='%s')-%d  where itemname='%s'",itemname, quantity,itemname);
					System.out.println(query);
					this.conn.prepareStatement(query).executeUpdate();
					flag=true;
			}else{
				return "Insufficient quantity";
			}
			    return "added Successfully..!!";
		    }catch(Exception e){
			    return e+"";
		}
	}
	*/
}