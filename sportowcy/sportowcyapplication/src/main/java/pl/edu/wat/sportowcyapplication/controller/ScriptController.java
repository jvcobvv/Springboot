package pl.edu.wat.sportowcyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.sportowcyapplication.repository.AthleteRepository;
import pl.edu.wat.sportowcyapplication.repository.AchivmentRepository;
import pl.edu.wat.sportowcyapplication.service.ScriptService;


@RestController
@CrossOrigin
@RequestMapping("/api/script")
public class ScriptController {

    private final ScriptService scriptService;


    @Autowired
    public ScriptController(ScriptService scriptService, AthleteRepository athleteRepository, AchivmentRepository achivmentRepository) {
        this.scriptService = scriptService;

    }



    @PostMapping("/add-mail")
    public ResponseEntity<String> addMail() {
        String script = """
                    var Athlete = Java.type('pl.edu.wat.sportowcyapplication.entity.Athlete');
                    function addMail() {
                        
                        for (athlete of athleteRepository.findAll()) {
                                var athleteFirstName = athlete.getFirstName();
                                var athleteLastName = athlete.getLastName();
                                var mail = athleteFirstName+athleteLastName+'@mail.com';
                                athlete.setMail(mail);
                                athleteRepository.save(athlete);
                                
                      
                        }
                        return athleteRepository.findAll();
                  
                    }
                    addMail();
                """;
       

        return new ResponseEntity<>(scriptService.exec(script), HttpStatus.OK);
    }



}

