package com.lukasz.client;

import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private ParkingRepository parkingRepository;
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ParkingRepository parkingRepository) {
        this.clientRepository = clientRepository;
        this.parkingRepository = parkingRepository;
    }

    public List<Client> getClients(Integer parkingId) {
        if (isIdSent(parkingId)) {
            return getClientsWorkingOnParking(parkingId);
        } else
            return getAllClients();
    }

    private boolean isIdSent(Integer parkingParam) {
        return parkingParam != null;
    }

    public List<Client> getClientsWorkingOnParking(Integer parkingId) {
        List<Client> clients = new ArrayList<>();
        clientRepository.findByParking_ParkingId(parkingId).forEach(clients::add);
        return clients;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }


    public Client getClientById(Integer clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    public void addClient(Client client, Integer parkingId) {
        Parking parkingOnWithClientWorks = getAccurateParking(parkingId);
        client.setParking(parkingOnWithClientWorks);
        clientRepository.save(client);
    }

    private Parking getAccurateParking(Integer parkingId) {
        return parkingRepository.findById(parkingId).get();
    }

    public void updateClient(Client client, Integer parkingId) {
        Parking parkingOnWithClientWorks = getAccurateParking(parkingId);
        client.setParking(parkingOnWithClientWorks);
        clientRepository.save(client);
    }

    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}
