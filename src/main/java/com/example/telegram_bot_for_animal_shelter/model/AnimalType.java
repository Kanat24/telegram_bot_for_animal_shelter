package com.example.telegram_bot_for_animal_shelter.model;

public enum AnimalType {

    CAT("Кошка"),
    DOG("Собака"),
    Bird("Птица");
    private final String type;

    AnimalType(String type) {
        this.type = type;
    }
}

