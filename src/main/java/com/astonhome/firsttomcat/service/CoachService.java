package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.dto.CoachUpdateDTO;
import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.mapper.CoachMapper;
import com.astonhome.firsttomcat.mapper.CoachUpdateMapper;
import com.astonhome.firsttomcat.repository.CoachDAO;

import java.util.List;
import java.util.stream.Collectors;

public class CoachService {
    private final CoachDAO coachDAO = new CoachDAO();

    public CoachDTO getCoachById(Long id) {
        Coach coach = coachDAO.getCoach(id);
        if (coach == null) {
            return null;
        }
        return CoachMapper.INSTANCE.toDTO(coach);
    }

    public List<CoachDTO> getAllCoaches() {
        List<Coach> coaches = coachDAO.getAllCoaches();
        return coaches.stream().map(CoachMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public CoachDTO saveCoach(CoachDTO coachDTO) {
        Coach coach = CoachMapper.INSTANCE.toEntity(coachDTO);
        return CoachMapper.INSTANCE.toDTO(coachDAO.addCoach(coach));
    }

    public void updateCoach(CoachUpdateDTO coachUpdateDTO) {
        Coach coach = CoachUpdateMapper.INSTANCE.toEntity(coachUpdateDTO);
        coachDAO.updateCoach(coach.getId(), coach);
    }

    public CoachDTO deleteCoach(Long id) {
        return CoachMapper.INSTANCE.toDTO(coachDAO.deleteCoach(id));
    }

    public List<CoachDTO> getAllCoachByUserId(long id) {
        return coachDAO.getAllCoachByUserId(id).stream().map(CoachMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}