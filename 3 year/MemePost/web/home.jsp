<%-- 
    Document   : home
    Created on : 20.03.2013, 0:31:22
    Author     : maks
--%>

<%@page import="com.barsoft.memepost.entities.Comment"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.barsoft.memepost.entities.Post"%>
<%@page import="com.barsoft.memepost.entities.User"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="users" class="com.barsoft.memepost.entities.Users" scope="application"/>

<!DOCTYPE html>
<%@include file= "global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>mame-post.com</title>
        <link href="res/meme-post.css" rel="stylesheet" type="text/css" />
    </head>
     
    <body class="body">
       
      
            
        <%if (curuser.isRegisteredUser()) {%>
        <h1 class="label"><%="Hello, " + curuser.getUsername() + "!"%>
            
            <!-- ADD POST BUTTON--------------------------------------------------------------->    
            
            <div style="float: right;">
                <input type="button" class="button" value="Add post" onclick="location.href='protected/addpost.jsp'"  />
            </div>
        </h1>
        <%}%>
        <HR WIDTH="100%" SIZE="2" COLOR="White" />

        <div >
            <%
                int countPosts = 0;
                for (User u : users.getUsers().keySet()) {
                    for (Post p : ((List<Post>) users.getUsers().get(u))) {
            %>


                            <!-- POST DATE--------------------------------------------------------------->    
                            <div style="float: right;">
                                <% SimpleDateFormat ft =
                                                        new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");%>
                                <h1 class="label"><%= ft.format(p.getDate())%></h1>
                            </div>
                            
                            <!-- POST SENDER INFO--------------------------------------------------------------->    
                            <div style="float: left;">
                                <h1 class="label"><%= u.getUsername()%></h1>
                                <img src="<%=u.getPhotoUrl()%>"  class="logo" width="100" height="100" />
                            </div>
 
                            
                            <center>
                                
                            <!-- POST DATA--------------------------------------------------------------->    
                            <h1 class="label"><%= p.getText()%></h1>
                            <img src="<%=p.getMemUrl()%>"  class="meme" width="300" />

                               <br></br>
                            <!-- COMMENTS LIST--------------------------------------------------------------->  
                            <div style="background-color: #999; width: 460px" >
                            <%for (Comment c: p.getComments()){%>

                            <div style="float: left">
                            <img src="<%=c.getUser().getPhotoUrl() %>"  class="photo_small" width="50" />
                            </div>
                            <div style="float: left">
                            <h1 class="comment" style="width: 400px"><%=c.getText()%></h1>
                            
                            </div>
                            <br></br>
                            <br></br>
                            <%}%>
                            </div>
                            <!-- COMMENT INPUT AREA--------------------------------------------------------------->  

                            <div>
                            <%if (curuser.isRegisteredUser()) {%>
                             <form action="protected/comment-post" method="post">
                                <textarea placeholder="Input comment text" name="comment" class="textarea" style="width: 400px; height: 70px" ></textarea>
                                <br></br>
                                <input name="id" type="hidden" class="button" value="<%= p.getId()%>"  />
                                
                                <input type="submit"  value="Send"  class="button"></input>
                             </form>
                            <%}%>
                            </div>
                            
                            <!-- LIKE POST BUTTON--------------------------------------------------------------->    
                             <%if (curuser.isRegisteredUser()) {%>
                                <form action="protected/like-post" method="post">
                                  
                                   
                                    <%String calcLikers=String.valueOf(p.getLikers().getUsers().keySet().size());%>
                                    <h1 class="label"><%=calcLikers+ (calcLikers.toString().charAt(calcLikers.length()-1)=='1' ?" user":" users") + " appreciate this"%></h1>
                                    
                                    
                                    
                                    <input name="id" type="hidden" class="button" value="<%= p.getId()%>"  />
                                    
                                    <% if (p.userLikes(curuser)){%>
                                    
                                    <input type="submit"  value=""  class="likeactive"
                                       />      
                                    <%}else{%>
                                    <input type="submit"  value=""  class="likenoactive"
                                    
                                       />  
                                    <%}%>

                                </form>
                            <%}%>
                            
                            <!-- DISLIKE POST BUTTON--------------------------------------------------------------->    
                            
                             <%if (curuser.isRegisteredUser()) {%>
                                <form action="protected/dislike-post" method="post">
                                  
                                   
                                    <%String calcDislikers=String.valueOf(p.getDislikers().getUsers().keySet().size());%>
                                     <h1 class="label"><%=calcDislikers+ (calcDislikers.toString().charAt(calcDislikers.length()-1)=='1' ?" user":" users") + " disrespect this"%></h1>
                                    
                                    
                                    
                                    <input name="id" type="hidden" class="button" value="<%= p.getId()%>"  />
                                    
                                    <% if (p.userDislikes(curuser)){%>
                                    
                                    <input type="submit"  value=""  class="dislikeactive"
                                       />      
                                    <%}else{%>
                                    <input type="submit"  value=""  class="dislikenoactive"
                                    
                                       />  
                                    <%}%>
                                    
                                   
                                    
                                </form>
                            <%}%>
                            
                            </center>
                            
                            <!-- DELETE POST BUTTON--------------------------------------------------------------->    
                            <div style="float: right">
                            <%if (curuser.equals(u)) {%>
                            <form action="protected/delete-post" method="post">

                                    <input name="id" type="hidden" class="button" value="<%= p.getId()%>"  />
                                    <input type="submit" class="button" value="Delete post"  />

                            </form>     
                            <%}%>
                            </div>
                           
                           
                          

                            <HR WIDTH="100%" SIZE="2" COLOR="White" />

            <%
                        countPosts++;
                    }
                }
                if (countPosts == 0) {
            %>
        </div>



        <div style="float: left;">
            <h1 class="label">There is no posts yet.</h1>
        </div>
        <%}%>

    </body>
</html>