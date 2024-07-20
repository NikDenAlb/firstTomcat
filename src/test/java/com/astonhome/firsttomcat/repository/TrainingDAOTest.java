package com.astonhome.firsttomcat.repository;

import com.astonhome.firsttomcat.dto.TrainingDTO;
import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainingDAOTest {
    private TrainingDAO trainingDAO;
    private CoachDAO coachDAO;
    private UserDAO userDAO;

    private Coach coach;
    private User user;
    private TrainingDTO trainingDTO;


    @BeforeEach
    public void setUp() {
        trainingDAO = new TrainingDAO();
        userDAO = new UserDAO();
        coachDAO = new CoachDAO();
        user = new User(1L, "username1", null, null);
        coach = new Coach(1L, "couchname1", null);
        trainingDTO = new TrainingDTO();
    }


    @Test
    void checkTraining() {
        user = userDAO.addUser(user);
        coach = coachDAO.addCoach(coach);

        trainingDTO.setUser(user.getId());
        trainingDTO.setCoach(coach.getId());

        assertFalse(trainingDAO.checkTraining(trainingDTO));
        trainingDAO.setTraining(trainingDTO);
        assertTrue(trainingDAO.checkTraining(trainingDTO));
    }

    @Test
    void setTraining() {
        user = userDAO.addUser(user);
        coach = coachDAO.addCoach(coach);

        trainingDTO.setUser(user.getId());
        trainingDTO.setCoach(coach.getId());

        coach.setName("newName");
        coach = coachDAO.addCoach(coach);

        assertFalse(trainingDAO.checkTraining(trainingDTO));
        trainingDAO.setTraining(trainingDTO);
        assertTrue(trainingDAO.checkTraining(trainingDTO));
        trainingDTO.setCoach(coach.getId());
        assertFalse(trainingDAO.checkTraining(trainingDTO));
    }

    @Test
    void deleteTraining() {
        user = userDAO.addUser(user);
        coach = coachDAO.addCoach(coach);

        trainingDTO.setUser(user.getId());
        trainingDTO.setCoach(coach.getId());

        coach.setName("newName");
        coach = coachDAO.addCoach(coach);

        trainingDAO.setTraining(trainingDTO);
        assertTrue(trainingDAO.checkTraining(trainingDTO));
        trainingDAO.deleteTraining(trainingDTO);
        assertFalse(trainingDAO.checkTraining(trainingDTO));
    }
}