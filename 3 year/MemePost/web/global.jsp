<%-- 
    Document   : header
    Created on : 20.03.2013, 2:32:41
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="curuser" class="com.barsoft.memepost.entities.User" scope="session"/>
<!DOCTYPE html>
<html>
    <body>
        <div style="float: right">
        <input type="image" src=<%=request.getContextPath()+ "/res/logo.png"%>  width="150" class="logo"  
               onclick="location.href='<%=request.getContextPath()+"/home.jsp"%>'"  />
        </div>
        
        <div style="float: left">
        <input type="image" src=<%=request.getContextPath()+ "/res/home_icon.jpg"%>  width="70" class="home_icon"  
               onclick="location.href='<%=request.getContextPath()+"/home.jsp"%>'"  />
        </div>
         <div style="float: left">
        <h1 class="title">meme-post.com</h1>
        </div>
        
        <HR WIDTH="100%" COLOR="White" SIZE="2"/>

        <%if (curuser.getUsername() == null) {%>
        <div style="float: right;">
            <input type="button" class="button" value="Sign in" onclick="location.href='<%=request.getContextPath()+"/signin.jsp"%>'"  />
            <input type="button"  class="button" value="Sign up" onclick="location.href='<%=request.getContextPath()+"/signup.jsp"%>'" />
        </div>
        <%} else {%>
        <div style="float: right;">
            <input type="button" class="button" value="Sign out" onclick="location.href='<%=request.getContextPath()+"/protected/sign-out"%>'"  />
        </div>
        <%}%>
        <HR WIDTH="100%" SIZE="2" COLOR="White" />
    </body>
</html>
