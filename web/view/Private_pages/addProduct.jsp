<%-- 
    Document   : addProduct
    Created on : Jun 21, 2024, 2:14:52 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <div class="clr"></div>
        <br/><br/><br/>
        <div>
            <div class="container">
                <h2>Add a product</h2>
                <form class="form-horizontal" action="AddProduct" enctype="multipart/form-data" method="post">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Product ID</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="productId" type="text" placeholder="Enter product ID" required>
                        </div>
                    </div>

                    <h3 style="color: red">${duplicateProduct}</h3>
                    <!--<h3 style="color: red">${errorMessage}</h3>-->

                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Product Name</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="productName" type="text" placeholder="Enter product Name" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Product Image</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="productImage" type="file" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Brief</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="brief" type="text" placeholder="Enter product brief">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Category Name</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="categoryName" type="text" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Account Name</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="account" type="text" required>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Unit</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="unit" type="text" placeholder="Enter unit">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Price</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="price" type="text" placeholder="Enter price" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Discount</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="discount" type="text" placeholder="Enter discount">
                        </div>
                    </div>
                    
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>        
    </body>
</html>
