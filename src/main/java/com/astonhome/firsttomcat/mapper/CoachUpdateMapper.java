package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.CoachUpdateDTO;
import com.astonhome.firsttomcat.entity.Coach;
import org.mapstruct.factory.Mappers;


public interface CoachUpdateMapper {
    CoachUpdateMapper INSTANCE = Mappers.getMapper(CoachUpdateMapper.class);

    Coach toEntity(CoachUpdateDTO coachUpdateDTO);
}