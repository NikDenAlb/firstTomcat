package com.astonhome.firsttomcat.servlet;

import com.astonhome.firsttomcat.repository.CoachDAO;
import com.astonhome.firsttomcat.entity.Coach;
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
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        response.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Coach> coaches = CoachDAO.getAllCoaches();
            PrintWriter out = response.getWriter();
            String coachesJsonString = gson.toJson(coaches);
            out.print(coachesJsonString);
            out.flush();
        } else {
            try {
                long id = Long.parseLong(pathInfo.split("/")[1]);
                Coach coach = CoachDAO.getCoach(id);
                if (coach != null) {
                    PrintWriter out = response.getWriter();
                    String userJsonString = gson.toJson(coach);
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
        Coach coach = gson.fromJson(request.getReader(), Coach.class);
        Coach newCoach = CoachDAO.addCoach(coach);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(newCoach));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            Coach coach = gson.fromJson(request.getReader(), Coach.class);
            Coach updatedCoach = CoachDAO.updateCoach(id, coach);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(updatedCoach));
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
            Coach coach = CoachDAO.deleteCoach(id);
            if (coach != null) {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(gson.toJson(coach));
                out.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}