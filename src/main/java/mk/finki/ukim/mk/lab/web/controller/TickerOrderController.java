package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.Implement.MovieServiceImplement;
import mk.finki.ukim.mk.lab.service.Implement.ProductionServiceImplement;
import mk.finki.ukim.mk.lab.service.Implement.TicketOrderServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticketOrder")
public class TickerOrderController {

    private final ProductionServiceImplement productionServiceImplement;
    private final MovieServiceImplement movieServiceImplement;
    private final TicketOrderServiceImplement ticketOrderServiceImplement;

    public TickerOrderController(ProductionServiceImplement productionServiceImplement, MovieServiceImplement movieServiceImplement, TicketOrderServiceImplement ticketOrderServiceImplement) {
        this.productionServiceImplement = productionServiceImplement;
        this.movieServiceImplement = movieServiceImplement;
        this.ticketOrderServiceImplement = ticketOrderServiceImplement;
    }
    @GetMapping("/{id}")
    public String getOrder(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("Ip", request.getRemoteAddr());
        model.addAttribute("Ticket",ticketOrderServiceImplement.findById(Long.parseLong(id)));
        return "orderConfirmation";
    }
}
