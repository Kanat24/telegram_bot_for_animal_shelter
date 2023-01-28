package com.example.telegram_bot_for_animal_shelter.repository;

import com.example.telegram_bot_for_animal_shelter.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
