package mk.finki.ukim.mk.lab.service.Implement;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AuthenticationServiceImplement implements AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private boolean CredentialsNotValid(String username, String password)
    {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }
    @Override
    public User login(String username, String password) {
        if(CredentialsNotValid(username, password))
        {
            //throw error
            return null;
        }
        return userRepository.findByUsernameAndPassword(username, password);
    }
    @Override
    @Transactional
    public User register(String username, String name, String surname, String password, String repeatpassword, LocalDate dateOfBirth) {
        if(CredentialsNotValid(username, password))
        {
            //throw error
            return null;
        }
        if(!password.equals(repeatpassword))
        {
            //throw error
            return null;
        }
        User user = new User(username, name, surname, password, dateOfBirth);
        userRepository.saveAndFlush(user);
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
