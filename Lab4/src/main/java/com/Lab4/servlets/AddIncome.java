package com.Lab4.servlets;

import com.Lab4.models.Taxes.Incomes.Income;
import com.Lab4.models.Taxes.Incomes.JobIncome;
import com.Lab4.models.Taxes.Incomes.PropertySaleIncome;
import com.Lab4.models.Taxes.Incomes.RoyaltyIncome;
import com.Lab4.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddIncome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getServletContext()
                .getRequestDispatcher("/pages/home.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("income");
        String inc = req.getParameter("income-amount");
        User us = (User) req.getSession().getAttribute("account");
        if (type.equals("salary")) {
            us.addTax(new JobIncome(Double.parseDouble(inc)));
        } else if (type.equals("Royalties")) {
            us.addTax(new RoyaltyIncome(Double.parseDouble(inc)));
        } else if (type.equals("Property")) {
            us.addTax(new PropertySaleIncome(Double.parseDouble(inc)));
        }
        doGet(req, resp);
    }
}
