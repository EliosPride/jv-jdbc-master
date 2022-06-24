<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Illia
  Date: 22.06.2022
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div align="center">
    <h1> Driver Registration Page</h1>

    ${error}
    <br> <br>

    <form action="/register" method="post">
        Login <input type="text" name="login" value=${save_login}>
        <br> <br>

        First Name <input type="text" name="first_name" value=${save_firstName}>
        <br> <br>

        Last Name <input type="text" name="last_name" value=${save_lastName}>
        <br> <br>

        Password <input type="password" name="password">
        <br> <br>

        Confirm password <input type="password" name="confirm">
        <br> <br>

        <input type="submit" value="Зарегистрироваться">
    </form>
</div>
</body>
</html>
