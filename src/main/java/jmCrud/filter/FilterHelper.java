package jmCrud.filter;

import jmCrud.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FilterHelper {

    public static String getSessionRoleName(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User sessionUser = (User)session.getAttribute("user");
        return (String)sessionUser.getRole().getRolename();
    }
}
