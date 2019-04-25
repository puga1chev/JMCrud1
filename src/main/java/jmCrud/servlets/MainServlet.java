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

    private UserServiceDB userService = new UserServiceOrm();// = new UserServiceJdbc();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get all users from db
        List<User> users = null;
        try {
            users = userService.getAll();
        } catch (Exception e) {
            req.setAttribute("exception", e.getStackTrace());
        }

        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
