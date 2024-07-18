package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.entity.Coach;

public class CoachMapperImpl implements CoachMapper {
    @Override
    public CoachDTO toDTO(Coach coach) {
        CoachDTO coachDTO = new CoachDTO();
        coachDTO.setId(coach.getId());
        coachDTO.setName(coach.getName());
        return coachDTO;
    }

    @Override
    public Coach toEntity(CoachDTO coachDTO) {
        Coach coach = new Coach();
        coach.setId(coachDTO.getId());
        coach.setName(coachDTO.getName());
        return coach;
    }
}