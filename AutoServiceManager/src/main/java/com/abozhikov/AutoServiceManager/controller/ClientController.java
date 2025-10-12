package com.abozhikov.AutoServiceManager.controller;

import com.abozhikov.AutoServiceManager.model.Client;
import com.abozhikov.AutoServiceManager.repo.ClientRepo;
import com.abozhikov.AutoServiceManager.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "signIn";
    }
    @PostMapping("/signIn")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        Client client = clientService.findByEmail(email);
        if(client != null && client.getPassword().equals(password)) {
            return "index";
        } else {
            model.addAttribute("error", "Невалидни данни");
            return "signIn";
        }
    }
}
