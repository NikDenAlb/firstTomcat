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
    //@ManyToMany
    private List<User> users;
}
