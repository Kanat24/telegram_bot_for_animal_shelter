package com.example.telegram_bot_for_animal_shelter.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private PersonStatus personStatus;
    @OneToMany
    private Set <Animal> animalSet;


}
