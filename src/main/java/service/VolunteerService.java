package service;

import model.Volunteer;
import org.springframework.stereotype.Service;
import repository.VolunteerRepository;

import java.util.Collection;

@Service
public class VolunteerService {

    private VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public Volunteer addVolunteer(Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

    public Collection<Volunteer> getAllVolunteer(){
        return volunteerRepository.findAll();
    }
}