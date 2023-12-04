package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.Implement.AuthenticationServiceImplement;
import mk.finki.ukim.mk.lab.service.Implement.MovieServiceImplement;
import mk.finki.ukim.mk.lab.service.Implement.ShoppingCartServiceImplement;
import mk.finki.ukim.mk.lab.service.Implement.TicketOrderServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartServiceImplement shoppingCartServiceImplement;
    private final AuthenticationServiceImplement authenticationServiceImplement;
    private final MovieServiceImplement movieServiceImplement;
    private final TicketOrderServiceImplement ticketOrderServiceImplement;

    public ShoppingCartController(ShoppingCartServiceImplement shoppingCartServiceImplement, AuthenticationServiceImplement authenticationServiceImplement, MovieServiceImplement movieServiceImplement, TicketOrderServiceImplement ticketOrderServiceImplement) {
        this.shoppingCartServiceImplement = shoppingCartServiceImplement;
        this.authenticationServiceImplement = authenticationServiceImplement;
        this.movieServiceImplement = movieServiceImplement;
        this.ticketOrderServiceImplement = ticketOrderServiceImplement;
    }

    @GetMapping("")
    public String GetShoppingCartPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("user", authenticationServiceImplement.findByUsername(((User)request.getSession().getAttribute("User")).getUsername()));
        return "carts";
    }
    @PostMapping("")
    public String PostShoppingCartPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("cart", shoppingCartServiceImplement.getShoppingCart(((User)request.getSession().getAttribute("User")).getUsername(), Long.parseLong(request.getParameter("CartSelect").toString())));
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

    @PostMapping("/checkout")
    public String PostCartCheckOut(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("cart", shoppingCartServiceImplement.getShoppingCart(((User)request.getSession().getAttribute("User")).getUsername(), Long.parseLong(request.getParameter("CartId"))));
        return "checkoutpage";
    }

    @GetMapping("/add")
    public String GetAddShoppingCart(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        return "addcart";
    }

    @PostMapping("/add")
    public String PostAddShoppingCart(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        shoppingCartServiceImplement.addShoppingCart((User)request.getSession().getAttribute("User"), LocalDateTime.parse(request.getParameter("Date")));
        return "redirect:/cart";
    }

    @GetMapping("/edit/{id}")
    public String GetEditShoppingCart(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("cart", shoppingCartServiceImplement.getShoppingCart(((User)request.getSession().getAttribute("User")).getUsername(), Long.parseLong(id)));
        return "editcart";
    }

    @PostMapping("/edit/{id}")
    public String PostEditShoppingCart(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        shoppingCartServiceImplement.editShoppingCart((User)request.getSession().getAttribute("User"), LocalDateTime.parse(request.getParameter("Date")), Long.parseLong(id));
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String GetDeleteShoppingCart(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        model.addAttribute("cart", shoppingCartServiceImplement.getShoppingCart(((User)request.getSession().getAttribute("User")).getUsername(), Long.parseLong(id)));
        return "deletecart";
    }

    @PostMapping("/delete/{id}")
    public String PostDeleteShoppingCart(HttpServletRequest request, @RequestParam(required = false) String error, Model model, @PathVariable String id)
    {
        shoppingCartServiceImplement.deleteShoppingCart((User)request.getSession().getAttribute("User"), Long.parseLong(id));
        return "redirect:/cart";
    }

    @PostMapping("/addtocart")
    public String PostAddToShoppingCart(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        User user = (User) request.getSession().getAttribute("User");
        Long cartid = Long.parseLong(request.getParameter("CartIdf").toString());
        TicketOrder ticketOrder = ticketOrderServiceImplement.placeOrder(request.getParameter("MovieSelect").toString(), Integer.parseInt(request.getParameter("numTickets").toString()), user);

        shoppingCartServiceImplement.addTicketOrderToShoppingCart(user.getUsername(), cartid, ticketOrder);

        model.addAttribute("cart", shoppingCartServiceImplement.getShoppingCart(user.getUsername(), Long.parseLong(request.getParameter("CartIdf").toString())));
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

    @PostMapping("/buy")
    public String PostBuyShoppingCart(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        shoppingCartServiceImplement.deleteShoppingCart((User)request.getSession().getAttribute("User"), Long.parseLong(request.getParameter("CartId")));
        return "redirect:/movies";
    }
}
