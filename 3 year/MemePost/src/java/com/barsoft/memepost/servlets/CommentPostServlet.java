/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.memepost.servlets;

import com.barsoft.memepost.entities.Comment;
import com.barsoft.memepost.entities.Post;
import com.barsoft.memepost.entities.User;
import com.barsoft.memepost.entities.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maks
 */
public class CommentPostServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
        HttpSession sess = request.getSession();
        User curuser = (User) sess.getAttribute("curuser");
        String curpost = (String) request.getParameter("id");
        Users users = (Users) this.getServletContext().getAttribute("users");

        boolean stop=false;
        for (User u : users.getUsers().keySet()) {
            for (Post p : ((List<Post>) users.getUsers().get(u))) {
                if (p.getId() == Integer.parseInt(curpost)) {
                    Comment c=new Comment();
                    c.setText(request.getParameter("comment"));
                    c.setUser(curuser);
                    p.getComments().add(c);
                    response.sendRedirect(request.getContextPath() + "/home.jsp");
                   return;
                }
                
            }

        }

        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
