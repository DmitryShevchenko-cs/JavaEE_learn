package com.Lab3.servlets;

import com.Lab3.models.User;
import com.Lab3.repositories.UsersRepository;
import com.Lab3.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddUserServlet extends HttpServlet {

    public static UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepositoryInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersRepository.findAll();
        request.setAttribute("usersFromServer", users);
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/jsp/AddUser.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthdate");
        String role = request.getParameter("role");
        if(name != null)
            usersRepository.set_user(new User(name, password, email, LocalDate.parse(birthdate), role));

        doGet(request, response);
    }
}
