<%-- 
    Document   : newjsp
    Created on : 2020-3-7, 10:27:22
    Author     : guanshiqian
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<!--        <h1>Hello World!</h1>-->

<%
          String[] a={"车间","CE","C","Si","维护时间"}; 
           request.setAttribute("a",a);
         %>
         <c:forEach items="${a}" var="s">
             <p><c:out value="${s}"></c:out></p>
         </c:forEach>
    </body>
</html>
