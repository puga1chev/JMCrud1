package jmCrud.servlets;

import jmCrud.model.User;
import jmCrud.service.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private UserServiceJdbcImpl userServiceJdbc = new UserServiceJdbc();
    private UserServiceOrmImpl userServiceOrm = new UserServiceOrm();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String deleteUser = req.getParameter("delete");
        if (deleteUser != null) {

            try {
                userServiceOrm.remove(Long.parseLong(deleteUser));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // get all users from db
        List<User> users = null;
        try {
            users = userServiceOrm.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }

}
