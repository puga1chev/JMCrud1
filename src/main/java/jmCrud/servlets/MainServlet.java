package jmCrud.servlets;

import jmCrud.model.User;
import jmCrud.service.UserServiceJDBC;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // получаем список пользователей из БД
        UserServiceJDBC userServiceJDBC = new UserServiceJDBC();
        List<User> users = null;
        try {
            users = userServiceJDBC.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("Users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
