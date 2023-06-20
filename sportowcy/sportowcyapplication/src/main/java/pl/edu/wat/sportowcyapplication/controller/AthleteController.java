package pl.edu.wat.sportowcyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.sportowcyapplication.dto.AthleteRequest;
import pl.edu.wat.sportowcyapplication.dto.AthleteResponse;
import pl.edu.wat.sportowcyapplication.entity.Athlete;
import pl.edu.wat.sportowcyapplication.service.AthleteService;

import java.util.List;

@RestController
@RequestMapping("api/v1/athlete")
public class AthleteController {

    private final AthleteService authleteService;

    @Autowired
    public AthleteController(AthleteService authleteService) {
        this.authleteService = authleteService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<Athlete>> fetchAllAthlete() {
        return new ResponseEntity<>(authleteService.getAllAthlete(), HttpStatus.OK);
    }

    @PostMapping("/add")//musze zwrocic authleteRequest
    public ResponseEntity<AthleteRequest> createAthlete(@RequestBody AthleteRequest authleteRequest) {
        AthleteResponse athleteResponse = authleteService.save(authleteRequest);
        return new ResponseEntity<>(authleteRequest, HttpStatus.CREATED);
    }


}
