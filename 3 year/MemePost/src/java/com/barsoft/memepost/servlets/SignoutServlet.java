package com.barsoft.memepost.servlets;

import com.barsoft.memepost.entities.Post;
import com.barsoft.memepost.entities.User;
import com.barsoft.memepost.entities.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sess = request.getSession();
        User curuser = (User) sess.getAttribute("curuser");
        if (request.getParameter("clear") != null) {
            ServletContext cont = getServletContext();
            Users users = (Users) cont.getAttribute("users");
            users.getUsers().put(curuser,new ArrayList<Post>());
            cont.setAttribute("users", users);
        }
        sess.removeAttribute("curuser");
        response.sendRedirect("signin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}