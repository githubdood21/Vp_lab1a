package mk.finki.ukim.mk.lab.repository.old;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryMovieRepository {

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
    public Movie getbyId(Long id)
    {
        return InMemoryDB.movieList.stream().filter(x -> x.getId().equals(id)).findFirst().get();
    }
    public Movie saveMovie(Movie movie)
    {
        if(InMemoryDB.movieList.stream().anyMatch(x -> x.getId().equals(movie.getId())))
        {
            InMemoryDB.movieList.removeIf(x -> x.getId().equals(movie.getId()));
        }
        InMemoryDB.movieList.add(movie);
        return movie;
    }
    public Movie editMovie(Movie movie)
    {
        Movie m = null;
        if(InMemoryDB.movieList.stream().anyMatch(x -> x.getId().equals(movie.getId())))
        {
            m = InMemoryDB.movieList.stream().filter(x -> x.getId().equals(movie.getId())).findFirst().get();
        }
        m = movie;
        return movie;
    }
    public void deleteMovie(Movie movie)
    {
        InMemoryDB.movieList.removeIf(x -> x.getId().equals(movie.getId()));
    }
}
