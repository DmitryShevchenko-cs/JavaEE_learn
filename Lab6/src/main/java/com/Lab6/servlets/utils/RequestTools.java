package com.Lab6.servlets.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class RequestTools
{
    public static void addLoginAttemptToSession(HttpServletRequest rq, HttpServletResponse rs)
            throws IOException, ServletException
    {
        final int attempts =
                Integer.parseInt(rq.getSession()
                        .getAttribute("failedLoginAttemptsCount")
                        .toString());
        rq.getSession().setAttribute("failedLoginAttemptsCount", attempts + 1);
    }
    public static boolean isBlocked(HttpServletRequest rq, HttpServletResponse rs)
            throws IOException, ServletException
    {
        if (rq.getSession().getAttribute("blockedDate") == null
                || rq.getSession().getAttribute("blockedTime") == null)
        {
            rq.getSession().setAttribute("blockedDate", LocalDate.now());
            rq.getSession().setAttribute("blockedTime", LocalTime.now());
        }

        final int attempts =
                Integer.parseInt(rq.getSession()
                        .getAttribute("failedLoginAttemptsCount")
                        .toString());
        return attempts >= 3;
    }

    public static void redirectToBlockedPage(HttpServletRequest rq, HttpServletResponse rs)
            throws IOException, ServletException
    {
        rq.getRequestDispatcher("/pages/blocked.jsp").forward(rq, rs);
    }
}