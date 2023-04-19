package servlets;

import models.User;
import repositories.UsersRepository;
import repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class HomeServlet extends HttpServlet {

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
                request.getServletContext().getRequestDispatcher("/jsp/home.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
