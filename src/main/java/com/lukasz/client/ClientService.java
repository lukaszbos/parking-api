package com.lukasz.client;

import com.lukasz.bill.Bill;
import com.lukasz.bill.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private BillRepository billRepository;
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, BillRepository billRepository) {
        this.clientRepository = clientRepository;
        this.billRepository = billRepository;
    }

    List<Client> getClients(Integer billId) {
        if (isIdSent(billId)) {
            return getClientsWorkingOnBill(billId);
        } else
            return getAllClients();
    }

    private boolean isIdSent(Integer billParam) {
        return billParam != null;
    }

    private List<Client> getClientsWorkingOnBill(Integer billId) {
        List<Client> clients = new ArrayList<>();
        clientRepository.findByBill_BillId(billId).forEach(clients::add);
        return clients;
    }

    private List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    Client getClientById(Integer clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    void addClient(Client client, Integer billId) {
        Bill billOnWithClientWorks = getAccurateBill(billId);
        client.setBill(billOnWithClientWorks);
        clientRepository.save(client);
    }

    private Bill getAccurateBill(Integer billId) {
        return billRepository.findById(billId).get();
    }

    void updateClient(Client client, Integer billId) {
        Bill billOnWithClientWorks = getAccurateBill(billId);
        client.setBill(billOnWithClientWorks);
        clientRepository.save(client);
    }

    void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}
