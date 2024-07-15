package com.astonhome.firsttomcat.servlet;

import com.astonhome.firsttomcat.dto.UserDTO;
import com.astonhome.firsttomcat.service.UserService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    private UserService userService;
    private Gson gson = new Gson();

    @Override
    public void init() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        response.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            List<UserDTO> users = userService.getAllUsers();
            PrintWriter out = response.getWriter();
            String usersJsonString = gson.toJson(users);
            out.print(usersJsonString);
            out.flush();
        } else {
            try {
                long id = Long.parseLong(pathInfo.split("/")[1]);
                UserDTO user = userService.getUserById(id);
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
        UserDTO userDTO = gson.fromJson(request.getReader(), UserDTO.class);
        userService.saveUser(userDTO);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(userDTO));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            UserDTO userDTO = gson.fromJson(request.getReader(), UserDTO.class);
            userDTO.setId(id);
            userService.updateUser(userDTO);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(userDTO));
            out.flush();
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            UserDTO userDTO = userService.deleteUser(id);
            if (userDTO != null) {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(gson.toJson(userDTO));
                out.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}