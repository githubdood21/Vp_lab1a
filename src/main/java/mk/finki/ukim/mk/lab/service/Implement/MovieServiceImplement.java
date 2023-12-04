package mk.finki.ukim.mk.lab.service.Implement;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidMovieException;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepository;
import mk.finki.ukim.mk.lab.repository.old.InMemoryMovieRepository;
import mk.finki.ukim.mk.lab.repository.old.InMemoryProductionRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplement implements MovieService {

    private final ProductionRepository productionRepository;
    private final MovieRepository movieRepository;
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
    @Transactional
    public Movie saveMovie(String title, String summary, Double rating, Long productionId) throws InvalidMovieException {
        if(title.isEmpty() || summary.isEmpty() || rating.isNaN() || productionId == null)
        {
            throw new InvalidMovieException();
        }
        Movie m = new Movie(title, summary, rating, productionRepository.findById(productionId).get());
        movieRepository.save(m);
        return m;
    }

    @Override
    public Movie findbyId(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Movie editMovie(String title, String summary, Double rating, Long productionId, Long movieId) throws InvalidMovieException {
        if(title.isEmpty() || summary.isEmpty() || rating.isNaN() || productionId == null || movieId == null)
        {
            throw new InvalidMovieException();
        }
        Movie m = movieRepository.findById(movieId).get();
        m.setTitle(title);
        m.setSummary(summary);
        m.setRating(rating);
        m.setProduction(productionRepository.findById(productionId).get());
        movieRepository.save(m);
        return m;
    }

    @Override
    @Transactional
    public void deleteMovie(Long id) throws NullPointerException {
        if(id == null)
        {
            throw new NullPointerException();
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.findAllByTitleContaining(text);
    }

    @Override
    public Movie findFirst(String text) {
        return movieRepository.findFirstByTitle(text);
    }
}
