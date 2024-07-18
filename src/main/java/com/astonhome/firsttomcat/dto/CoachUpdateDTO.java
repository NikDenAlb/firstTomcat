package com.astonhome.firsttomcat.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CoachUpdateDTO {
    private Long id;
    private String name;
}