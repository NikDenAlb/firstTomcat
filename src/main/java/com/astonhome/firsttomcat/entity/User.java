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
    //@ManyToMany
    private List<Coach> coaches;
    private String health;
//    private List<Contact> contact; //for one-to-many - контакт обязательно класс(табличка)
}