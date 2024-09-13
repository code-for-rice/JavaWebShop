<%-- 
    Document   : addNewAccount
    Created on : Jun 15, 2024, 3:17:28 PM
    Author     : Dell
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add category</title>
    </head>
    <body>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <br/><br/><br/>
        <div class="container">
            <h2>New Category</h2>
            <form class="form-horizontal" action="AddCategoryServlet">
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Category Name</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="categoryName" type="text" placeholder="New category" required>
                    </div>
                </div>
                
                <c:if test="${sessionScope.duplicateCategoryName != null}">
                    <h3 style="color: red">${duplicateCategoryName}</h3>
                </c:if>

                <div class="form-group row">
                    <label class="col-sm-2 control-label">Memo</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="memo" type="text" placeholder="Products for travel, exploration" required>
                    </div>
                </div>                
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </body>
</html>
