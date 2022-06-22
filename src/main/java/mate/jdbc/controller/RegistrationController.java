package mate.jdbc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mate.jdbc.dao.DriverDao;
import mate.jdbc.model.Driver;
import mate.jdbc.util.InjectorUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
    private final DriverDao driverDao = (DriverDao) InjectorUtils.getInstance(DriverDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("driver-registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm");
        Driver driver = new Driver(firstName, lastName, login, password);
        if (password.equals(confirmPassword) && !isEmpty(login, firstName, lastName, password)) {
            driverDao.create(driver);
            resp.sendRedirect("/driver");
        } else {
            String error = "Different passwords".toUpperCase(Locale.ROOT);
            if (isEmpty(login, firstName, lastName, password)) {
                error = "Please insert empty fields";
            }
            req.setAttribute("error", error);
            req.setAttribute("save_login", login);
            req.setAttribute("save_firstName", firstName);
            req.setAttribute("save_lastName", lastName);
            req.getRequestDispatcher("driver-registration.jsp").forward(req, resp);
        }
    }

    private boolean isEmpty(String login, String firstName, String lastName, String password) {
        return Objects.equals(login, "")
                || Objects.equals(firstName, "")
                || Objects.equals(lastName, "")
                || Objects.equals(password, "");
    }
}
