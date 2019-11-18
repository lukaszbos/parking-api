package com.lukasz.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    public ClientDTO getClientById(UUID clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found :D :D :D "));
        return clientMapper.toDTO(client);
    }

    public ClientDTO addClient(ClientDTO clientDTO) {
        Client client = clientMapper.toModel(clientDTO);
        Client addedClient = clientRepository.save(client);
        return clientMapper.toDTO(addedClient);
    }

    void updateClient(Client client, UUID clientId) {
        clientRepository.save(client);
    }

    private boolean isThisClientOnRepo(UUID clientId) {
        return clientRepository.existsById(clientId);
    }

    void deleteClient(UUID clientId) {
        clientRepository.deleteById(clientId);
    }
}