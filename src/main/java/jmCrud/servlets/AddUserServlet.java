package jmCrud.servlets;

import jmCrud.model.User;
import jmCrud.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {

    private UserServiceJdbcImpl userServiceJdbc = new UserServiceJdbc();
    private UserServiceOrm userServiceOrm = new UserServiceOrm();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", req.getContextPath() + "/add");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user_manage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        User currentUser = new User(
                0L,
                req.getParameter("username"),
                req.getParameter("login"),
                req.getParameter("password")
        );

        try {
            userServiceOrm.add(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath());
    }
}
