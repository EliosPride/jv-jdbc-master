<%@ page import="mate.jdbc.model.Driver" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="mate.jdbc.model.Role" %>
<%@ page import="static mate.jdbc.util.Constants.DRIVER_ID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>

<%
    List<Driver> drivers = (List<Driver>) request.getAttribute("driverList");
    PrintWriter printWriter = response.getWriter();
    printWriter.write("<table border=\"1\">");
    printWriter.write("<tr>");
    printWriter.write("<th>login</th>");
    printWriter.write("<th>first_name</th>");
    printWriter.write("</tr>");
    for (Driver driver : drivers) {
        if (driver.getRole().equals(Role.USER.name())) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + driver.getLogin() + "</td>");
            printWriter.write("<td>" + driver.getFirstName() + "</td>");
            printWriter.write("<td>" +
                    "<form action=\"/admin-controller?" + DRIVER_ID + "=" + driver.getId() + "\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Delete Oluh\">" +
                    "</form></td>");
            printWriter.write("</tr>");
        }
    }
    printWriter.write("</table>");
%>

</body>
</html>
