<%@ page import="mate.jdbc.model.Car" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="static mate.jdbc.util.Constants.DRIVER_ID" %><%--
  Created by IntelliJ IDEA.
  User: Illia
  Date: 23.06.2022
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Driver Account</title>
</head>
<body>

<%
    Car car = (Car) request.getAttribute("driversCar");
    Object driverId = request.getAttribute(DRIVER_ID);
    PrintWriter printWriter = response.getWriter();
    printWriter.write("<table border=\"1\">");
    printWriter.write("<tr>");
    printWriter.write("<th>model</th>");
    printWriter.write("<th>year</th>");
    printWriter.write("<tr>");
    printWriter.write("<td>" + car.getModel() + "</td>");
    printWriter.write("<td>" + car.getYear() + "</td>");
    printWriter.write("</tr>");
    printWriter.write("</table>");
    printWriter.write("<button><a href=\"/car/add?" + DRIVER_ID + "=" + driverId + "\"> Add/Change Car</a></button>");
%>

</body>
</html>
