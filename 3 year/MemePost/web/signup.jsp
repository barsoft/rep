<%-- 
    Document   : signup
    Created on : 20.03.2013, 0:39:18
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@include file="global.jsp" %>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Sign up</title>
        <link href="res/meme-post.css" rel="stylesheet" type="text/css" />
    </head>

    <body class="body" >
       
        <div class="center_inputs_signin_div">
            <h3 class="errorlabel"><%= request.getAttribute("message") != null ? request.getAttribute("message") : ""%></h3>
        </div>
         <form enctype="multipart/form-data" action="sign-up" method="post">
            <div class="center_inputs_signin_div">
                <h1 class="title">Sign up</h1>
                <h1 class="label" >Login  </h1>
                <input class="input_signin" type="text" name="username" size="40"/>
                <h1 class="label" >Password  </h1>
                <input class="input_signin" type="password" name="password" size="40"/>
                <br></br>
                <h1 class="label" >Photo  </h1>
                <input class="input_signin" type="file" name="file" size="40"/>
            </div>
            <div class="center_submit_signin_div">
                <button  type="submit" class="button" >Ok</button>
            </div>
        </form>

    </body>
</html>
