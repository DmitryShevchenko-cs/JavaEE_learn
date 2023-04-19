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
import java.util.List;

@WebServlet("/AddUser")
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
        if(name != null)
            usersRepository.set_user(new User(name, password, email, LocalDate.parse(birthdate)));

        doGet(request, response);
    }
}
