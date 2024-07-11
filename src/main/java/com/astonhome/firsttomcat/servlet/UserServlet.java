package com.astonhome.firsttomcat.servlet;

import com.astonhome.firsttomcat.dao.UserDAO;
import com.astonhome.firsttomcat.entity.User;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        response.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            // GET /users - Получить всех пользователей
            List<User> users = UserDAO.getAllUsers();
            PrintWriter out = response.getWriter();
            String usersJsonString = gson.toJson(users);
            out.print(usersJsonString);
            out.flush();
        } else {
            // GET /users/{id} - Получить пользователя
            try {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                User user = UserDAO.getUser(id);
                if (user != null) {
                    PrintWriter out = response.getWriter();
                    String userJsonString = gson.toJson(user);
                    out.print(userJsonString);
                    out.flush();
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = gson.fromJson(request.getReader(), User.class);
        User newUser = UserDAO.addUser(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(newUser));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            int id = Integer.parseInt(pathInfo.split("/")[1]);
            User user = gson.fromJson(request.getReader(), User.class);
            User updatedUser = UserDAO.updateUser(id, user);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(updatedUser));
            out.flush();
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            int id = Integer.parseInt(pathInfo.split("/")[1]);
            User user = UserDAO.deleteUser(id);
            if (user != null) {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(gson.toJson(user));
                out.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

