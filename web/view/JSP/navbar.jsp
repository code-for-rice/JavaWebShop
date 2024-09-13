<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/tlds/CustomTag" prefix="MyCustomTag"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            .width {
                width: 450px;
            }
            .width2 {
                padding-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand width" href="MainController?action=update&account=${sessionScope.u.account}">
                        <c:if test="${not empty sessionScope.u}">
                            <c:choose>
                                <c:when test="${sessionScope.u.account != sessionScope.updated.account}">
                                    Welcome 
                                    <c:if test="${sessionScope.u.gender}">Mr. </c:if> 
                                    <c:if test="${not sessionScope.u.gender}">Ms. </c:if>
                                    <MyCustomTag:CustomTag role="${sessionScope.u.roleInSystem}" name="${sessionScope.u.lastName} ${sessionScope.u.firstName}" />
                                </c:when>
                                <c:otherwise>
                                    Welcome 
                                    <c:if test="${sessionScope.updated.gender}">Mr. </c:if> 
                                    <c:if test="${not sessionScope.updated.gender}">Ms. </c:if>
                                    <MyCustomTag:CustomTag role="${sessionScope.updated.roleInSystem}" name="${sessionScope.updated.lastName} ${sessionScope.updated.firstName}" />
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </a>
                </div>
                <div class="collapse navbar-collapse width2">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="MainController?action=home">Home</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Account<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="MainController?action=addAccount" class="dropdown-item">Create a new account</a></li>
                                <li><a href="MainController?action=list_acc" class="dropdown-item">List of accounts</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Category<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="CategoryController?action=add">Add new category</a></li>
                                <li><a href="CategoryController?action=list">List of categories</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Product<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="ProductController?action=add">Add new product</a></li>
                                <li><a href="ProductController?action=list">List of products</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="MainController?action=update&account=${sessionScope.u.account}"><span class="glyphicon glyphicon-user"></span></a></li>
                        <li><a href="MainController?action=logout"><span class="glyphicon glyphicon-log-in"></span> Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
