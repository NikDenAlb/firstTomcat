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
    //ManytoMany
    private List<Coach> coaches; //for many-to-many
//    private String health; -только геттером//for privacy
//    private List<Contact> contact; //for one-to-many - контакт обязательно класс(табличка)
}