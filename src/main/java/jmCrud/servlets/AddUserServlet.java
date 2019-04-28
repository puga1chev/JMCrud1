package jmCrud.servlets;

import jmCrud.model.Role;
import jmCrud.model.User;
import jmCrud.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {

    private ObjectService<User> userService = new UserServiceImpl();
    private ObjectService<Role> roleService = new RoleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", req.getContextPath() + "/add");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user_add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        addUser(req);

        resp.sendRedirect(req.getContextPath() + "/admin");
    }

    private void addUser(HttpServletRequest req) {
        Role role = getRole(req);
        User currentUser = new User(
                0L,
                req.getParameter("username"),
                req.getParameter("login"),
                req.getParameter("password"),
                role
        );
        userService.insert(currentUser);
    }

    private Role getRole(HttpServletRequest req) {
        Role role = roleService.getByField("rolename", req.getParameter("role"));
        if (role == null) {
            role = new Role(0L, req.getParameter("role"));
            roleService.insert(role);
        }
        return role;
    }
}
