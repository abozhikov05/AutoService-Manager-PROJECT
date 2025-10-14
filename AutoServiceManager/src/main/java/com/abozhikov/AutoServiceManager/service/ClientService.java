package com.abozhikov.AutoServiceManager.service;

import com.abozhikov.AutoServiceManager.model.Client;
import com.abozhikov.AutoServiceManager.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;
    public ClientService(ClientRepo clientRepository) {
        this.clientRepo = clientRepository;
    }

    public Optional<Client> findByEmail(String email) {
        return clientRepo.findByEmail(email);
    }
    public Client saveClient(Client client) {
        return clientRepo.save(client);
    }
}

