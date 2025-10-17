package com.abozhikov.AutoServiceManager.controller;

import com.abozhikov.AutoServiceManager.model.Client;
import com.abozhikov.AutoServiceManager.repo.ClientRepo;
import com.abozhikov.AutoServiceManager.repo.ServiceRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private ServiceRequestRepo serviceRequestRepo;

    public AuthController(ClientRepo clientRepo, PasswordEncoder passwordEncoder) {
        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/signIn")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Invalid email or password!");
        }
        if (logout != null) {
            model.addAttribute("msg", "You have been logged out successfully.");
        }
        return "signIn";
    }
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("client", new Client());
        return "signup";
    }
    @PostMapping("/signup")
    public String registerClient(@ModelAttribute Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepo.save(client);
        return "redirect:/signIn";
    }
    @GetMapping({"/", "/index"})
    public String showIndex(Model model) {
        List<Object[]> stats = serviceRequestRepo.countServicesByCarBrand();
        model.addAttribute("stats", stats);
        return "index";
    }
}
