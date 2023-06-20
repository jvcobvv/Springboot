package pl.edu.wat.sportowcyapplication.service;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.wat.sportowcyapplication.dto.AthleteResponse;
import pl.edu.wat.sportowcyapplication.dto.AchivmentRequest;
import pl.edu.wat.sportowcyapplication.dto.AchivmentResponse;
import pl.edu.wat.sportowcyapplication.entity.Athlete;
import pl.edu.wat.sportowcyapplication.entity.Achivment;
import pl.edu.wat.sportowcyapplication.exception.EntityNotFound;
import pl.edu.wat.sportowcyapplication.repository.AthleteRepository;
import pl.edu.wat.sportowcyapplication.repository.AchivmentRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AchivmentService {
    private final AchivmentRepository achivmentRepository;
    private final AthleteRepository athleteRepository;

    @Autowired
    public AchivmentService(AchivmentRepository achivmentRepository, AthleteRepository athleteRepository) {
        this.achivmentRepository = achivmentRepository;
        this.athleteRepository = athleteRepository;
    }

    public List<Achivment> getAllAchivments() {
        return achivmentRepository.findAll();
    }

    public Achivment getAchivmentById(String id) throws EntityNotFound {
        return achivmentRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    @Transactional
    public String deleteById(String id) {
        achivmentRepository.deleteById(id);
        return "Delete";
    }

    public AchivmentResponse save(AchivmentRequest achivmentRequest) throws EntityNotFound {

        Achivment achivment = new Achivment();

        achivment.setTitle(achivmentRequest.getTitle());
        achivment.setDescription(achivmentRequest.getDescription());
        Athlete athlete = athleteRepository.findById(achivmentRequest.getAthleteId()).orElseThrow(EntityNotFound::new);
        achivment.setAthlete(athlete.getId());
        achivment.setType(achivmentRequest.getType());
        achivment.setCreated(LocalDateTime.now());
        achivment = achivmentRepository.save(achivment);
        return new AchivmentResponse(achivment.getId(), achivment.getTitle(), achivment.getDescription(), new AthleteResponse(athlete.getId(), athlete.getFirstName(), athlete.getLastName(), athlete.getMail()), achivment.getType(), achivment.getCreated());
    }

    public AchivmentResponse updateAchivment(String id, String title, String description) throws EntityNotFound {
        Achivment achivment = achivmentRepository.findById(id).orElseThrow(EntityNotFound::new);

        if (StringUtils.hasText(title)) {
            achivment.setTitle(title);
        }

        if (StringUtils.hasText(description)) {
            achivment.setDescription(description);
        }

        achivment = achivmentRepository.save(achivment);

        return new AchivmentResponse(achivment.getId(), achivment.getTitle(), achivment.getDescription());
    }

}
