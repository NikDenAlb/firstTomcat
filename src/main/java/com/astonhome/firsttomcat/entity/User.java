package com.astonhome.firsttomcat.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
}