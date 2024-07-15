package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.dto.UserDTO;
import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoachMapper {
    CoachMapper INSTANCE = Mappers.getMapper(CoachMapper.class);

    UserDTO coachToCoachDTO(Coach coach);

    User coachDTOToCoach(CoachDTO coachDTO);
}