package com.Lab6.servlets.middleware;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next; return next;
    }

    public abstract boolean check(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    protected boolean checkNext(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        return (next == null) ? true : next.check(request, response);
    }

}
