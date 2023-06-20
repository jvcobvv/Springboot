package pl.edu.wat.sportowcyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.sportowcyapplication.dto.AthleteRequest;
import pl.edu.wat.sportowcyapplication.dto.AthleteResponse;
import pl.edu.wat.sportowcyapplication.entity.Athlete;
import pl.edu.wat.sportowcyapplication.exception.EntityNotFound;
import pl.edu.wat.sportowcyapplication.repository.AthleteRepository;

import java.util.List;


@Service
public class AthleteService {

    private final AthleteRepository athleteRepository;

    @Autowired
    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    public List<Athlete> getAllAthlete() {
        return athleteRepository.findAll();
    }

    public Athlete getAthleteById(String id) throws EntityNotFound {
        return athleteRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public AthleteResponse save(AthleteRequest athleteRequest) {
        Athlete athlete = new Athlete();
        athlete.setFirstName(athleteRequest.getFirstName());
        athlete.setLastName(athleteRequest.getLastName());
        athlete = athleteRepository.save(
                athlete
        );
        return new AthleteResponse(athlete.getId(), athlete.getFirstName(), athlete.getLastName(), athlete.getMail());
    }


}

