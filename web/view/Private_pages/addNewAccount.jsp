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
        <title>Create a new account</title>
        <link href="../../css/navbar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="menu-tab">
            <%@include file="../JSP/navbar.jsp" %>
        </div>
        <div class="clr"></div>
        <br/><br/><br/>
        <div>
            <div class="container">
                <h2>Create an account</h2>
                <form class="form-horizontal" action="MainController?action=addAccountDoPost" method="post">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Account</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="account" type="text" placeholder="Enter username" required>
                        </div>
                    </div>

                    <h3 style="color: red">${duplicateAcc}</h3>

                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="pass" type="password" placeholder="Enter password" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Last name</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="lastName" type="text" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">First name</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="firstName" type="text" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Phone number</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="phone" type="text">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label">Birth day</label>
                        <div class="col-sm-4">
                            <input class="form-control" name="birthday" type="date" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label" for="gender">Gender</label>
                        <div class="col-sm-4">
                            <label class="radio-inline"><input type="radio" name="gender" checked value="true">Male</label>
                            <label class="radio-inline"><input type="radio" name="gender" value="false">Female</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="roleInSystem" class="col-sm-2 control-label">Role in system</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="roleInSystem">
                                <option value="1">Administrator</option>
                                <option value="2">Staff</option>
                            </select>
                        </div>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="isUse" value="true">Is active</label>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>

    </body>
</html>
