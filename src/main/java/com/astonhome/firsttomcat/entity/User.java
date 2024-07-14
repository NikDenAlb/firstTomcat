package com.astonhome.firsttomcat.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private List<String> contact; //for one-to-many
    private List<Coach> coaches; //for many-to-many
    private String health; //for privacy
}