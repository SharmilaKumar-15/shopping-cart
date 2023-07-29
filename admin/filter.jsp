
<%@ page import='com.db.Database' %>
<html>

          <head>
               <!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/webapp/view.css"> -->
               <link rel="stylesheet" type="text/css" href="http://localhost:8080/webapp/admin/style.css">
          </head>
          <body>

               <div class="tab-bar">
                      
                      

                      
                        <h3>Go to <a href="URL">Login</a></h3>

                         <form action="http://localhost:8080/webapp/admin/insert.jsp" class="form">
                              <input type="submit" name="insert" value="INSERT" />
                         </form>
                         <form action="http://localhost:8080/webapp/viewItems.jsp" class="form">
                              <input type="submit" name="insert" value="VIEW" />
                         </form>
                         <form action="http://localhost:8080/webapp/admin/delete.jsp" class="form">
                              <input type="submit" name="insert" value="DELETE" />
                         </form>
                         <form action="http://localhost:8080/webapp/admin/update.jsp" class="form">
                              <input type="submit" name="insert" value="UPDATE" />
                         </form>
                          <form action="logout" class="form">
                              <input type="submit" name="insert" value="LOGOUT" />
                         </form>
                         

                    </div>  
      
                    <div id='inside-container'>

                    
                    <select id='choice' onchange="myFunction()">
                              <option>select</option>
                              <option>itemname</option>
                              <option>quantity</option>
                              <option>expirydate</option>
                    </select>

                    </div>
                    <div id='itemname'>
            
                    <select id='item' style="visibility: hidden"onchange="myFunction()">
                              <option>equals</option>
                              <option>not equals</option>
                              <option>contains</option>
                              <option>not contains</option>
                    </select>
                  </div>
                  <div id=quantity>
                        <select id='quants'style="visibility: hidden" onchange="myFunction()">
                            
                              <option>equals</option>
                              <option>greaterthan</option>
                              <option>lessthan</option>
                              <option>between</option>
                         </select>
                       </div>
                       <div id=expirydate>
                        <select id='expiryDate'style="visibility: hidden" onchange="myFunction()">
                      
                              <option>equals</option>
                              <option>after</option>
                              <option>before</option>
                         </select>
                       </div>

                    <input id ='input1' type='text'>
                    <input id='input' type='text' style="visibility:hidden">
                    
                    <div class='header'>
                          <button id='search' >Search</button>
                    </div>
                    <div id='content-body'>
        <div class='content' style="flex:1">
        </div>
        <div class="content" style="flex:5">
          <%
            Database obj = new Database();
            out.println(obj.displayItems());
          %>
        </div>
        <div class='content' style="flex:1">
        </div>      

                </div>

                     <script>
                          function myFunction(){
                                   let choose = document.getElementById('Main').value;
                                   if(choose=='Item name'){
                                    document.getElementById('item').style.visibility='visible';
                                    document.getElementById('quants').style.visibility='hidden';
                                    document.getElementById('expiryDate').style.visibility='hidden';
                                    document.getElementById('input').style.visibility='hidden';
                                
                                   }
                                   else if(choose=='quantity'){
                                        document.getElementById('quants').style.visibility='visible';

                                         document.getElementById('item').style.visibility='hidden';
                                         document.getElementById('expiryDate').style.visibility='hidden';
                                         let between=document.getElementById('quants').value;
                  
                                         if(between=='between'){
                                          document.getElementById('input').style.visibility='visible';
                          
                                         }
                                         else{
                                           document.getElementById('input').style.visibility='hidden';
                                         }
                                   }
                                   else if(choose=='expirydate'){
                                        document.getElementById('expiryDate').style.visibility='visible';
                                        document.getElementById('quants').style.visibility='hidden';
                                         document.getElementById('item').style.visibility='hidden';
                                         document.getElementById('input').style.visibility='hidden';
                                   }
                                 }
                               </script>

                             </body>
                    </html>