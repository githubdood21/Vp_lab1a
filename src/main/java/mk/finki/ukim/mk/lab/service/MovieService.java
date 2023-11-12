package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    Movie findbyId(Long id);
    Movie findFirst(String text);
    public Movie saveMovie(String title, String summary, Double rating, Long productionId);
    public Movie editMovie(String title, String summary, Double rating, Long productionId, Long movieId);
    public void deleteMovie(Long id);
}
