package jmCrud.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String servletPath = req.getServletPath();
        if (servletPath.equals("/admin") ||
                servletPath.equals("/add") ||
                servletPath.equals("/delete") ||
                servletPath.equals("/edit")
        ) {
            if (!RoleManagment.AdminAccesss(req, resp)) {
                return;
            }
        } else if (servletPath.equals("/user")) {
            if (!RoleManagment.UserAccesss(req, resp)) {
                return;
            }
        }

        filterChain.doFilter(req, resp);
    }


}
