package pl.edu.wat.sportowcyapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AthleteResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String mail;

}
