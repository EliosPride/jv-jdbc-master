package mate.jdbc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mate.jdbc.dao.DriverDao;
import mate.jdbc.util.InjectorUtils;

import java.io.IOException;
import java.io.PrintWriter;

public class DriverController extends HttpServlet {
    private final DriverDao driverDao = (DriverDao) InjectorUtils.getInstance(DriverDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(String.valueOf(driverDao.getAll()));
        printWriter.close();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("drivers.jsp");
        requestDispatcher.forward(req, resp);
    }
}
