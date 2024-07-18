package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.CoachUpdateDTO;
import com.astonhome.firsttomcat.entity.Coach;

public class CoachUpdateMapperImpl implements CoachUpdateMapper {
    @Override
    public Coach toEntity(CoachUpdateDTO coachUpdateDTO) {
        Coach coach = new Coach();
        coach.setId(coachUpdateDTO.getId());
        coach.setName(coachUpdateDTO.getName());
        return coach;
    }
}
