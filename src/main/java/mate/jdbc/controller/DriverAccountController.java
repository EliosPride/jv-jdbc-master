package mate.jdbc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mate.jdbc.factory.CarServiceFactory;
import mate.jdbc.service.CarService;

import java.io.IOException;

@WebServlet("/driver-account")
public class DriverAccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("driverAccount.jsp").forward(req, resp);
    }
}
