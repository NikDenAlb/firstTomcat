package com.astonhome.firsttomcat.entity;

import jakarta.persistence.ManyToMany;
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
    @ManyToMany
    private List<User> users; //for many-to-many
}
