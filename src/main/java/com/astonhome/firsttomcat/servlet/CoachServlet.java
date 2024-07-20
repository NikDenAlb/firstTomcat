package com.astonhome.firsttomcat.servlet;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.dto.CoachUpdateDTO;
import com.astonhome.firsttomcat.repository.CoachDAO;
import com.astonhome.firsttomcat.repository.UserDAO;
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
import java.util.List;

@WebServlet("/coaches/*")
public class CoachServlet extends HttpServlet {
    private CoachService coachService;
    private UserService userService;
    private Gson gson = new Gson();

    @Override
    public void init() {
        this.coachService = new CoachService(new CoachDAO());
        this.userService = new UserService(new UserDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        response.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            List<CoachDTO> coaches = coachService.getAllCoaches();
            PrintWriter out = response.getWriter();
            String coachesJsonString = gson.toJson(coaches);
            out.print(coachesJsonString);
            out.flush();
        } else {
            try {
                long id = Long.parseLong(pathInfo.split("/")[1]);
                CoachDTO coachDTO = coachService.getCoachById(id);
                if (coachDTO != null) {
                    coachDTO.setUsers(userService.getAllUserByCoachId(id));
                    PrintWriter out = response.getWriter();
                    String coacJsonString = gson.toJson(coachDTO);
                    out.print(coacJsonString);
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
        CoachDTO coachDTO = gson.fromJson(request.getReader(), CoachDTO.class);
        CoachDTO newCoachDTO = coachService.saveCoach(coachDTO);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(newCoachDTO));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            if (coachService.getCoachById(id) == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            CoachUpdateDTO coachUpdateDTO = gson.fromJson(request.getReader(), CoachUpdateDTO.class);
            coachUpdateDTO.setId(id);
            coachService.updateCoach(coachUpdateDTO);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(coachUpdateDTO));
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
            if (coachService.getCoachById(id) == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            CoachDTO coachDTO = coachService.deleteCoach(id);
            if (coachDTO != null) {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(gson.toJson(coachDTO));
                out.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}