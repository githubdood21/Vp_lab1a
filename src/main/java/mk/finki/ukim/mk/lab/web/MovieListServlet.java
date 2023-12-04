package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="Home", urlPatterns = "/servlet")
public class MovieListServlet extends HttpServlet {
    private final MovieService movieService;
    private final SpringTemplateEngine springTemplateEngine;

    public MovieListServlet(MovieService movieService, SpringTemplateEngine springTemplateEngine)
    {
        this.movieService = movieService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(exchange);

        context.setVariable("test", 1);
        context.setVariable("Movies", movieService.listAll());

        this.springTemplateEngine.process("listMoviesServlet.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieSelect = req.getParameter("SearchMovie");

        IWebExchange exchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(exchange);

        context.setVariable("Movies", movieService.searchMovies(movieSelect));

        this.springTemplateEngine.process("listMoviesServlet.html", context, resp.getWriter());
    }
}
