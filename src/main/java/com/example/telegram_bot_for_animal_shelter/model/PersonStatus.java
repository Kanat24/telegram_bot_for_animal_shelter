package com.example.telegram_bot_for_animal_shelter.model;

public enum PersonStatus {

    NEW_PERSON("Новый клиент"),
    POTENTIAL_PARENT("Потенциальный усыновитель"),
    PARENT("Усыновитель");

    private final String status;

    PersonStatus(String status) {
        this.status = status;
    }
}
