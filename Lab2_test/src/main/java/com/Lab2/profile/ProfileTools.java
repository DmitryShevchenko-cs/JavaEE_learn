package com.Lab2.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileTools {
    public static final String sessionLogName = "user";

    // метод возвращает true в том случае, если пользователь залогинился

    public static boolean isLoggedIn(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        return session != null &&
                session.getAttribute(sessionLogName) != null;
    }

}
