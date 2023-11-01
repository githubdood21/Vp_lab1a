package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketOrderReopsitory {
    public void addOrder(TicketOrder o)
    {
        InMemoryDB.ticketOrders.add(o);
    }
}
