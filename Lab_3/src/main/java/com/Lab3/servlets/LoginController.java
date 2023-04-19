package com.Lab3.servlets;

import com.Lab3.models.User;
import com.Lab3.servlets.utils.RequestTools;
import com.Lab3.profile.ProfileTools;
import com.Lab3.repositories.UsersRepository;
import com.Lab3.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class LoginController extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public static UsersRepository usersRepository;

    public LoginController() { super(); usersRepository = new UsersRepositoryInMemoryImpl();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        request.getServletContext()
                .getRequestDispatcher("/jsp/login.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String authAction = request.getParameter("authAction");
        if (authAction.equals("login")){
            login(request, response);
        }
        else if (authAction.equals("logout")) {
            logout(request, response);
        }
        else { }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session!=null)
            session.invalidate();
        doGet(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String authTypeParam = request.getParameter("authType");
        String password = request.getParameter("psw");
        String authValue = request.getParameter("loginValue");

        var authenticator = ProfileTools.getAuthenticator();
        User account;
        if (authTypeParam.equals("email")) {
            account = authenticator.authenticateByUserEmail(authValue, password);
        }
        else {
            account = authenticator.authenticateByUserName(authValue, password);
        }

        if (account != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute(ProfileTools.SESSION_LOGGEDIN_ATTRIBUTE_NAME,
                    account.get_name());

            if (ProfileTools.isAdmin(account)) {
                session.setAttribute(ProfileTools.ACCOUNT_IS_ADMIN_ATTRIBUTE_NAME,
                        ProfileTools.isAdmin(account));
            }

            request.getSession().setAttribute("failedLoginAttemptsCount", 0);
            request.getSession().removeAttribute("blockedDate");
            request.getSession().removeAttribute("blockedTime");
            request.getServletContext()
                    .getRequestDispatcher("/jsp/home.jsp")
                    .forward(request, response);
        }
        else
        {
            RequestTools.addLoginAttemptToSession(request, response);
            if (RequestTools.isBlocked(request, response)) {
                RequestTools.redirectToBlockedPage(request, response);
            }
            else
            {
                request.getServletContext()
                        .getRequestDispatcher("/jsp/login-error.jsp")
                        .forward(request, response);
            }
        }
    }
}
