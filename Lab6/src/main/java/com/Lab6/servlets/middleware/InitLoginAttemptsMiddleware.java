package com.Lab6.servlets.middleware;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitLoginAttemptsMiddleware extends Middleware
{
    @Override
    public boolean check(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        if (request.getSession().getAttribute("failedLoginAttemptsCount") == null)
            request.getSession().setAttribute("failedLoginAttemptsCount", 0);

        return checkNext(request, response);
    }
}

