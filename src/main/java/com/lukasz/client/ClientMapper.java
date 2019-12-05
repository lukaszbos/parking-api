package com.lukasz.client;

import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toModel(ClientDto clientDto) {
        return new Client(clientDto.getName(), clientDto.getSurname(), clientDto.getEmail());
    }

    public ClientDto toDto(Client client) {
        return new ClientDto(client.getClientId(), client.getName(), client.getSurname(), client.getEmail());
    }

}