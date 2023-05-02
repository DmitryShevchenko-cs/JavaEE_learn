package com.Lab6.servlets;
import com.Lab6.logger.ILogger;


import com.Lab6.logger.LoggerFactory;
import com.Lab6.models.User;
import com.Lab6.profile.Authenticator;
import com.Lab6.profile.ProfileTools;
import com.Lab6.servlets.filters.SessionFilter;
import com.Lab6.servlets.utils.RequestTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    private static ILogger createLogger() {
        LoggerFactory factory = new LoggerFactory();
        return factory.getLogger();
    }


    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ILogger logger = createLogger();

        String authTypeParam = request.getParameter("authType");
        String password = request.getParameter("psw");
        String authValue = request.getParameter("loginValue");
        Authenticator authenticator =
                ProfileTools.getAuthenticator(ProfileTools.getDataSource(
                        getServletContext().getRealPath(ProfileTools.DATASOURCE_PROPERTIES_FILE)));

        final User account = authTypeParam.equals("email")
                ? authenticator.authenticateByUserEmail(authValue, password)
                : authenticator.authenticateByUserName(authValue, password);

        if (account != null) {
            ProfileTools.doLogin(account, request.getSession(),
                    ProfileTools.getDataSource(getServletContext().getRealPath(ProfileTools.DATASOURCE_PROPERTIES_FILE)));

            logger.debug("A user has just logged in.");

            request.getServletContext()
                    .getRequestDispatcher("/pages/home.jsp")
                    .forward(request, response);
        } else {
            RequestTools.addLoginAttemptToSession(request, response);
            if (RequestTools.isBlocked(request, response)){
                logger.error("A user is blocked.");
                RequestTools.redirectToBlockedPage(request, response);
            }else {
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

