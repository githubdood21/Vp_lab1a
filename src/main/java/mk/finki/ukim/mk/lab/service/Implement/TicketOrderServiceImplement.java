package mk.finki.ukim.mk.lab.service.Implement;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.TicketOrderRepository;
import mk.finki.ukim.mk.lab.repository.old.InMemoryTicketOrderRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderServiceImplement implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImplement(TicketOrderRepository ticketOrderRepository)
    {
        this.ticketOrderRepository = ticketOrderRepository;
    }
    @Override
    @Transactional
    public TicketOrder placeOrder(String movieTitle, int numberOfTickets, User user) {
        if(numberOfTickets < 1)
        {
            return null;
        }
        TicketOrder t = new TicketOrder(movieTitle, Integer.toUnsignedLong(numberOfTickets), user);
        ticketOrderRepository.saveAndFlush(t);
        return t;
    }

    @Override
    public TicketOrder findById(Long Id) {
        return ticketOrderRepository.findById(Id).get();
    }
}
