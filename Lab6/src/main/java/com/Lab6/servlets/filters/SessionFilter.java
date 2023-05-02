package com.Lab6.servlets.filters;

import com.Lab6.logger.LoggerFactory;
import com.Lab6.logger.ILogger;
import com.Lab6.profile.ProfileTools;
import com.Lab6.servlets.middleware.InitLoginAttemptsMiddleware;
import com.Lab6.servlets.middleware.IsBlockedUserMiddleware;
import com.Lab6.servlets.middleware.IsPublicUriMiddleware;
import com.Lab6.servlets.middleware.Middleware;
import com.Lab6.servlets.utils.RequestTools;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SessionFilter implements Filter {
    private ArrayList<String> ignoredUrlList;
    public void init(FilterConfig fConfig) throws ServletException
    {
        ignoredUrlList = new ArrayList<String>();
        String urls = fConfig.getInitParameter("ignore-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
        while (token.hasMoreTokens()) {
            ignoredUrlList.add(token.nextToken());
        }
    }

    public static ILogger createLogger() {
        LoggerFactory factory = new LoggerFactory();
        return factory.getLogger();
    }


    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain)
            throws IOException, ServletException
    {
        ILogger logger = createLogger();

        HttpServletRequest request = (HttpServletRequest) rq;
        HttpServletResponse response = (HttpServletResponse) rs;

        Middleware middleware = new IsPublicUriMiddleware(getIgnoredUrlList());
        middleware.linkWith(
                new IsBlockedUserMiddleware().linkWith(
                        new InitLoginAttemptsMiddleware()));
        if (middleware.check(request, response)) {
            chain.doFilter(rq, rs);
        }
    }

    public ArrayList<String> getIgnoredUrlList() {
        return ignoredUrlList;
    }

    public void setIgnoredUrlList(ArrayList<String> urlList) {
        this.ignoredUrlList = urlList;
    }


    public void destroy() {
    }
}
