package com.Lab2.servlets;
import com.Lab2.profile.Authenticator;
import com.Lab2.profile.AuthenticatorImpl;
import com.Lab2.profile.ProfileTools;
import com.Lab2.repositories.UsersRepository;
import com.Lab2.repositories.UsersRepositoryInMemoryImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        boolean isAuthentificated = false;
        Authenticator authenticator = new AuthenticatorImpl();
        if (authTypeParam.equals("email")) {
            isAuthentificated = authenticator.authenticateByUserEmail(authValue, password);
        }

        else {
            isAuthentificated = authenticator.authenticateByUserName(authValue, password);
        }
        if (isAuthentificated)
        {
            HttpSession session=request.getSession();
            session.setAttribute(ProfileTools.sessionLogName, authValue);
            request.getServletContext()
                    .getRequestDispatcher("/jsp/home.jsp")
                    .forward(request, response);
        }
        else
        {
            request.getServletContext()
                    .getRequestDispatcher("/jsp/login-error.jsp")
                    .forward(request, response);
        }
    }
}
