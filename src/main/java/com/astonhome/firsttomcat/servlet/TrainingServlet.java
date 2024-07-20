package com.astonhome.firsttomcat.servlet;

import com.astonhome.firsttomcat.dto.TrainingDTO;
import com.astonhome.firsttomcat.repository.CoachDAO;
import com.astonhome.firsttomcat.repository.TrainingDAO;
import com.astonhome.firsttomcat.repository.UserDAO;
import com.astonhome.firsttomcat.service.CoachService;
import com.astonhome.firsttomcat.service.TrainingService;
import com.astonhome.firsttomcat.service.UserService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/training/*")
public class TrainingServlet extends HttpServlet {
    private UserService userService;
    private CoachService coachService;
    private TrainingService trainingService;
    private Gson gson = new Gson();

    @Override
    public void init() {
        this.userService = new UserService(new UserDAO());
        this.coachService = new CoachService(new CoachDAO());
        this.trainingService = new TrainingService(new TrainingDAO());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TrainingDTO trainingDTO = gson.fromJson(request.getReader(), TrainingDTO.class);
        if (userService.getUserById(trainingDTO.getUser()) == null || coachService.getCoachById(trainingDTO.getCoach()) == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (trainingService.checkTraining(trainingDTO)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }
        trainingService.setTraining(trainingDTO);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(trainingDTO));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TrainingDTO trainingDTO = gson.fromJson(request.getReader(), TrainingDTO.class);
        if (!trainingService.checkTraining(trainingDTO)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        try {
            trainingService.deleteTraining(trainingDTO);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(trainingDTO));
            out.flush();
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}