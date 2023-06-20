package pl.edu.wat.sportowcyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.wat.sportowcyapplication.dto.*;
import pl.edu.wat.sportowcyapplication.entity.Achivment;
import pl.edu.wat.sportowcyapplication.exception.EntityNotFound;
import pl.edu.wat.sportowcyapplication.service.AthleteService;
import pl.edu.wat.sportowcyapplication.service.AchivmentService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("api/v1/achivment")
public class AchivmentController {
    //skrot klawiszowy na ctrl + alt + l
    private final AchivmentService achivmentService;
    private final AthleteService athleteService;

    @Autowired
    public AchivmentController(AchivmentService achivmentService, AthleteService athleteService) {
        this.achivmentService = achivmentService;
        this.athleteService = athleteService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<Achivment>> fetchAllAchivments() {
        return new ResponseEntity<>(achivmentService.getAllAchivments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Achivment> achivmentById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(achivmentService.getAchivmentById(id), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AchivmentResponse> updateAthlete(@PathVariable String id, @RequestBody AchivmentRequest achivmentRequest) {
        try {
            return new ResponseEntity<>(achivmentService.updateAchivment(id, achivmentRequest.getTitle(), achivmentRequest.getDescription()), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<AchivmentRequest> createAthlete(@RequestBody AchivmentRequest achivmentRequest) {
        try {
            AchivmentResponse achivmentResponse = achivmentService.save(achivmentRequest);
            return new ResponseEntity<>(achivmentRequest, HttpStatus.CREATED);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/del/{id}")
    public ResponseEntity delUser(@PathVariable String id) {
        achivmentService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }//tego nie musze bardziej szczegolowo juz poprawiac

    @GetMapping("/with-athlete")
    public List<AchivmentWithAthlete> fetchAllAchivmentsWithAthletes() {
        List<Achivment> achivments = achivmentService.getAllAchivments();
        return achivments.stream()
                .map(achivment -> {
                    try {
                        String athleteId = achivment.getAthlete();
                        AthleteResponse athleteObject = new AthleteResponse(athleteId, athleteService.getAthleteById(athleteId).getFirstName(), athleteService.getAthleteById(athleteId).getLastName(), athleteService.getAthleteById(athleteId).getMail());
                        return new AchivmentWithAthlete(achivment.getId(), achivment.getTitle(), achivment.getDescription(), athleteObject);
                    } catch (EntityNotFound e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }




}
