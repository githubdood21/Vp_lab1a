package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;

public interface TicketOrderService {
    TicketOrder placeOrder(String movieTitle, int numberOfTickets, User user);
    TicketOrder findById(Long Id);
}
