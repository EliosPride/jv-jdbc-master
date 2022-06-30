<%@ page import="java.io.PrintWriter" %>
<%@ page import="mate.jdbc.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="static mate.jdbc.util.Constants.CAR_ID" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Car Add</title>
</head>
<body>
<h1> Car Add</h1>

    <%
        List<Car> carList = (List<Car>) request.getAttribute("carList");
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
                    "<form action=\"/car/add?" + CAR_ID + "=" + car.getId() + "\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Choose your Destiny\">" +
                    "</form></td>");
            printWriter.write("</tr>");
        }
        printWriter.write("</table>");
    %>

</body>
</html>
