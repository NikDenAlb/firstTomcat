package com.astonhome.firsttomcat.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Coach {
    private Long id;
    private String name;
    private List<String> scope; //for one-to-many
    private List<User> users; //for many-to-many
}
