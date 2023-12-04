package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "shoppingcarts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private LocalDateTime dateCreated;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<TicketOrder> ticketOrders;

    public ShoppingCart(User user, LocalDateTime dateCreated) {
        this.user = user;
        this.dateCreated = dateCreated;
        this.ticketOrders = new ArrayList<>();
    }

    public ShoppingCart() {

    }
}
