<%@ page import="java.io.PrintWriter" %>
<%@ page import="mate.jdbc.model.Role" %>
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

        <div>
            <label for="loginId">Login
                <input
                        type="text"
                        name="login"
                        id="loginId"
                        value=${save_login}
                >
            </label>
        </div>

        <div>
            <label for="firstId">First Name
                <input
                        type="text"
                        name="first_name"
                        id="firstId"
                        value=${save_firstName}
                >
            </label>
        </div>

        <div>
            <label for="lastId">Last Name
                <input
                        type="text"
                        name="last_name"
                        id="lastId"
                        value=${save_lastName}
                >
            </label>
        </div>

        <div>
            <label for="pasId">Password
                <input
                        type="password"
                        name="password"
                        id="pasId"
                >
            </label>
        </div>

        <div>
            <label for="confId">Confirm password
                <input
                        type="password"
                        name="confirm"
                        id="confId"
                >
            </label>
        </div>

        <div>
            <label for="buttonName"> User
                <input
                        type="radio"
                        name="button"
                        value="USER"
                        id="buttonName"
                >
            </label>
        </div>

        <div>
            <label for="buttonName1"> Admin
                <input
                        type="radio"
                        name="button"
                        value="ADMIN"
                        id="buttonName1"
                >
            </label>
        </div>

        <div>
            <button type="submit">Submit</button>
        </div>
    </form>

</div>
</body>
</html>
