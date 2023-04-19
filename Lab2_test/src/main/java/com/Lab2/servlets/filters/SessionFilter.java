package com.Lab2.servlets.filters;
import com.Lab2.profile.ProfileTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter
{
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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest rq = (HttpServletRequest) request;
        final String requestUri = rq.getRequestURI();
        final String relativeUri = requestUri.substring(rq.getContextPath().length());
        boolean shouldBeIgnored = isIgnoredUrl(relativeUri);
        if (! shouldBeIgnored && ! ProfileTools.isLoggedIn(rq))
        {
            rq.getServletContext()
                    .getRequestDispatcher("/jsp/login.jsp")
                    .forward(request, response);
        }
        else {
            chain.doFilter(request, response);
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

    @Override
    public void destroy(){ }
}