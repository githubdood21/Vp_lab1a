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
@RequestMapping("/movies")
public class MovieController {


    private final ProductionServiceImplement productionServiceImplement;
    private final MovieServiceImplement movieServiceImplement;
    private final TicketOrderServiceImplement ticketOrderServiceImplement;

    public MovieController(ProductionServiceImplement productionServiceImplement, MovieServiceImplement movieServiceImplement, TicketOrderServiceImplement ticketOrderServiceImplement) {
        this.productionServiceImplement = productionServiceImplement;
        this.movieServiceImplement = movieServiceImplement;
        this.ticketOrderServiceImplement = ticketOrderServiceImplement;
    }

    @GetMapping("")
    public String getMoviesPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        if(error != null)
        {
            model.addAttribute("hasError", true);
            model.addAttribute("errormessage", error);
        }
        else{
            model.addAttribute("hasError", false);
        }
        model.addAttribute("Movies", movieServiceImplement.listAll());
        model.addAttribute("User", (User)request.getSession().getAttribute("User"));

        return "listMovies";
    }
    @PostMapping("")
    public String getMoviesSearch(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("Movies", movieServiceImplement.searchMovies(request.getParameter("SearchMovie").toString()));
        return "listMovies";
    }
    @GetMapping("/add")
    public String GetAddMovie(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("Studios", productionServiceImplement.findAll());
        return "addMovie";
    }
    @PostMapping("/add")
    public String PostAddMovie(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        movieServiceImplement.saveMovie(request.getParameter("Title").toString(), request.getParameter("Summary").toString(), Double.parseDouble(request.getParameter("Rating").toString()), Long.parseLong(request.getParameter("Production").toString()));

        return "redirect:/movies";
    }
    @GetMapping("/edit")
    public String GetEditMovie(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        String errorMessage = "Error selected film is not valid";
        return "redirect:/movies?error=" + errorMessage;
    }
    @GetMapping("/edit/{id}")
    public String GetEditMovie(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("Movie", movieServiceImplement.findbyId(Long.parseLong(id)));
        model.addAttribute("Studios", productionServiceImplement.findAll());
        return "editMovie";
    }
    @PostMapping("/edit/{id}")
    public String PostEditMovie(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        movieServiceImplement.editMovie(request.getParameter("Title").toString(), request.getParameter("Summary").toString(), Double.parseDouble(request.getParameter("Rating").toString()), Long.parseLong(request.getParameter("Production").toString()), Long.parseLong(id));

        return "redirect:/movies";
    }
    @GetMapping("/delete/{id}")
    public String GetDeleteMovie(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("Movie", movieServiceImplement.findbyId(Long.parseLong(id)));
        return "deleteMovie";
    }
    @PostMapping("/delete/{id}")
    public String PostDeleteMovie(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        movieServiceImplement.deleteMovie(Long.parseLong(id));
        return "redirect:/movies";
    }
}
