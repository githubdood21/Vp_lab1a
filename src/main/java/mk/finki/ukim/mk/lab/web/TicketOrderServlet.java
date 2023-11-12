package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="order", urlPatterns = "/servlet/ticketOrder")
public class TicketOrderServlet extends HttpServlet {
    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final SpringTemplateEngine springTemplateEngine;

    public TicketOrderServlet(MovieService movieService, TicketOrderService ticketOrderService, SpringTemplateEngine springTemplateEngine)
    {
        this.movieService = movieService;
        this.ticketOrderService = ticketOrderService;
        this.springTemplateEngine = springTemplateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieSelect = req.getParameter("MovieSelect");
        Movie model = movieService.findFirst(movieSelect);
        String numTickets = req.getParameter("numTickets").toString();

        IWebExchange exchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(exchange);

        context.setVariable("Ip",req.getRemoteAddr());
        context.setVariable("Movie", model);
        context.setVariable("Tickets",numTickets);

        this.springTemplateEngine.process("orderConfirmationServlet.html", context, resp.getWriter());
    }
}
