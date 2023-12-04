package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productions")
public class Production {
    public Production(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String address;
    public Production() {

    }
}
