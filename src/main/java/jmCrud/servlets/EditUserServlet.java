package jmCrud.servlets;

import jmCrud.model.Role;
import jmCrud.model.User;
import jmCrud.service.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {

    private ObjectService<User> userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_id = req.getParameter("id");
        User user = userService.getById(Long.parseLong(user_id));

        req.setAttribute("action", req.getContextPath() + "/edit");
        req.setAttribute("user", user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user_manage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        User newUser = new User(
                Long.parseLong(req.getParameter("user_id")),
                req.getParameter("username"),
                req.getParameter("login"),
                req.getParameter("password"),
                new Role()
        );

        userService.update(newUser);
        resp.sendRedirect(req.getContextPath());
    }

/*    private String[] parsePath(String path) {

        if (path == null || path.isEmpty()) {
            return null;
        }
        String[] array = path.split("/");
        return array;
    }*/
}
