package mk.finki.ukim.mk.lab.service.Implement;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidMovieException;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplement implements MovieService {

    private final ProductionRepository productionRepository;
    MovieRepository movieRepository;
    public MovieServiceImplement(MovieRepository movieRepository, ProductionRepository productionRepository)
    {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie saveMovie(String title, String summary, Double rating, Long productionId) throws InvalidMovieException {
        if(title.isEmpty() || summary.isEmpty() || rating.isNaN() || productionId == null)
        {
            throw new InvalidMovieException();
        }
        Movie m = new Movie(title, summary, rating, productionRepository.findbyId(productionId).get());
        movieRepository.saveMovie(m);
        return m;
    }

    @Override
    public Movie findbyId(Long id) {
        return movieRepository.getbyId(id);
    }

    @Override
    public Movie editMovie(String title, String summary, Double rating, Long productionId, Long movieId) throws InvalidMovieException {
        if(title.isEmpty() || summary.isEmpty() || rating.isNaN() || productionId == null || movieId == null)
        {
            throw new InvalidMovieException();
        }
        Movie m = movieRepository.getbyId(movieId);
        m.setTitle(title);
        m.setSummary(summary);
        m.setRating(rating);
        m.setProduction(productionRepository.findbyId(productionId).get());
        movieRepository.editMovie(m);
        return m;
    }

    @Override
    public void deleteMovie(Long id) throws NullPointerException {
        if(id == null)
        {
            throw new NullPointerException();
        }
        Movie m = movieRepository.getbyId(id);
        movieRepository.deleteMovie(m);
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
