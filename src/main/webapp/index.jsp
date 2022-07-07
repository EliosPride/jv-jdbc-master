<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nero-Taxi</title>
</head>
<body>
<div align="center">
    <h1> Authorization</h1>

    ${wrongData}
    <br> <br>

    <form action="/authorization" method="post">
        Login <input type="text" name="login">
        <br> <br>

        Password <input type="password" name="password">
        <br> <br>

        <input type="submit" value="Sign in">

    </form>

        <button><a href="/register">Driver-Registration</a></button>
</div>
</body>
</html>
