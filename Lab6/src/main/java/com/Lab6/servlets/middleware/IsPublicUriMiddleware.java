package com.Lab6.servlets.middleware;

import com.Lab6.logger.ILogger;
import com.Lab6.logger.LoggerFactory;
import com.Lab6.profile.ProfileTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IsPublicUriMiddleware extends Middleware
{
    private ArrayList<String> _ignoredUrlList;

    public IsPublicUriMiddleware(ArrayList<String> ignoredUrlList) {
        _ignoredUrlList = ignoredUrlList;
    }

    @Override
    public boolean check(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        ILogger logger = new LoggerFactory().getLogger();

        final String requestUri = request.getRequestURI();
        final String relativeUri = requestUri.substring(request.getContextPath().length());
        final boolean shouldBeIgnored = isIgnoredUrl(relativeUri, _ignoredUrlList);

        if (! shouldBeIgnored && ! ProfileTools.isLoggedIn(request))
        {
            logger.error("A user typed an incorrect password.");
            request.getRequestDispatcher("/pages/login.jsp")
                    .forward(request, response);
            return false;
        }

        return checkNext(request, response);
    }

    private boolean isIgnoredUrl(String url, List<String> ignoredUrlList)
    {
        for (String ignoredUrl : ignoredUrlList) {
            if (url.equals(ignoredUrl))
                return true;
        }
        return false;
    }
}
