package mate.jdbc.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mate.jdbc.factory.DriverServiceFactory;
import mate.jdbc.model.Driver;
import mate.jdbc.model.Role;
import mate.jdbc.service.DriverService;

import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/admin"})
public class AdminFilter implements Filter {
    private static final DriverService driverService = DriverServiceFactory.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        Driver driver = (Driver) session.getAttribute("driver");
        if (Objects.equals(driver.getRole(), Role.ADMIN)) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
