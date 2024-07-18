package com.astonhome.firsttomcat.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserUpdateDTO {
    private Long id;
    private String name;
}