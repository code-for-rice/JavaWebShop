<%-- 
    Document   : listAccount
    Created on : Jun 15, 2024, 9:10:42 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Of Account</title>

        <script type="text/javascript">
            function doDelete(typeId) {
                if (confirm("Are u sure to delete with type ID = " + typeId + " ?")) {
                    window.location = "CategoryController?action=delete&typeId=" + typeId;
                }
            }
        </script>
        <link href="css/listCategory.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%--<jsp:include page="../JSP/navbar.jsp"/>--%>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <br/><br/><br/>
        <h1 class="mid">Category List</h1>
        <div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 col-xs-11">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <tr>   
                                <th>Type ID</th>
                                <th>Category name</th>
                                <th>Memo</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${requestScope.dsCate}" var="c">
                                <c:set var="typeId" value="${c.typeId}"/>
                                <tr>
                                    <td>${typeId}</td>
                                    <td>${c.categoryName}</td>
                                    <td>${c.memo}</td>
                                    
                                    <td>
                                        <a href="CategoryController?action=update&typeId=${typeId}" class="btn btn-primary">Update</a>
                                        <a href="#" class="btn btn-danger" onclick="doDelete('${typeId}')">Delete</a>
                                    </td>

                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
