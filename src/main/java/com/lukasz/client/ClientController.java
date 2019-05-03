package com.lukasz.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> getAllClients(@RequestParam Integer parkingId) {
        return clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    public Client getClientById(@PathVariable Integer clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping()
    public void addClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

    @PutMapping(value = "/{clientId}")
    public void updateClient(@RequestBody Client client, @PathVariable Integer clientId) {
        clientService.updateClient(client, clientId);
    }

    @DeleteMapping(value = "/{clientId}")
    public void deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
    }
}