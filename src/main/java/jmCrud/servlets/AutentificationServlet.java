package jmCrud.servlets;

import jmCrud.model.*;
import jmCrud.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/autentification")
public class AutentificationServlet extends HttpServlet {

    private ObjectService<User> userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get login pass from form
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.getByField("login", login);
        // todo mod5 pass
        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        //generate a new session
        HttpSession session = req.getSession(true);
        if (user != null && user.getLogin().equals(login) && user.getPass().equals(password)) {

            session.setAttribute("role", user.getRole().getRolename());

            if (user.getRole().getRolename().equals("Администратор")) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else if (user.getRole().getRolename().equals("Пользователь")) {
                resp.sendRedirect(req.getContextPath() + "/user");
            }
            else {
                // todo бросить исключение
            }
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/");
        }

    }
}
