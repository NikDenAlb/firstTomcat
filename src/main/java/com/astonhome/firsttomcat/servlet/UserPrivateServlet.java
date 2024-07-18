package com.astonhome.firsttomcat.servlet;

import com.astonhome.firsttomcat.dto.UserPrivateDTO;
import com.astonhome.firsttomcat.service.CoachService;
import com.astonhome.firsttomcat.service.UserService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/users/private/*")
public class UserPrivateServlet extends HttpServlet {
    private UserService userService;
    private CoachService coachService;
    private Gson gson = new Gson();

    @Override
    public void init() {
        this.userService = new UserService();
        this.coachService = new CoachService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        response.setContentType("application/json");

        try {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            UserPrivateDTO userPrivateDTO = userService.getUserPrivateById(id);
            if (userPrivateDTO != null) {
                userPrivateDTO.setCoaches(coachService.getAllCoachByUserId(id));
                PrintWriter out = response.getWriter();
                String userJsonString = gson.toJson(userPrivateDTO);
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
