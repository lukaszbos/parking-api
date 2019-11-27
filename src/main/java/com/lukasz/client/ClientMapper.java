package com.lukasz.client;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toModel(ClientDTO clientDTO) {
        return new Client(clientDTO.getName(), clientDTO.getSurname(), clientDTO.getEmail());
    }

    public ClientDTO toDTO(Client client) {
        return new ClientDTO(client.getClientId(), client.getName(), client.getSurname(), client.getEmail());
    }

}