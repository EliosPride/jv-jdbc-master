package mate.jdbc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mate.jdbc.factory.CarServiceFactory;
import mate.jdbc.factory.DriverServiceFactory;
import mate.jdbc.model.Car;
import mate.jdbc.model.Driver;
import mate.jdbc.model.Role;
import mate.jdbc.service.CarService;
import mate.jdbc.service.DriverService;
import mate.jdbc.util.HashUtils;

import java.io.IOException;
import java.util.Optional;

import static mate.jdbc.util.Constants.CAR;
import static mate.jdbc.util.Constants.DRIVER;

@WebServlet("/authorization")
public class AuthorizationController extends HttpServlet {
    private static final DriverService driverService = DriverServiceFactory.getInstance();
    private static final CarService carService = CarServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String encrypt = HashUtils.encrypt(password);
        Optional<Driver> driverOptional = driverService.getByLogin(login);
        if (driverOptional.isPresent() && encrypt.equals(driverOptional.get().getPassword())) {
            Driver driver = driverOptional.get();
            session.setAttribute(DRIVER, driver);
            if (driver.getRole().equals(Role.ADMIN.name())) {
                resp.sendRedirect("/admin-controller");
            } else {
                if (driver.getCarId() == 0) {
                    resp.sendRedirect("/car/add");
                } else {
                    Car car = carService.get(driver.getCarId()).orElseThrow();
                    session.setAttribute(CAR, car);
                    resp.sendRedirect("/driver-account");
                }
            }
        } else {
            req.setAttribute("wrongData", "incorrect login or password");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
