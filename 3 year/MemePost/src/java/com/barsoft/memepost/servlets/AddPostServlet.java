package com.barsoft.memepost.servlets;

import com.barsoft.memepost.entities.Post;
import com.barsoft.memepost.entities.User;
import com.barsoft.memepost.entities.Users;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

public class AddPostServlet extends HttpServlet {

    private Random random = new Random();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //проверяем является ли полученный запрос multipart/form-data
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

        Post p = new Post();

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();



            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //если принимаемая часть данных является полем формы			    	
                    if (item.getString() != null && !item.getString().equals("")) {
                        processFormField(item, p);

                    } else {
                        request.setAttribute("message", "Title shouldn't be empty!");
                        final RequestDispatcher rd = getServletContext().getRequestDispatcher("/protected/addpost.jsp");
                        rd.forward(request, response);
                        return;
                    }
                } else {
                    //в противном случае рассматриваем как файл
                    if (!item.getName().contains(".jpg") && !item.getName().contains(".png") && !item.getName().contains(".bmp") && !item.getName().contains(".jpeg")) {
                        request.setAttribute("message", "Only .png, .jpg, .jpeg, .bmp. files allowed!");
                        final RequestDispatcher rd = getServletContext().getRequestDispatcher("/protected/addpost.jsp");
                        rd.forward(request, response);
                        return;
                    } else if (item.getName() != "") {
                        processUploadedFile(item, p);

                    } else {
                        request.setAttribute("message", "No file selected!");

                        final RequestDispatcher rd = getServletContext().getRequestDispatcher("/protected/addpost.jsp");
                        rd.forward(request, response);

                        return;
                    }
                }
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        User curuser = (User) request.getSession().getAttribute("curuser");
        Users users = (Users) this.getServletContext().getAttribute("users");
        ((List<Post>) users.getUsers().get(curuser)).add(p);

        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }

    /**
     * Сохраняет файл на сервере, в папке upload. Сама папка должна быть уже
     * создана.
     *
     * @param item
     * @throws Exception
     */
    private void processUploadedFile(FileItem item, Post p) throws Exception {
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

        p.setMemUrl(uploadToPath);
        p.setDate(Calendar.getInstance().getTime());
    }

    /**
     * Выводит на консоль имя параметра и значение
     *
     * @param item
     */
    private void processFormField(FileItem item, Post p) {
        p.setText(item.getString());
    }
}
