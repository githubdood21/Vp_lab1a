package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface AuthenticationService {
    User login(String username, String password);
    User register(String username, String name, String surname, String password, String repeatpassword, LocalDate dateOfBirth);
    User findByUsername(String username);
}
