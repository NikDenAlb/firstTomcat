package com.astonhome.firsttomcat.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserPrivateDTO {
    private Long id;
    private String name;
    private List<CoachDTO> coaches;
    private String health;
}