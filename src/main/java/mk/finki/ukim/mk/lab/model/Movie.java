package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Movie {
    public Movie (String title, String summary, Double rating)
    {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
    }
    private String title;
    private String summary;
    private Double rating;
}
