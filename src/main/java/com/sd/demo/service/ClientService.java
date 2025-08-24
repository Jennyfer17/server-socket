package com.sd.demo.service;

import java.util.List;

import com.sd.demo.model.Client;
import com.sd.demo.repository.ClientRepository;

public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client createClient(Client client) {
        return repository.save(client);
    }

    public Client getClientById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Client updateClient(String id, Client client) {
        if (!repository.existsById(id)) {
            return null;
        }
        client.setId(id);
        return repository.save(client);
    }

    public boolean deleteClient(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
