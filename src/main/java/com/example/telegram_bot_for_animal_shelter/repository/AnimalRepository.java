package com.example.telegram_bot_for_animal_shelter.repository;

import com.example.telegram_bot_for_animal_shelter.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
