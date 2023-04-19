package com.Lab3.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        request.getServletContext()
                .getRequestDispatcher("/jsp/indv.jsp")
                .forward(request, response);

    }
}
