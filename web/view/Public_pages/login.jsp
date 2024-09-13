<%-- 
    Document   : login
    Created on : Jun 14, 2024, 4:26:06 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/styleindex.css">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

    </head>
    <body>
        <div class="wrapper">
            <form action="MainController?action=login" method="post">
                <h1><b>Login</b></h1>
                <div class="input-box">
                    <input class="form-control" type="text" placeholder="Username" name="user" required>
                    <i class='bx bxs-user'></i>
                </div>
                <div class="input-box">
                    <input class="form-control" type="password" placeholder="Password" name="pass" required>
                    <i class='bx bxs-lock-alt'></i>
                </div>

                <div class="remember-forgot">
                    <label><input type="checkbox"> Remember me</label>
                    <a href="#">Forgot password?</a>
                </div>

                <button class="btn" type="submit">Login</button>
<!--                <h1 style="color: red">
                    <%= (session.getAttribute("errorMessage") != null) ? session.getAttribute("errorMessage") : ""%>
                </h1>-->
                <%
                    if (session.getAttribute("errorMessage") != null) {
                        String er = (String) session.getAttribute("errorMessage");
                %>
                <h5 style="color: red"><b><%= er%></b></h5>
                <%
                    }
                %>

                <div class="register-link">
                    <p>Don't have an account? <a href="MainController?action=addAccount">Register</a></p>
                </div>
            </form>
        </div>

    </body>
</html>
