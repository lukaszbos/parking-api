package com.lukasz.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    Client getClientById(Integer clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    void addClient(Client client) {
        clientRepository.save(client);
    }

    void updateClient(Client client, Integer clientId) {
        clientRepository.save(client);
    }

    private boolean isThisClientOnRepo(Integer clientId) {
        return clientRepository.existsById(clientId);
    }

    void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}