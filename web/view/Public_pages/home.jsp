<%-- 
    Document   : dashboard
    Created on : Jun 14, 2024, 7:49:14 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="../../css/navbar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%--<%@include file="../JSPF/navbar.jsp" %>--%>
        <%--<jsp:include page="../JSP/navbar.jsp"/>--%>
        <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Home - Web Bán Hàng</title>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
            <style>
                .carousel-inner > .item > img {
                    width: 100%;
                    height: 500px;
                }
                .product {
                    margin-bottom: 30px;
                }
                .product img {
                    width: 100%;
                    height: 200px;
                }
            </style>
        </head>
        <body>
            <!-- Navbar -->
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>                        
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#">Home</a></li>
                            <li><a href="ProductController?action=list">Sản Phẩm</a></li>
                        </ul>
                        <!--                        <ul class="nav navbar-nav navbar-right">
                                                    <li><a href="MainController?action=addAccount"><span class="glyphicon glyphicon-user"></span> Sign up</a></li>
                                                    <li><a href="LoginServlet"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                                                </ul>-->
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="MainController?action=addAccount"><span class="glyphicon"></span> Sign up</a></li>
                            <li>
                                <form action="LoginServlet" method="GET" style="display: inline;">
                                    <button type="submit" class="btn btn-link navbar-btn">
                                        <span class="glyphicon"></span> Login
                                    </button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <!-- Carousel -->
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/images/sanPham/TaiNgheBluetoothY98.jpg" alt="Image 1">
                    </div>

                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/sanPham/giayTheThaoNu.jpg" alt="Image 2">
                    </div>

                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/sanPham/tuLanhELECTROLUX.png" alt="Image 3">
                    </div>
                </div>

                <!-- Left and right controls -->
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <!-- Products Section -->
            <div class="container text-center">
                <h3>Sản Phẩm Nổi Bật</h3>
                <div class="row">
                    <div class="col-sm-4 product">
                        <img src="${pageContext.request.contextPath}/images/sanPham/chaoChienTefal.jpg" alt="Product 1">
                        <h4>Sản Phẩm 1</h4>
                        <p>Giá: 100.000 VNĐ</p>
                    </div>
                    <div class="col-sm-4 product">
                        <img src="${pageContext.request.contextPath}/images/sanPham/samsungGalaxyNote10Plus.jpg" alt="Product 2">
                        <h4>Sản Phẩm 2</h4>
                        <p>Giá: 200.000 VNĐ</p>
                    </div>
                    <div class="col-sm-4 product">
                        <img src="${pageContext.request.contextPath}/images/sanPham/tuLanhELECTROLUX.png" alt="Product 3">
                        <h4>Sản Phẩm 3</h4>
                        <p>Giá: 300.000 VNĐ</p>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <footer class="container-fluid text-center">
                <p>Web Bán Hàng &copy; 2024</p>
            </footer>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        </body>
    </html>


</body>
</html>
