package com.Lab6.servlets.middleware;

import com.Lab6.logger.ILogger;
import com.Lab6.logger.LoggerFactory;
import com.Lab6.servlets.utils.RequestTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IsBlockedUserMiddleware extends Middleware
{
    @Override
    public boolean check(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        ILogger logger = new LoggerFactory().getLogger();

        if (RequestTools.isBlocked(request, response))
        {
            logger.error("A user is blocked.");
            RequestTools.redirectToBlockedPage(request, response);
            return false;
        }

        return checkNext(request, response);
    }
}

