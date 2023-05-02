package com.Lab6.servlets.filters;

import com.Lab6.profile.ProfileTools;
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
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) rq;
        HttpServletResponse response = (HttpServletResponse) rs;
        if (request.getSession().getAttribute("failedLoginAttemptsCount") == null)
        {
            request.getSession().setAttribute("failedLoginAttemptsCount", 0);
        }

        final String requestUri = request.getRequestURI();
        final String relativeUri = requestUri.substring(request.getContextPath().length());

        final boolean shouldBeIgnored = isIgnoredUrl(relativeUri);
        if (RequestTools.isBlocked(request, response))
        {
            RequestTools.redirectToBlockedPage(request, response);
        }
        else if (!shouldBeIgnored && !ProfileTools.isLoggedIn(request))
        {
            request.getRequestDispatcher("/pages/Index.html").forward(request, response);
        }
        else
        {
            chain.doFilter(rq, rs);
        }
    }

    private boolean isIgnoredUrl(String url)
    {
        for (String ignoredUrl : getIgnoredUrlList())
            if (url.startsWith(ignoredUrl))
                return true;
        return false;
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
