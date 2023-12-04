package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticketorders")
public class TicketOrder {
    public TicketOrder(String movieTitle, Long numberOfTickets, User user)
    {
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
        this.user = user;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String movieTitle;
    private Long numberOfTickets;
    @ManyToOne
    private User user;
    public TicketOrder() {

    }
}
