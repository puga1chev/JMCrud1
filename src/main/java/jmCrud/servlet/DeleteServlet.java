package jmCrud.servlet;

import jmCrud.service.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {

    private ObjectService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String deleteUser = req.getParameter("id");
        if (deleteUser != null) {
            userService.delete(Long.parseLong(deleteUser));
        }

        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
