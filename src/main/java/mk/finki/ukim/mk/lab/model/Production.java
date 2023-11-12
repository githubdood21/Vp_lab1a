package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Production {
    public Production(String name, String country, String address) {
        this.id = IdCount;
        IdCount++;
        this.name = name;
        this.country = country;
        this.address = address;
    }
    private Long id;
    private static Long IdCount = 0L;
    private String name;
    private String country;
    private String address;
}
