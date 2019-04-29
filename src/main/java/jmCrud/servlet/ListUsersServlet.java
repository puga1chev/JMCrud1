package jmCrud.servlet;

import jmCrud.model.User;
import jmCrud.service.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/admin")
public class ListUsersServlet extends HttpServlet {

    private ObjectService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userService.getAll("id");

        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/users_view.jsp");
        requestDispatcher.forward(req, resp);
    }
}
