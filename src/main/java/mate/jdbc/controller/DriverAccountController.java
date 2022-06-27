package mate.jdbc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mate.jdbc.factory.FactorySingleton;
import mate.jdbc.dao.CarDao;
import mate.jdbc.service.CarService;
import mate.jdbc.util.InjectorUtils;

import java.io.IOException;

import static mate.jdbc.util.Constants.CAR_ID;
import static mate.jdbc.util.Constants.DRIVER_ID;

@WebServlet("/driver-account")
public class DriverAccountController extends HttpServlet {
    private final CarService carService = FactorySingleton.getCarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long carId = Long.valueOf(req.getParameter(CAR_ID));
        req.setAttribute("driversCar", carService.get(carId).orElseThrow());
        req.setAttribute(DRIVER_ID, req.getParameter(DRIVER_ID));
        req.getRequestDispatcher("driverAccount.jsp").forward(req, resp);
    }
}
