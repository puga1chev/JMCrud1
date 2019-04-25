package jmCrud.servlets;

import jmCrud.model.User;
import jmCrud.service.UserServiceDB;
import jmCrud.service.UserServiceOrm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private UserServiceDB userService = new UserServiceOrm();// = new UserServiceJdbc();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String deleteUser = req.getParameter("id");
        if (deleteUser != null) {

            try {
                userService.remove(Long.parseLong(deleteUser));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("action", req.getContextPath() + "/");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user_delete.jsp");
        requestDispatcher.forward(req, resp);
    }
}
