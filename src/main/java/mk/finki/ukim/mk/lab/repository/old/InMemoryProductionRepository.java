package mk.finki.ukim.mk.lab.repository.old;

import mk.finki.ukim.mk.lab.DB.InMemoryDB;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductionRepository {
    public List<Production> findAll()
    {
        return InMemoryDB.productions;
    }
    public Optional<Production> findbyId(Long id){
        return InMemoryDB.productions.stream().filter(x -> x.getId().equals(id)).findFirst();
    }
}
