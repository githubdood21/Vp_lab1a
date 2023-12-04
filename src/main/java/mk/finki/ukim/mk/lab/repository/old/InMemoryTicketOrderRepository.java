package mk.finki.ukim.mk.lab.repository.old;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTicketOrderRepository {
    public void addOrder(TicketOrder o)
    {
        InMemoryDB.ticketOrders.add(o);
    }
}
