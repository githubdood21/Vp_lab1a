package mk.finki.ukim.mk.lab.model.exceptions;

public class InvalidMovieException extends RuntimeException{
    public InvalidMovieException() {
        super(String.format("Invalid Movie parameters"));
    }
}
