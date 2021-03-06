package mate.jdbc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mate.jdbc.factory.DriverServiceFactory;
import mate.jdbc.model.Driver;
import mate.jdbc.model.Role;
import mate.jdbc.service.DriverService;
import mate.jdbc.util.HashUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import static mate.jdbc.util.Constants.DRIVER;
import static mate.jdbc.util.Constants.DRIVER_ID;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
    private static final DriverService driverService = DriverServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("driverRegistration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm");
        String encrypt = HashUtils.encrypt(password);
        Role role = Role.valueOf(req.getParameter("button"));
        if (password.equals(confirmPassword) && !isEmpty(login, firstName, lastName, password)) {
            Driver driver = new Driver(firstName, lastName, login, encrypt, role);
            driverService.create(driver);
            if (driver.getRole().equals(Role.USER)) {
                HttpSession session = req.getSession();
                session.setAttribute(DRIVER, driver);
                resp.sendRedirect("/car/add?" + DRIVER_ID + "=" + driver.getId());
            } else {
                HttpSession session = req.getSession();
                session.setAttribute(DRIVER, driver);
                resp.sendRedirect("/adminPage.jsp");
            }
        } else {
            String error = "Different passwords".toUpperCase(Locale.ROOT);
            if (isEmpty(login, firstName, lastName, password)) {
                error = "Please insert empty fields";
            }
            req.setAttribute("error", error);
            req.setAttribute("save_login", login);
            req.setAttribute("save_firstName", firstName);
            req.setAttribute("save_lastName", lastName);
            req.getRequestDispatcher("driverRegistration.jsp").forward(req, resp);
        }
    }

    private boolean isEmpty(String login, String firstName, String lastName, String password) {
        return Objects.equals(login, "")
                || Objects.equals(firstName, "")
                || Objects.equals(lastName, "")
                || Objects.equals(password, "");
    }
}
