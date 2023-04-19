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
import java.util.List;

public class UsersListServlet extends HttpServlet {

    public UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        this.usersRepository = new UsersRepositoryInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        List<User> users = null;
        if(name != null && !name.equals("")){
            users = usersRepository.findByName(name);
        }
        else{
            users = usersRepository.findAll();
        }
        request.setAttribute("usersFromServer", users);
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/jsp/UsersList.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
