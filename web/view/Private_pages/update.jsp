<%-- 
    Document   : update
    Created on : Jun 16, 2024, 5:47:44 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function myFunction() {
                var x = document.getElementById("pass");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
            }
        </script>
    </head>
    <body>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <br/><br/><br/>
        <div class="container">
            <h2>Update account information</h2>
            <c:set var="a" value="${requestScope.accountObj}"/>
            <form class="form-horizontal" action="MainController?action=updateDoPost" method="post">
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Account</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="account" type="text" placeholder="${a.account}" value="${a.account}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="pass" id="pass" type="password" placeholder="${a.pass}" value="${a.pass}">
                        <input type="checkbox" onclick="myFunction()">Show Password
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Last name</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="lastName" type="text" placeholder="${a.lastName}" value="${a.lastName}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">First name</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="firstName" type="text" placeholder="${a.firstName}" value="${a.firstName}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Phone number</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="phone" type="text" placeholder="${a.phone}" value="${a.phone}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label">Birth day</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="birthday" type="date" placeholder="${a.birthday}" value="${a.birthday}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label" for="gender">Gender</label>
                    <div class="col-sm-4">
                        <label class="radio-inline"><input type="radio" name="gender" value="true" <c:if test="${a.gender == true}">checked</c:if>>Male</label>
                        <label class="radio-inline"><input type="radio" name="gender" value="false" <c:if test="${a.gender == false}">checked</c:if>>Female</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">Usage:</label>
                        <div class="col-sm-4">
                            <input type="checkbox" name="isUse" value="true" <c:if test="${a.isUse==true}">checked</c:if><c:if test="${a.isUse==false}">unchecked</c:if>> Active
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="roleInSystem" class="col-sm-2 control-label">Role in system</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="roleInSystem">
                                <option value="1" <c:if test="${a.roleInSystem == 1}">selected</c:if>>Administrator</option>
                            <option value="2" <c:if test="${a.roleInSystem == 2}">selected</c:if>>Staff</option>
                        </select>
                    </div>
                </div>
                <button type="submit" class="btn btn-default">SAVE</button>
            </form>
        </div>
    </body>
</html>
