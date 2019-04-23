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

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {

    private UserServiceJdbc userServiceJdbc = new UserServiceJdbc();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_id = req.getParameter("id");
        User user = null;
        try {
            user = userServiceJdbc.getById(user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("user", user);

        req.setAttribute("action", "edit");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user_manage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User newUser = new User(
                req.getParameter("user_id"),
                req.getParameter("username"),
                req.getParameter("login"),
                req.getParameter("password")
        );

        try {
            userServiceJdbc.edit(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath());
    }

    private String[] parsePath(String path) {

        if (path == null || path.isEmpty()) {
            return null;
        }
        String[] array = path.split("/");
        return array;
    }
}
