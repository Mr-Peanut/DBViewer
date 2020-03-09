<%-- 
    Document   : checkRFX
    Created on : 2018-12-27, 20:09:23
    Author     : guans
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="database.ObtainTittle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
        <title>数据查询</title>
        <script type="text/javascript" src="JS/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
        <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
        <!--        <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/foundation/dataTables.foundation.css">
                <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/foundation/dataTables.foundation.js"></script>-->
        <script type="text/javascript">
            <%
                Map<String, String> a = ObtainTittle.obtainTittle(request.getParameter("MKID"));
                request.setAttribute("a", a);
            %>
            var table;
            var CJ;
            var mkid = '<%=request.getParameter("MKID").toString()%>';            
            $(document).ready(function () {
                table = $("#result_table").DataTable( );
                $("#check").click(function () {
                    $.ajax({
                        type: "GET", //提交方式 
                        url: "./LinkServlet", //路径
                        data: {"CJ": $("#CJ  option:selected").val(),
                            "MKID": mkid
                        },
                        success: function (result) {
                            if (result['OTHER_SERVER_ERROR'] !== 'OK') {
                                alert(result['OTHER_SERVER_ERROR']);
                            }
                            // $("#XS").html($("#CJ  option:selected").val());
                            //返回数据根据结果进行相应的处理 
                            if (table !== undefined) {
                                table.destroy();
                            }
                            var data2=result['RESULT'];
                            var data3=[];
//                            data3.push({data: "WLXX_WLID"});
//                            data3.push({data: "WLXX_MC"});
                            <c:forEach items="${a}" var="map">
                            data3.push({data:'<c:out value="${map.value}"></c:out>'});
                           </c:forEach>
//                             alert(data3);  
                            table = $("#result_table").DataTable({
                                data: data2,
                                //使用对象数组，一定要配置columns，告诉 DataTables 每列对应的属性
                                //data 这里是固定不变的，name，position，salary，office 为你数据里对应的属性
                                columns:data3 
                            });
                        },
                        error: function (result) {
                            alert(result.statusText);
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
    <center style="width: 100%;height: 40px"><a style="width: 15%;height: 40px">请选择查询的车间：</a><select id="CJ"  name="CJ" style="width: 85%;height: 40px" >
            <option value="1车间" >1车间</option>
            <option value="2车间" selected="selected">2车间</option>
               </select></center>
    <br/>
    <center><button id="check" style="width: 95%;height: 40px">点击查询最新数据</button></center> 
    <br/>
    <a id="XS"></a>
    <div id="resultContent">
        <table id="result_table" class="display">
            <thead>
                <c:forEach items="${a}" var="map">
                <th><c:out value="${map.key}"></c:out></th>
                </c:forEach>
            </thead>
        </table>
    </div>
</body>
</html>
