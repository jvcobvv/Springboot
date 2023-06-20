package pl.edu.wat.sportowcyapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Athlete {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String mail;

    public Athlete() {

    }


}

