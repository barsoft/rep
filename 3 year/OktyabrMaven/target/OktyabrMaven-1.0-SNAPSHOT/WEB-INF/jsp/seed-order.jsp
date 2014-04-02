<%@page import="com.barsoft.oktyabr.dao.SeedTypesDAO"%>
<%@page import="com.barsoft.oktyabr.entities.SeedTypes"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen, projection">
    </head>


    <body>
        <jsp:include page="header.jsp"/>
        <div class="work-area">
            <table border="1" class="simple-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Pack size (gr)</th>
                        <th>Price per instance</th>
                    </tr>
                </thead>
                <tbody
                    <%

                        for (SeedTypes seedType : SeedTypesDAO.INSTANCE_SEED_TYPES.getSeedTypes()) {%>
                    <tr>
                        <td><%=seedType.getName()%></td>
                        <td><%=seedType.getPackSizeGr()%></td>
                        <td><%=seedType.getPricePerInstance()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

        </div>
        <jsp:include page="footer.jsp"/>
    </body>

</html>
