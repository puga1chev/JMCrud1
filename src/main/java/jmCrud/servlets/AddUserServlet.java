package jmCrud.servlets;

import jmCrud.model.User;
import jmCrud.service.UserServiceJdbc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {

    private UserServiceJdbc userServiceJdbc = new UserServiceJdbc();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "add");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user_manage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUser = new User(
                "",
                req.getParameter("username"),
                req.getParameter("login"),
                req.getParameter("password")
        );

        try {
            userServiceJdbc.add(currentUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath());
    }
}