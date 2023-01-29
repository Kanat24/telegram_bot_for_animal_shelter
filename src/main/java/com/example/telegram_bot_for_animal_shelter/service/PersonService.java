package com.example.telegram_bot_for_animal_shelter.service;

import com.example.telegram_bot_for_animal_shelter.exception.PersonNotFoundException;
import com.example.telegram_bot_for_animal_shelter.model.Person;
import com.example.telegram_bot_for_animal_shelter.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

   // public Person getPerson (String phoneNumber) {
   //     return personRepository.findByPhoneNumber(phoneNumber).
   //             orElseThrow(PersonNotFoundException::new);
   // }
}
