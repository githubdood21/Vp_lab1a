package mk.finki.ukim.mk.lab.service.Implement;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.*;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.jpa.TicketOrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImplement implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final TicketOrderRepository ticketOrderRepository;

    public ShoppingCartServiceImplement(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, MovieRepository movieRepository, TicketOrderRepository ticketOrderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public List<TicketOrder> listAllTicketOrdersInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(cartId).get();
        return shoppingCart.getTicketOrders();
    }

    @Override
    public ShoppingCart getShoppingCart(String username, Long cartId) {
        return this.shoppingCartRepository.findByUserAndId(userRepository.findByUsername(username), cartId);
    }

    @Override
    @Transactional
    public ShoppingCart addTicketOrderToShoppingCart(String username, Long cartId, TicketOrder ticketOrder) {
        ShoppingCart cart = this.getShoppingCart(username, cartId);
        cart.getTicketOrders().add(ticketOrder);
        return cart;
    }

    @Override
    @Transactional
    public void deleteShoppingCart(User user, Long id) {
        ShoppingCart cart = shoppingCartRepository.findByUserAndId(user, id);
        ticketOrderRepository.deleteAll(cart.getTicketOrders());
        shoppingCartRepository.delete(cart);
        shoppingCartRepository.flush();
    }

    @Override
    @Transactional
    public ShoppingCart editShoppingCart(User user, LocalDateTime date, Long id) {
        ShoppingCart cart = shoppingCartRepository.findByUserAndId(user, id);
        cart.setDateCreated(date);
        shoppingCartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    @Transactional
    public ShoppingCart addShoppingCart(User user, LocalDateTime date) {
        return shoppingCartRepository.saveAndFlush(new ShoppingCart(user, date));
    }
}
