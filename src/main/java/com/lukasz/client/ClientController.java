package com.lukasz.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }


    @GetMapping()
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    // public Client getClientById(@PathVariable UUID clientId) {
    //   return clientService.getClientById(clientId);
    //}
    public ResponseEntity<ClientDTO> getClientById(@PathVariable UUID clientId){
        ClientDTO clientDTO = clientService.getClientById(clientId);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @PostMapping()
//    public void addClient(@RequestBody Client client) {
//        clientService.addClient(client);
//    }
    public ResponseEntity <ClientDTO> addClient (@RequestBody ClientDTO clientDTO){
        ClientDTO addedClientDTO = clientService.addClient(clientDTO);
        return new ResponseEntity<>(addedClientDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clientId}")
    public void updateClient(@RequestBody Client client, @PathVariable UUID clientId) {
        clientService.updateClient(client, clientId);
    }

    @DeleteMapping(value = "/{clientId}")
    public void deleteClient(@PathVariable UUID clientId) {
        clientService.deleteClient(clientId);
    }
}