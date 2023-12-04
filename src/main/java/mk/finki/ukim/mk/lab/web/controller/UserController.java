package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.Implement.AuthenticationServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    private final AuthenticationServiceImplement authenticationServiceImplement;

    public UserController(AuthenticationServiceImplement authenticationServiceImplement) {
        this.authenticationServiceImplement = authenticationServiceImplement;
    }

    @GetMapping("/login")
    public String GetLoginPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        return "loginpage";
    }

    @PostMapping("/login")
    public String PostLoginUser(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        User user = authenticationServiceImplement.login(request.getParameter("Username"), request.getParameter("Password"));
        request.getSession().setAttribute("User", user);
        return "redirect:/movies";
    }

    @GetMapping("/register")
    public String GetRegisterPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        return "registerpage";
    }

    @PostMapping("/register")
    public String PostRegisterUser(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        authenticationServiceImplement.register(request.getParameter("Username"), request.getParameter("Name"), request.getParameter("Surname"),request.getParameter("Password"), request.getParameter("RepeatPassword"), LocalDate.parse(request.getParameter("Date")));
        return "redirect:/movies";
    }

    @GetMapping("/logout")
    public String GetLogout(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        request.getSession().invalidate();
        return "redirect:/movies";
    }

}
