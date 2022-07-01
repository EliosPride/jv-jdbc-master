package mate.jdbc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mate.jdbc.factory.CarServiceFactory;
import mate.jdbc.factory.DriverServiceFactory;
import mate.jdbc.model.Car;
import mate.jdbc.model.Driver;
import mate.jdbc.service.CarService;
import mate.jdbc.service.DriverService;

import java.io.IOException;

import static mate.jdbc.util.Constants.*;

@WebServlet("/car/add")
public class CarAddController extends HttpServlet {
    private static final CarService carService = CarServiceFactory.getInstance();
    private static final DriverService driverService = DriverServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("carList", carService.getAll());
        req.setAttribute(DRIVER_ID, req.getParameter(DRIVER_ID));
        req.getRequestDispatcher("/carAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long carId = Long.valueOf(req.getParameter(CAR_ID));
        Driver driver = (Driver) req.getSession().getAttribute(DRIVER);
        driver.setCarId(carId);
        driverService.update(driver);
        Car car = carService.get(driver.getCarId()).orElseThrow();
        req.getSession().setAttribute(CAR, car);
        resp.sendRedirect("/driver-account");
    }
}
