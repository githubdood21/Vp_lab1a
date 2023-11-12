package mk.finki.ukim.mk.lab.DB;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryDB {
    public static List<Movie> movieList = new ArrayList<>();
    public static List<TicketOrder> ticketOrders = new ArrayList<>();
    public static List<Production> productions = new ArrayList<>();

    public InMemoryDB(){

    }

    @PostConstruct
    public void init()
    {
        Production p1 = new Production("Prod1","Country1","Address1");
        Production p2 = new Production("Warner Bros.","USA","Los Angeles");
        Production p3 = new Production("Paramount Pictures","USA","New York");
        Production p4 = new Production("Universal Pictures","USA","Miami");
        Production p5 = new Production("Studio Ghibli","Japan","Tokyo");
        productions.add(p1);
        productions.add(p2);
        productions.add(p3);
        productions.add(p4);
        productions.add(p5);

        movieList.add(new Movie("Film1","Sum1",5.0d, p1));
        movieList.add(new Movie("The Godfather","Widely regarded as one of the greatest films of all time.",4.5d, p2));
        movieList.add(new Movie("The Godfather Part II","The compelling sequel to The Godfather",3.8d, p2));
        movieList.add(new Movie("Casablanca","Rick Blaine (Humphrey Bogart), who owns a nightclub in Casablanca",3.3d, p3));
        movieList.add(new Movie("Schindler's List","Businessman Oskar Schindler (Liam Neeson) arrives in Krakow in 1939",2.7d, p3));
        movieList.add(new Movie("Citizen Kane","When a reporter is assigned to decipher newspaper magnate Charles Foster Kane's (Orson Welles) dying words",4.4d,p3));
        movieList.add(new Movie("Forrest Gump","Slow-witted Forrest Gump (Tom Hanks) has never thought of himself as disadvantaged",4.2d, p4));
        movieList.add(new Movie("12 Angry Men","Following the closing arguments in a murder trial, the 12 members of the jury must deliberate",2.8d, p4));
        movieList.add(new Movie("The Matrix","Neo (Keanu Reeves) believes that Morpheus (Laurence Fishburne), an elusive figure considered to be the most dangerous man alive",4.9d, p4));
        movieList.add(new Movie("Fight Club","A depressed man (Edward Norton) suffering from insomnia meets a strange soap salesman named Tyler Durden (Brad Pitt)",4.5d, p5));
    }
}
