package com.controller;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import com.model.Shopping;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.model.*;

public class Authorise
{
    public static String role = null;
    public static int userId=0;
    public boolean verify(String username,String password)
    {
        Shopping obj=new Shopping();
        boolean flag = false;
		try{
            String[] command = {"cmd"," /c ","powershell", "C:\\pass.ps1",username,password};
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process=builder.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            String output = "";
            while ((s = stdInput.readLine()) != null) 
            {
                output=output+s;
            }
            stdInput.close();
            if(output.equals("True"))
            {
                boolean thresh=isAdmin(username);
                System.out.println(thresh);
                if(thresh)
                {
                    role = "Admin";
                }
                else
                {
                    obj.insertUsers(username,password);
                    userId=obj.getUserId(username,password);
                    role = "User";
		        }
                flag = true;
	        }
		    else
            {
                flag = false;
		    }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println(e);
            System.exit(0);
        }
        return flag;
    }
    public static boolean isAdmin(String admin) 
    {
        String groups[] = (new com.sun.security.auth.module.NTSystem()).getGroupIDs();
        for (String group : groups) 
        {
          if (group.equals("S-1-5-32-544"))
          {
            String admin1=(new com.sun.security.auth.module.NTSystem()).getName();
            if(admin.equals(admin1))
            {
              return true;
            }
          }
        }
    return false;
   }
   public static String getRole()
   {
    return role;
   }
}