<%@ page import="java.io.PrintWriter" %>
<%@ page import="mate.jdbc.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="static mate.jdbc.util.Constants.DRIVER_ID" %>
<%@ page import="static mate.jdbc.util.Constants.CAR_ID" %><%--
  Created by IntelliJ IDEA.
  User: Illia
  Date: 23.06.2022
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Car Add</title>
</head>
<body>
<h1> Car Add</h1>

    <%
        List<Car> carList = (List<Car>) request.getAttribute("carList");
        request.getAttribute(DRIVER_ID);
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<table border=\"1\">");
        printWriter.write("<tr>");
        printWriter.write("<th>model</th>");
        printWriter.write("<th>year</th>");
        printWriter.write("</tr>");
        for (Car car : carList) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + car.getModel() + "</td>");
            printWriter.write("<td>" + car.getYear() + "</td>");
            printWriter.write("<td>" +
                    "<form action=\"/car/add?" + CAR_ID + "=" + car.getId() + "&" + DRIVER_ID + "=" + request.getAttribute(DRIVER_ID) + "\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Choose your Destiny\">" +
                    "</form></td>");
            printWriter.write("</tr>");
        }
        printWriter.write("</table>");
    %>

</body>
</html>
