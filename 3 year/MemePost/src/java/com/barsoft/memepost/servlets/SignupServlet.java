package com.barsoft.memepost.servlets;

/*final String username = req.getParameter("username");
 final Users users = (Users) getServletContext().getAttribute("users");
 User newUser = new User();
 newUser.setUsername(username);
 String message = null;
 if (users.getUsers().containsKey(newUser)) {
 message = String.format("User \"%s\" already exists", username);
 } else {
 newUser.setPassword(req.getParameter("password"));
 users.getUsers().put(newUser, new ArrayList<Post>());
 req.getSession().setAttribute("curuser", newUser);
 resp.sendRedirect("home.jsp");
 return;
 }
 req.setAttribute("message", message);
 final RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
 rd.forward(req, resp);
 }*/
import com.barsoft.memepost.entities.Post;
import com.barsoft.memepost.entities.User;
import com.barsoft.memepost.entities.Users;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class SignupServlet extends HttpServlet {

    private Random random = new Random();
    private String username;
    private String password;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //проверяем является ли полученный запрос multipart/form-data
        request.setCharacterEncoding("UTF-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Создаём класс фабрику 
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем один мегабайт
        factory.setSizeThreshold(1024 * 1024);

        // устанавливаем временную директорию
        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        //Создаём сам загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);

        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт. 
        upload.setSizeMax(1024 * 1024 * 10);

        User u = new User();

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();



            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //если принимаемая часть данных является полем формы			    	
                    //if (item.getString("username") != null && !item.getString().equals("password") &&item.getString("password") != null && !item.getString("password").equals("")) {
                    if (item.getFieldName().equals("username")) {
                        if (item.getString() != null && !item.getString().equals("")) {
                            processUsernameField(item, u);

                        } else {
                            request.setAttribute("message", "Login shouldn't be empty!");
                            final RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
                            rd.forward(request, response);
                            return;
                        }
                    } else if (item.getFieldName().equals("password")) {
                        if (item.getString() != null && !item.getString().equals("")) {
                            processPasswordField(item, u);

                        } else {
                            request.setAttribute("message", "Password shouldn't be empty!");
                            final RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
                            rd.forward(request, response);
                            return;
                        }
                    }
                } else {
                    //в противном случае рассматриваем как файл
                    if (!item.getName().contains(".jpg") && !item.getName().contains(".png") && !item.getName().contains(".bmp") && !item.getName().contains(".jpeg")) {
                        request.setAttribute("message", "Only .png, .jpg, .jpeg, .bmp. files allowed!");
                        final RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
                        rd.forward(request, response);
                        return;
                    } else if (item.getName() != "") {
                        processUploadedFile(item, u);

                    } else {
                        request.setAttribute("message", "No file selected!");

                        final RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
                        rd.forward(request, response);

                        return;
                    }
                }
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        Users users = (Users) this.getServletContext().getAttribute("users");

        if (users.getUsers().containsKey(u)) {
            request.setAttribute("message", String.format("User \"%s\" already exists", username));

            final RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
            rd.forward(request, response);
            return;
        }
        users.getUsers().put(u, new ArrayList<Post>());

        request.getSession().setAttribute("curuser", u);

        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }

    /**
     * Сохраняет файл на сервере, в папке upload. Сама папка должна быть уже
     * создана.
     *
     * @param item
     * @throws Exception
     */
    private void processUploadedFile(FileItem item, User p) throws Exception {
        File uploadedFile = null;
        //выбираем файлу имя пока не найдём свободное
        Date curdate = Calendar.getInstance().getTime();
        SimpleDateFormat ft = new SimpleDateFormat("ddMMyyyyHHmmss");
        String uploadToPath = uploadToPath = "upload/" + Math.abs(random.nextInt(1024 * 1024)) + "_" + ft.format(curdate) + ".jpg";
        String path;
        do {
            path = getServletContext().getRealPath(uploadToPath);
            uploadedFile = new File(path);
        } while (uploadedFile.exists());

        //создаём файл
        uploadedFile.createNewFile();
        //записываем в него данные
        item.write(uploadedFile);

        p.setPhotoUrl(uploadToPath);
    }

    /**
     * Выводит на консоль имя параметра и значение
     *
     * @param item
     */
    private void processUsernameField(FileItem item, User p) throws UnsupportedEncodingException {
        username = item.getString();
        p.setUsername(username);
    }

    private void processPasswordField(FileItem item, User p) throws UnsupportedEncodingException {
        password = item.getString();
        p.setPassword(password);
    }
}
