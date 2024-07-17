package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.entity.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoachMapper {
    CoachMapper INSTANCE = Mappers.getMapper(CoachMapper.class);

    CoachDTO toDTO(Coach coach);

    Coach toEntity(CoachDTO coachDTO);
}