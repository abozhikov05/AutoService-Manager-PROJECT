package com.abozhikov.AutoServiceManager.controller;

import com.abozhikov.AutoServiceManager.model.Client;
import com.abozhikov.AutoServiceManager.repo.ClientRepo;
import com.abozhikov.AutoServiceManager.service.ClientService;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/signup")
    public String showForm(Model model) {
        model.addAttribute("client", new Client());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "index";
    }

    @GetMapping("/signIn")
    public String signInPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && ((org.springframework.security.core.Authentication) auth).isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/index";
        }
        return "signIn";
    }
    @PostMapping("/signIn")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Optional<Client> client = clientService.findByEmail(email);

        if (client.isPresent() && client.get().getPassword().equals(password)) {
            session.setAttribute("clientName", client.get().getFirstName()); // или каквото искаш
            return "redirect:/index"; // редирект към index
        } else {
            model.addAttribute("error", "Невалидни данни");
            return "signIn";
        }
    }
}
