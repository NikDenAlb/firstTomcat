package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.mapper.CoachMapper;
import com.astonhome.firsttomcat.repository.CoachDAO;

import java.util.List;
import java.util.stream.Collectors;

public class CoachService {

    public CoachDTO getCoachById(Long id) {
        Coach coach = CoachDAO.getCoach(id);
        if (coach == null) {
            return null;
        }
        return CoachMapper.INSTANCE.toDTO(coach);
    }

    public List<CoachDTO> getAllCoaches() {
        List<Coach> coaches = CoachDAO.getAllCoaches();
        return coaches.stream().map(CoachMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public CoachDTO saveCoach(CoachDTO coachDTO) {
        Coach coach = CoachMapper.INSTANCE.toEntity(coachDTO);
        return CoachMapper.INSTANCE.toDTO(CoachDAO.addCoach(coach));
    }

    public void updateCoach(CoachDTO coachDTO) {
        Coach coach = CoachMapper.INSTANCE.toEntity(coachDTO);
        CoachDAO.updateCoach(coach.getId(), coach);
    }

    public CoachDTO deleteCoach(Long id) {
        return CoachMapper.INSTANCE.toDTO(CoachDAO.deleteCoach(id));
    }
}