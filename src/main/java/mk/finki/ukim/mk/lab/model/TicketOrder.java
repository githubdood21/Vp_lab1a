package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class TicketOrder {
    public TicketOrder(String movieTitle, String clientName, String address, Long numberOfTickets)
    {
        this.movieTitle = movieTitle;
        this.clientName = clientName;
        this.clientAddress = address;
        this.numberOfTickets = numberOfTickets;
    }
    private String movieTitle;
    private String clientName;
    private String clientAddress;
    private Long numberOfTickets;
}
