package pl.edu.wat.sportowcyapplication.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Achivment {
    @Id
    private String id;
    private String title;
    //@Indexed(unique = true)
    private String description;
    private String athlete;
    private List<String> type;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;

    public Achivment(String title,
                     String description,
                     String athlete,
                     List<String> type,
                     LocalDateTime created) {
        this.title = title;
        this.description = description;
        this.athlete = athlete;
        this.type = type;
        this.created = created;
    }

    public Achivment(String id) {
        this.id = id;
    }

    public Achivment() {

    }
}

