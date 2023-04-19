package com.Lab42.servlets;

import com.Lab42.models.Incomes.*;
import com.Lab42.models.Taxes;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class testservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Taxes tax = new Taxes();
        tax.addIncome(new JobIncome(2000));
        tax.addIncome(new PropertySaleIncome(1000));
        tax.addIncome(new RoyaltyIncome(500));


        req.setAttribute("Tax", tax);
        RequestDispatcher dispatcher =
                req.getServletContext().getRequestDispatcher("/pages/taxes.jsp");

        dispatcher.forward(req, resp);
    }
}
