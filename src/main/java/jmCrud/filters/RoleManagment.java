package jmCrud.filters;

import javax.servlet.http.*;
import java.io.IOException;

public class RoleManagment {

    public static boolean AdminAccesss(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String role = getSessionRole(req);
        if (role == null) {
            //todo log
            redirectToStart(req, resp);
            return false;
        }
        else if (role.equals("Администратор")) {
            return true;
        }
        redirectToStart(req, resp);
        return false;

    }

    private static void redirectToStart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }

    public static boolean UserAccesss(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String role = getSessionRole(req);
        if (role == null) {
            //todo log
            redirectToStart(req, resp);
            return false;
        } else if (role.equals("Пользователь") || role.equals("Администратор")) {
            return true;
        }
        redirectToStart(req, resp);
        return false;
    }
//todo кнопка выход из пользователя
    private static String getSessionRole(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (String) session.getAttribute("role");
    }
}
