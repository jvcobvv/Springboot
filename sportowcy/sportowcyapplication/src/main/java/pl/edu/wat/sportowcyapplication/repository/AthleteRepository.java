package pl.edu.wat.sportowcyapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.sportowcyapplication.entity.Athlete;

public interface AthleteRepository extends MongoRepository<Athlete, String> {
}

