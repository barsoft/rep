package com.barsoft.memepost.Utils;

import com.barsoft.memepost.entities.Users;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LifeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext ctx = sce.getServletContext();
        try {
            final File dataFile = new File(ctx.getRealPath(ctx.getInitParameter("data.file"))).getCanonicalFile();
            if (dataFile.isFile()) {
                ctx.log(String.format("Loading ChatUsers from: %s", dataFile.getAbsolutePath()));
                final ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFile));
                try {
                    final Users users = (Users) in.readObject();
                    ctx.setAttribute("users", users);
                } finally {
                    in.close();
                }
            }
        } catch (Exception ex) {
            ctx.log("Error during load", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        final ServletContext ctx = sce.getServletContext();
        final Users users = (Users) ctx.getAttribute("users");
        try {
            final File dataFile = new File(ctx.getRealPath(ctx.getInitParameter("data.file"))).getCanonicalFile();
            ctx.log(String.format("---------------------------Saving ChatUsers to: %s", dataFile.getAbsolutePath()));
            final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFile));
            try {
                out.writeObject(users);
            } finally {
                out.close();
            }
        } catch (Exception ex) {
            ctx.log("Error during save", ex);
        }
    }
}