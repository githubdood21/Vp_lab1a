package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    public List<Movie> findAll()
    {
        return InMemoryDB.movieList;
    }
    public Movie firstorDefault(String text)
    {
        return InMemoryDB.movieList.stream().filter(x -> x.getTitle().equals(text)).findFirst().get();
    }
    public List<Movie> searchMovies(String text)
    {
        return InMemoryDB.movieList.stream().filter(x -> x.getTitle().contains(text) || x.getSummary().contains(text)).collect(Collectors.toList());
    }
}
