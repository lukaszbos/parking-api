package com.lukasz.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parkings/{parkingId}/bills/{billId}/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getClients(@RequestParam(name = "billId", defaultValue = "") Integer billId) {
        return clientService.getClients(billId);
    }

    @GetMapping(value = "/{clientId}")
    public Client getClientById(@PathVariable Integer billId, @PathVariable Integer clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping()
    public void addClient(@RequestBody Client client, @PathVariable Integer billId) {
        clientService.addClient(client, billId);
    }

    @PutMapping(value = "/{clientId}")
    public void updateClient(@RequestBody Client client, @PathVariable Integer billId, @PathVariable Integer clientId) {
        //client.setParking(new Parking(billId, ""));
        clientService.updateClient(client, billId);
    }

    @DeleteMapping(value = "/{clientId}")
    public void deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
    }
}