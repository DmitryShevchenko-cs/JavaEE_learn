package com.Lab5.servlets;

import com.Lab5.models.User;
import com.Lab5.profile.Authenticator;
import com.Lab5.profile.ProfileTools;
import com.Lab5.servlets.utils.RequestTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

public class LoginController extends HttpServlet {


    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (ProfileTools.isLoggedIn(request))
            response.sendRedirect("home");
        else
            request.getServletContext()
                    .getRequestDispatcher("/pages/login.jsp")
                    .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authAction = request.getParameter("authAction");
        if (authAction.equals("login")) {
            login(request, response);
        } else if (authAction.equals("logout")) {
            logout(request, response);
        } else {
        }
    }


    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authTypeParam = request.getParameter("authType");
        String password = request.getParameter("psw");
        String authValue = request.getParameter("loginValue");
        Authenticator authenticator = ProfileTools.getAuthenticator(ProfileTools.getDataSource(ProfileTools.DATASOURCE_PROPERTIES_FILE));

        final User account = authTypeParam.equals("email")
                ? authenticator.authenticateByUserEmail(authValue, password)
                : authenticator.authenticateByUserName(authValue, password);

        if (account != null) {
            ProfileTools.doLogin(account, request.getSession(), ProfileTools.getDataSource(ProfileTools.DATASOURCE_PROPERTIES_FILE));
            request.getServletContext()
                    .getRequestDispatcher("/pages/home.jsp")
                    .forward(request, response);
        } else {
            RequestTools.addLoginAttemptToSession(request, response);
            if (RequestTools.isBlocked(request, response))
                RequestTools.redirectToBlockedPage(request, response);
            else {
                request.getServletContext()
                        .getRequestDispatcher("/pages/login-error.jsp")
                        .forward(request, response);
            }
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null)
            session.invalidate();

        response.sendRedirect("login");
    }

}

