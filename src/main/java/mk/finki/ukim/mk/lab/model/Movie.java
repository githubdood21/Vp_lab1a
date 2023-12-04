package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    public Movie (String title, String summary, Double rating, Production production)
    {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private Double rating;
    @ManyToOne
    private Production production;//to be set
    public Movie() {

    }
}
