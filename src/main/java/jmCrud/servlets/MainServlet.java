package jmCrud.servlets;

import jmCrud.model.User;
import jmCrud.service.*;
import jmCrud.util.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private UserServiceJdbcImpl userServiceJdbc = new UserServiceJdbc();
    private UserServiceOrm userServiceOrm = new UserServiceOrm();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String deleteUser = req.getParameter("delete");
        if (deleteUser != null) {

            try {
                userServiceJdbc.remove(deleteUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // get all users from db
        List<User> users = null;
        try {
            users = userServiceOrm.getAll();
        }/* catch (SQLException e) {
            e.printStackTrace();
        } */ catch (HibernateException e) {
            e.printStackTrace();
        }

        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }

}
