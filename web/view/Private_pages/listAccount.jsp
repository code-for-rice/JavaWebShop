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
            function doDelete(account) {
                if (confirm("Are u sure to delete with account = " + account + " ?")) {
                    window.location = "MainController?action=delete&account=" + account;
                }
            }
        </script>
        <link href="css/listAccount.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/navbar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <div class="clr"></div>
        <br/><br/><br/>
        <div>
            <h1 class="mid">Account List</h1>
            <div>
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8 col-xs-11">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <tr>   
                                    <th>Account</th>
                                    <th>Full name</th>
                                    <th>Birth Day</th>
                                    <th>Gender</th>
                                    <th>Phone</th>
                                    <th>Role in system</th>
                                    <th>Action</th>
                                </tr>
                                <c:set var="currentAccount" value="${u.account}"></c:set>
                                <c:forEach items="${ds}" var="i">
                                    <c:set var="account" value="${i.account}"/>
                                    <c:if test="${!i.account.equals(currentAccount)}">
                                        <tr>
                                            <td>${account}</td>
                                            <td>${i.lastName} ${i.firstName}</td>
                                            <td>${i.birthday}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${i.gender}">
                                                        <img src="${pageContext.request.contextPath}/images/img_avatar1.png" alt="Male" style="width: 40px; height: 40px;">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="${pageContext.request.contextPath}/images/img_avatar2.png" alt="Female" style="width: 40px; height: 40px;">
                                                    </c:otherwise>
                                                </c:choose>
                                                <!--${i.gender?"Male":"Female"}-->

                                            </td>
                                            <td>${i.phone}</td>
                                            <td>${i.roleInSystem==1?"Administrator":"Staff"}</td>
                                            <td>
                                                <a href="MainController?action=update&account=${account}" class="btn btn-primary">Update</a>
                                                <a href="MainController?action=updateIsUse&account=${account}&isUse=${i.isUse}" class="btn btn-success">${i.isUse==true?"Deactive":"Active"}</a>
                                                <a href="#" class="btn btn-danger" onclick="doDelete('${account}')">Delete</a>
                                            </td>

                                        </tr>
                                    </c:if>


                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
