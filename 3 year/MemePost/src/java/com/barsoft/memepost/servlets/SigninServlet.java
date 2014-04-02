package com.barsoft.memepost.servlets;

import com.barsoft.memepost.entities.User;
import com.barsoft.memepost.entities.Users;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SigninServlet extends HttpServlet {

    private Users users;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (users == null) {
            users = (Users) getServletContext().getAttribute("users");
        }
        for (Object obj : users.getUsers().keySet()) {
            if ((((User) obj).getUsername()).equals(username)
                    && (((User) obj).getPassword()).equals(password)) {
                request.getSession().setAttribute("curuser", obj);
                response.sendRedirect("home.jsp");
                return;
            }
        }
        request.setAttribute("message", "Incorrect login or password");
        final RequestDispatcher rd = getServletContext().getRequestDispatcher("/signin.jsp");
        rd.forward(request, response);
    }
}