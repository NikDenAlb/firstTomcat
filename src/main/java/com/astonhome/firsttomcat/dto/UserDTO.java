package com.astonhome.firsttomcat.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private List<CoachDTO> coaches;
}