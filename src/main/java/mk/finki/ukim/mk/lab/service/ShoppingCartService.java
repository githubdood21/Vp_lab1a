package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService {
    List<TicketOrder> listAllTicketOrdersInShoppingCart(Long cartId);
    ShoppingCart getShoppingCart(String username, Long cartId);
    ShoppingCart addTicketOrderToShoppingCart(String username, Long cartId, TicketOrder ticketOrder);
    ShoppingCart addShoppingCart(User user, LocalDateTime date);
    ShoppingCart editShoppingCart(User user, LocalDateTime date, Long id);
    void deleteShoppingCart(User user, Long id);
}
