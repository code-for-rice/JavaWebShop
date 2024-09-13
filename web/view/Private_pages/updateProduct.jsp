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
        <title>Update Product</title>
    </head>
    <body>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <br/><br/><br/>
        <div class="container">
            <h2>Product form</h2>
            <c:set var="p" value="${requestScope.productObj}"/>
            <form class="form-horizontal" action="ProductControllerV2?action=${ACTION}" enctype="multipart/form-data" method="post">
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Product ID</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="productId" type="text" value="${p.productId}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Product Name</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="productName" type="text" value="${p.productName}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Product Image</label>
                    <div class="col-sm-4">
                        <c:if test="${p.productImage != null}">
                            <img src="${pageContext.request.contextPath}/${p.productImage}" alt="" width="100px"/>
                        </c:if>
                            <input class="form-control" name="productImage" type="file" value="${pageContext.request.contextPath}${p.productImage}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Brief</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="brief" type="text" value="${p.brief}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Posted Date</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="postedDate" type="date" value="${p.postedDate}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Category Name</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="categoryName" type="text" value="${p.typeId.categoryName}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Manager</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="account" type="text" value="${p.account.account}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Unit</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="unit" type="text" value="${p.unit}">
                    </div>
                </div>       

                <div class="form-group row">
                    <label class="col-sm-2 control-label">Price</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="price" type="text" value="${p.price}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Discount</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="discount" type="text" value="${p.discount}">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">SAVE</button>
            </form>
        </div>       

    </body>
</html>

</html>
