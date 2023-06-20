package pl.edu.wat.sportowcyapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.sportowcyapplication.entity.Achivment;

import java.time.LocalDateTime;
import java.util.List;


public interface AchivmentRepository extends MongoRepository<Achivment, String> {
    List<Achivment> findByCreatedBefore(LocalDateTime date); //js
}
