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
    private ObjectService<Role> roleService = new RoleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_id = req.getParameter("id");
        User user = userService.getById(Long.parseLong(user_id));

        req.setAttribute("action", req.getContextPath() + "/edit");
        req.setAttribute("user", user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user_add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        EditRole(req);
        resp.sendRedirect(req.getContextPath() + "/admin");
    }

    private void EditRole(HttpServletRequest req) {
        Role role = getRole(req);
        User newUser = new User(
                Long.parseLong(req.getParameter("user_id")),
                req.getParameter("username"),
                req.getParameter("login"),
                req.getParameter("password"),
                role
        );
        userService.update(newUser);
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
