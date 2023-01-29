package controller;

import java.util.Collection;

import model.Volunteer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VolunteerService;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping()
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer addVolunteer = volunteerService.addVolunteer(volunteer);
        return ResponseEntity.ok(addVolunteer);
    }

    @GetMapping("all_volunteer")
    public ResponseEntity<Collection<Volunteer>> getAllVolunteer() {
        return ResponseEntity.ok(volunteerService.getAllVolunteer());
    }
}
