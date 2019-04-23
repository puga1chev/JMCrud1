package jmCrud.servlets;

import jmCrud.model.User;
import jmCrud.service.UserServiceJdbc;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private UserServiceJdbc userServiceJdbc = new UserServiceJdbc();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String deleteUser = req.getParameter("delete");
        if (deleteUser != null) {

            try {
                userServiceJdbc.remove(deleteUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            resp.sendRedirect(req.getContextPath());
        }

        // get all users from db
        List<User> users = null;
        try {
            users = userServiceJdbc.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("Users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }

}
