<%-- 
    Document   : header
    Created on : 25.06.2013, 0:21:12
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/header-style.css" type="text/css" media="screen, projection">
    </head>
    <body >
        <div class="header-body">
            <a href="index.htm" ><img src="resources/logo.png"  alt="logo" class="header-logo"/></a>

            <form style="margin-top: 35px; margin-right: 20px; width: 150px;  float: right"  action="/j_spring_security_check" method='POST'>
                <input type="text" class="input" name="j_username" placeholder="Логин"/>
                <input type="password" class="input" name="j_password" placeholder="Пароль"  />

                <a href="sign-up.htm" style=" margin-top: 15px;  float: right">Регистрация</a>
                <input type="submit" class="button" name="sign-in-submit"  style=" margin-top: 10px; float: right; margin-right:  10px; width: 60px" value="Вход" />

            </form>

        </div>
        <div class="header-body" style="height: 50px">
            <table  border="1" class="navigation-table">
                <thead>
                    <tr>
                        <th onclick="document.location.href = 'index.htm'" style="cursor:pointer;">Главная</th>
                        <th onclick="document.location.href = 'seed-order.htm'" style="cursor:pointer;">Заказ семок</th>
                        <th onclick="document.location.href = 'about.htm'" style="cursor:pointer;">О проекте</th>
                    </tr>
                </thead>
            </table>
        </div>

    </body>
</html>
