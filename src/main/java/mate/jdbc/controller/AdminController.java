package mate.jdbc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mate.jdbc.factory.DriverServiceFactory;
import mate.jdbc.service.DriverService;

import java.io.IOException;

import static mate.jdbc.util.Constants.DRIVER_ID;

@WebServlet("/admin-controller")
public class AdminController extends HttpServlet {
    private static final DriverService driverService = DriverServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("driverList", driverService.getAll());
        req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long driverId = Long.valueOf(req.getParameter(DRIVER_ID));
        driverService.delete(driverId);
        resp.sendRedirect("/admin-controller");
    }
}
