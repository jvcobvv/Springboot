package pl.edu.wat.sportowcyapplication.dto;

import lombok.Data;

@Data
public class AchivmentWithAthlete {
    private String id;
    private String title;
    private String description;
    private AthleteResponse athleteObject;

    public AchivmentWithAthlete(String id, String title, String description, AthleteResponse athleteObject) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.athleteObject = athleteObject;
    }
}