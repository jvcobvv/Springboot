package pl.edu.wat.sportowcyapplication.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AchivmentRequest {
    private String title;
    private String description;
    private String athleteId;
    private List<String> type;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;
}
