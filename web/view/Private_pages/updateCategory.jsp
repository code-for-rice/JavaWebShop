<%-- 
    Document   : updateCategory
    Created on : Jun 19, 2024, 8:14:51 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>
    </head>
    <body>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <br/><br/><br/>
        <div class="container">
            <h2>Update category information</h2>
            <c:set var="c" value="${requestScope.categoryObj}"/>
            <form class="form-horizontal" action="UpdateCategoryServlet">
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Type ID</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="typeId" type="text" placeholder="${c.typeId}" value="${c.typeId}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Category Name</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="categoryName" type="text" placeholder="${c.categoryName}" value="${c.categoryName}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Memo</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="memo" type="text" placeholder="${c.memo}" value="${c.memo}">
                    </div>
                </div>
                <button type="submit" class="btn btn-default">SAVE</button>
            </form>
        </div>

    </body>
</html>
