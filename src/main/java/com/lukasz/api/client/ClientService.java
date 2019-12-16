package com.lukasz.api.client;

import com.lukasz.api.exception.ConflictException;
import com.lukasz.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    List<ClientDto> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients
                .stream()
                .map(client -> clientMapper.toDto(client))
                .collect(Collectors.toList());
    }

    public ClientDto getClientById(UUID clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found :D :D :D "));
        return clientMapper.toDto(client);
    }

    public ClientDto addClient(ClientDto clientDto) {
        checkIfEmailExist(clientDto);
        Client client = clientMapper.toModel(clientDto);
        Client addedClient = clientRepository.save(client);
        return clientMapper.toDto(addedClient);
    }

    private void checkIfEmailExist(ClientDto clientDto) {
        if (clientRepository.findByEmail(clientDto.getEmail()) != null) {
            throw new ConflictException("Email already in use");
        }
    }

    public ClientDto updateClient(ClientDto clientDto, UUID clientId) {
        Client client = clientMapper.toModel(clientDto);
        client.setClientId(clientId);
        Client addedClient = clientRepository.save(client);
        return clientMapper.toDto(addedClient);
    }

    private boolean clientExists(UUID clientId) {
        return clientRepository.existsById(clientId);
    }

    public ClientDto deleteClient(UUID clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Couldn't delete - client not found :D :D :D "));
        clientRepository.deleteById(clientId);
        return clientMapper.toDto(client);
    }

}