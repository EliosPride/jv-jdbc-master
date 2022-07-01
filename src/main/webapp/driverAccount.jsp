<%@ page import="mate.jdbc.model.Car" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="static mate.jdbc.util.Constants.CAR" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Driver Account</title>
</head>
<body>

<%
    Car car = (Car) request.getSession().getAttribute(CAR);
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
    printWriter.write("<button><a href=\"/car/add\"> Add/Change Car</a></button>");
%>

<button><a href="/logout"> Logout</a></button>

</body>
</html>
