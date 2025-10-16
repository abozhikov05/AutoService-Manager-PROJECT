package com.abozhikov.AutoServiceManager.controller;

import com.abozhikov.AutoServiceManager.model.Client;
import com.abozhikov.AutoServiceManager.model.ServiceRequest;
import com.abozhikov.AutoServiceManager.repo.ClientRepo;
import com.abozhikov.AutoServiceManager.repo.ServiceRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ServiceRequestController {
    @Autowired
    private ServiceRequestRepo serviceRequestRepo;

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/services")
    public String showServiceForm(Model model) {
        model.addAttribute("serviceRequest", new ServiceRequest());
        return "services";
    }

    @PostMapping("/services")
    public String submitServiceRequest(@ModelAttribute ServiceRequest serviceRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Optional<Client> optionalClient = clientRepo.findByEmail(email);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            String fullName=client.getFirstName()+" "+client.getLastName();
            serviceRequest.setClientName(fullName);
        }

        serviceRequestRepo.save(serviceRequest);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}
