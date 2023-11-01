package mk.finki.ukim.mk.lab.service.Implement;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.TicketOrderReopsitory;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderServiceImplement implements TicketOrderService {

    TicketOrderReopsitory ticketOrderReopsitory = new TicketOrderReopsitory();

    public TicketOrderServiceImplement(TicketOrderReopsitory ticketOrderReopsitory)
    {
        this.ticketOrderReopsitory = ticketOrderReopsitory;
    }
    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        if(numberOfTickets < 1)
        {
            return null;
        }
        TicketOrder t = new TicketOrder(movieTitle, clientName, address, Integer.toUnsignedLong(numberOfTickets));
        ticketOrderReopsitory.addOrder(t);
        return t;
    }
}
