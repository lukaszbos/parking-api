package com.lukasz.client;

import com.lukasz.exception.ConflictException;
import com.lukasz.exception.NotFoundException;
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

    List<ClientDTO> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients
                .stream()
                .map(client -> clientMapper.toDTO(client))
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(UUID clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found :D :D :D "));
        return clientMapper.toDTO(client);
    }

    ClientDTO addClient(ClientDTO clientDTO) {
        checkIfClientExist(clientDTO);
        Client client = clientMapper.toModel(clientDTO);
        Client addedClient = clientRepository.save(client);
        return clientMapper.toDTO(addedClient);
    }

    private void checkIfClientExist(ClientDTO clientDTO) {
        if (clientRepository.findByEmail(clientDTO.getEmail()) != null) {
            throw new ConflictException("Email already in use");
        }
    }

    public ClientDTO updateClient(ClientDTO clientDTO, UUID clientId) {
        Client client = clientMapper.toModel(clientDTO);
        client.setClientId(clientId);
        Client addedClient = clientRepository.save(client);
        return clientMapper.toDTO(addedClient);
    }

    private boolean isThisClientOnRepo(UUID clientId) {
        return clientRepository.existsById(clientId);
    }

    public ClientDTO deleteClient(UUID clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Couldn't delete - client not found :D :D :D "));
        clientRepository.deleteById(clientId);
        return clientMapper.toDTO(client);
    }

}