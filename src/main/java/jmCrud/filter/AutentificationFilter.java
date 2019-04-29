package jmCrud.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/auth")
public class AutentificationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String role = FilterHelper.getSessionRoleName(req);
        if (role.equals("Администратор")) {
            resp.sendRedirect(req.getContextPath() + "/admin");
        } else if (role.equals("Пользователь")) {
            resp.sendRedirect(req.getContextPath() + "/user");
        }
        else {
            // todo бросить исключение
        }
    }
}
