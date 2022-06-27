package mate.jdbc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mate.jdbc.dao.DriverDao;
import mate.jdbc.model.Driver;
import mate.jdbc.util.InjectorUtils;

import java.io.IOException;
import java.util.Optional;

import static mate.jdbc.util.Constants.CAR_ID;
import static mate.jdbc.util.Constants.DRIVER_ID;

@WebServlet("/authorization")
public class AuthorizationController extends HttpServlet {
    DriverDao driverDao = (DriverDao) InjectorUtils.getInstance(DriverDao.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<Driver> driverOptional = driverDao.getByLogin(login);
        if (driverOptional.isPresent() && password.equals(driverOptional.get().getPassword())) {
            Driver driver = driverOptional.get();
            if (driver.getCarId() == 0) {
                resp.sendRedirect("/car/add?" + DRIVER_ID + "=" + driver.getId());
            } else {
                resp.sendRedirect("/driver-account?" + CAR_ID + "=" + driver.getCarId() + "&" + DRIVER_ID + "=" + driver.getId());
            }
        } else {
            req.setAttribute("wrongData", "incorrect login or password");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}