package mate.jdbc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mate.jdbc.dao.CarDao;
import mate.jdbc.dao.DriverDao;
import mate.jdbc.model.Car;
import mate.jdbc.model.Driver;
import mate.jdbc.util.InjectorUtils;

import java.io.IOException;
import java.util.List;

import static mate.jdbc.util.Constants.CAR_ID;
import static mate.jdbc.util.Constants.DRIVER_ID;

@WebServlet("/car/add")
public class CarAddController extends HttpServlet {
    private final CarDao carDao = (CarDao) InjectorUtils.getInstance(CarDao.class);
    private final DriverDao driverDao = (DriverDao) InjectorUtils.getInstance(DriverDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("carList", carDao.getAll());
        req.setAttribute(DRIVER_ID, req.getParameter(DRIVER_ID));
        req.getRequestDispatcher("/carAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long carId = Long.valueOf(req.getParameter(CAR_ID));
        Driver driver = driverDao.get(Long.valueOf(req.getParameter(DRIVER_ID))).orElseThrow();
        driver.setCarId(carId);
        driverDao.update(driver);
        resp.sendRedirect("/driver-account?" + CAR_ID + "=" + carId + "&" + DRIVER_ID + "=" + driver.getId());
    }
}
