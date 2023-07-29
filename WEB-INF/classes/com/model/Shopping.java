package com.model;

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



public class Shopping{
     String user="";
     String sql="";
     Connection c=null;
	PreparedStatement ps=null;

     public Shopping(){
          try{
               Class.forName("org.postgresql.Driver");

               c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/application","sharmila", "sharmi");   

               sql = "CREATE TABLE IF NOT EXISTS ITEMS";
               sql+= "(itemID BigSerial primary key ,";
               sql+= " ITEMNAME          VARCHAR(50)    NOT NULL, ";
               sql+= " QUANTITY            INT     NOT NULL, " ;
               sql+= " PRICE      INT,";
               sql+= "DATE DATE)";

               ps = c.prepareStatement(sql);
               ps.execute();

               sql = "CREATE TABLE if not exists users";
               sql+= "(ID BigSerial primary key," ;
               sql+= " username          VARCHAR(50) , " ;
               sql+= " password           varchar(10), " ;
               sql+= " emailid    varchar(50) unique,";
               sql+= "PHONENUMBER   varchar(10))";

               ps = c.prepareStatement(sql);
               ps.execute();

               sql = "CREATE TABLE if not exists INVOICE";
               sql+= "(INVOICENUM int ," ;
               sql+= " ITEMID    VARCHAR(50), ";
               sql+= " QUANTITY    INT)";

               ps = c.prepareStatement(sql);
               ps.execute();

               sql = "CREATE TABLE if not exists Bill";
               sql+= "(BILLNUM int ," ;
               sql+= " USERID    VARCHAR(50), " ;
               sql+= " INVOICE    INT, " ;
               sql+= " BILLAMOUNT       INT)";
               ps = c.prepareStatement(sql);
               ps.execute();
          }catch (Exception e) {
               e.printStackTrace();
               System.out.println(e);
               System.exit(0);
          }
     }


     public boolean registerUser(String username,String password,String emailid,String phonenumber){
          boolean flag = false;

          try{
               sql="insert into users(username,password,emailid,phonenumber) values (?,?,?,?);";
               ps=c.prepareStatement(sql);
               ps.setString(1,username);
               ps.setString(2,password);
               ps.setString(3,emailid);
               ps.setString(4,phonenumber);
               ps.execute();
               flag=true;
          }catch(Exception e){
               flag=false;
          }
          return flag;
     }


    public boolean loginUser(String username,String password,String emailid,String phonenumber){
          boolean flag = false;
       	String userPass = "";
          try
       	{
               String Query = "SELECT PASSWORD FROM USERS WHERE USERNAME=?;";
               PreparedStatement prepareStmt = c.prepareStatement(Query);
               prepareStmt.setString(1,username);
               ResultSet rs =  prepareStmt.executeQuery();
               while(rs.next())
               {
                 userPass = rs.getString("PASSWORD");
               }
               rs.close();
               if(userPass.equals(password))
           	{
             	flag=true;
             
           	}
          }catch(Exception e){
               System.out.println("error code");
               flag=false;
               e.printStackTrace();
          }
   	     return flag;
     }
}
