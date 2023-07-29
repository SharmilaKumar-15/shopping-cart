<!DOCTYPE>
<html>
     <%
         String result =(String) request.getAttribute("result");
         System.out.println(result);
          HttpSession sessionVar = (HttpSession)request.getSession(false);
          String user = (String) sessionVar.getAttribute("user");
          if (user!=null && user.equals("admin")){
            if (result!=null)
              out.println("<script>alert('"+result+"')</script>");
     %>

     
               <style>
                    .vertical-align{
                         width: px;
                         text-align: centre;
                         line-height: 10rem;
                    }
                    body
                    {
                         text-align: centre;
                         justify-content: center;
                    }
               </style>


               <body>
                    <div class="vertical-align">
                              <h1> Welcome admin...!!!<h1>
                    </div>
                    <br>
                    <br>
                    <button type="buttons" onclick="window.location.href = 'http://localhost:8080/webapp/admin/insert.jsp'">Insert</button>

                    <button type="buttons" onclick="window.location.href = 'http://localhost:8080/webapp/viewItems.jsp'">View</button>

                    <button type="buttons" onclick="window.location.href = 'http://localhost:8080/webapp/admin/update.jsp'">Update</button>

                    <button type="buttons" onclick="window.location.href = 'http://localhost:8080/webapp/admin/delete.jsp'">Delete</button>

                    <button type="buttons" onclick="window.location.href = 'http://localhost:8080/webapp/logout'">Logout</button>

               </body>

     <%
          }else{
               out.println("<h1>You are not admin</h1>");
     %>
          <h3>Go to <a href="http://localhost:8080/webapp/login.html">Login</a></h3>
     <%
          }
     %>

</html>