package com.Lab3.servlets;

import com.Lab3.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WriteCookieServlet extends HttpServlet {

    public List<String> readEmailListFromFile(String filePath) throws IOException { // read emails from file
        List<String> emailList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            emailList.add(line.trim());
        }
        reader.close();
        return emailList;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read emails from file
        List<String> Emails = readEmailListFromFile(getServletContext().getRealPath("/WEB-INF/emailList.txt"));

        //create temporary cookie with email and add it in response
        for (int i=0; i < Emails.size(); i++) {
            Cookie tempCookie = new Cookie(String.valueOf(i), Emails.get(i));
            tempCookie.setMaxAge(300);
            response.addCookie(tempCookie);
        }

        //get cookies from request and do filter, because there are some cookies that were added automatically
        List<Cookie> cookies = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().matches("\\d+"))
                .collect(Collectors.toList());

        request.setAttribute("EmailsFromCookies", cookies);
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/jsp/cookies.jsp");

        dispatcher.forward(request, response);
    }
}
