<%-- 
    Document   : index
    Created on : 2020-3-9, 23:19:56
    Author     : guanshiqian
--%>

<%@page import="database.ObtainMKinfo"%>
<%@page import="java.util.Map"%>
<%@page import="database.ObtainTittle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
        <title>菜单</title>
        <script type="text/javascript" src="JS/jquery-3.3.1.min.js"></script>
        <script type="text/javascript">
            <%
                Map<String, String> a = ObtainMKinfo.obtainMKinfo();
                request.setAttribute("a", a);
            %>
                $(document).ready(function () {
                    <c:forEach items="${a}" var="map">
                      $("#<c:out value="${map.key}"></c:out>").click(function(){
                     window.location.href="./BaseJsp.jsp?MKID=<c:out value="${map.key}"></c:out>";
                });                  
                    
             </c:forEach> 
                    
                });
          </script>                           

    </head>
    <body>
        <div>
              <c:forEach items="${a}" var="map">
               <br/>
                 <center><button id='<c:out value="${map.key}"></c:out>' style="width: 95%;height: 40px"> <c:out value="${map.value}"></c:out></button></center> 
              <br/>           
             </c:forEach>
        </div>
    </body>
</html>
