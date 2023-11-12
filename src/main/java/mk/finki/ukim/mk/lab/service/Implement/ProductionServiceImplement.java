package mk.finki.ukim.mk.lab.service.Implement;

import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImplement implements ProductionService {
    private final ProductionRepository productionRepository;
    public ProductionServiceImplement(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }
    @Override
    public List<Production> findAll() {
        return productionRepository.findAll();
    }
}
