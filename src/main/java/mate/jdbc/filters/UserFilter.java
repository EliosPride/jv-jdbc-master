package mate.jdbc.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mate.jdbc.model.Driver;
import mate.jdbc.model.Role;

import java.io.IOException;

@WebFilter(urlPatterns = {"/car/add", "/driver-account"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        Driver driver = (Driver) session.getAttribute("driver");
        if (driver.getRole().equals(Role.USER.name())) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
