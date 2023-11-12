package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Movie {
    public Movie (String title, String summary, Double rating, Production production)
    {
        this.id = IdCount;
        IdCount++;
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }
    private Long id;
    private static Long IdCount = 0L;
    private String title;
    private String summary;
    private Double rating;
    private Production production;//to be set
}
