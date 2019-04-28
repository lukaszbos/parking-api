package com.lukasz.client;

import com.lukasz.parking.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkings/{parkingId}/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getClients(@RequestParam(name = "parkingId", defaultValue = "") Integer parkingId) {
        return clientService.getClients(parkingId);
    }

    @GetMapping(value = "/{clientId}")
    public Client getClientById(@PathVariable Integer parkingId, @PathVariable Integer clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping()
    public void addClient(@RequestBody Client client, @PathVariable Integer parkingId) {
        clientService.addClient(client, parkingId);
    }

    @PutMapping(value = "/{clientId}")
    public void updateClient(@RequestBody Client client, @PathVariable Integer parkingId, @PathVariable Integer clientId) {
        //client.setParking(new Parking(parkingId, ""));
        clientService.updateClient(client, parkingId);
    }

    @DeleteMapping(value = "/{clientId}")
    public void deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
    }
}