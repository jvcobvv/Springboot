package pl.edu.wat.sportowcyapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.wat.sportowcyapplication.entity.Achivment;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class AchivmentResponse {
    private String id;
    private String title;
    private String description;
    private AthleteResponse athlete;
    private List<String> type;
    private LocalDateTime created;

    public AchivmentResponse(Achivment updatedAchivment) {
    }

    public AchivmentResponse(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
