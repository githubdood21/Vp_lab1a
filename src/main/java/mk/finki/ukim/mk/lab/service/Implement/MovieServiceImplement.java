package mk.finki.ukim.mk.lab.service.Implement;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplement implements MovieService {

    MovieRepository movieRepository;
    public MovieServiceImplement(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.searchMovies(text);
    }

    @Override
    public Movie findFirst(String text) {
        return movieRepository.firstorDefault(text);
    }
}
