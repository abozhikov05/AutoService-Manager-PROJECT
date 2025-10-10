package com.abozhikov.AutoServiceManager.service;

import com.abozhikov.AutoServiceManager.model.Client;
import com.abozhikov.AutoServiceManager.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    public Optional<Client> getClientById(int id) {
        return clientRepo.findById(id);
    }

    public void deleteClient(int id) {
        clientRepo.deleteById(id);
    }
    public void saveClient(Client client) {
        clientRepo.save(client);
    }

}
