package servlets;

import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/indv")
public class IndvServlet extends HttpServlet {

    public static String ReverseFirstAndLast(String text) {
        String[] sentences = text.split("\\."); // разделение текста на предложения

        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            String[] words = sentence.split(" ");
            String firstWord = words[0];
            String lastWord = words[words.length - 1];
            words[0] = lastWord;
            words[words.length - 1] = firstWord;
            sentences[i] = String.join(" ", words);
        }

        String newText = String.join(". ", sentences);
        return newText;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String text = request.getParameter("text");
        if(text != null){
            String newtext = ReverseFirstAndLast(text);
            request.setAttribute("text", text);
            request.setAttribute("newtext", newtext);
        }

        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/jsp/indv.jsp");
        dispatcher.forward(request, response);
    }
}
