<%-- 
    Document   : listProduct
    Created on : Jun 21, 2024, 12:26:50 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
        <link href="css/listProduct.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function doDelete(productId) {
                if (confirm("Are u sure to delete with type ID = " + productId + " ?")) {
                    window.location = "ProductControllerV2?action=delete&productId=" + productId;
                }
            }
        </script>
    </head>
    <body>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <div class="clr"></div>
        <br/><br/><br/>
        <div>
            <h1 class="mid">Product List</h1>
            <div>
                <div class="row">
                    <div class="col-md-12 col-xs-11">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <tr>  
                                    <th>Product ID</th>
                                    <th>Product Name</th>
                                    <th>Product Image</th>
                                    <th>Brief</th>
                                    <th>Posted Date</th>
                                    <th>Category Name</th>
                                    <th>Account</th>
                                    <th>Unit</th>
                                    <th>Price</th>
                                    <th>Discount</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach items="${listProduct}" var="p">
                                    <c:set var="productId" value="${p.productId}"/>
                                    <tr>
                                        <td>${p.productId}</td>
                                        <td>${p.productName}</td>
                                        <td><img src="${pageContext.request.contextPath}${p.productImage}" alt=""/></td>
                                        <td>${p.brief}</td>
                                        <td>${p.postedDate}</td>
                                        <td>${p.typeId.categoryName}</td>
                                        <td>${p.account.account}</td>
                                        <td>${p.unit}</td>
                                        <td>${p.price}</td>
                                        <td>${p.discount}</td>
                                        <td>
                                            <a href="ProductController?action=update&productId=${p.productId}" class="btn btn-primary">Update</a>
                                            <a href="#" class="btn btn-danger" onclick="doDelete('${productId}')">Delete</a>
                                        </td>

                                    </tr>


                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
