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
    public ResponseEntity<ClientDTO> getClientById(@PathVariable UUID clientId){
        ClientDTO clientDTO = clientService.getClientById(clientId);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity <ClientDTO> addClient (@RequestBody ClientDTO clientDTO){
        ClientDTO addedClientDTO = clientService.addClient(clientDTO);
        return new ResponseEntity<>(addedClientDTO, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{clientId}")
    public ResponseEntity <ClientDTO> updateClient(@RequestBody ClientDTO clientDTO, @PathVariable UUID clientId){
        ClientDTO updatedClientDTO = clientService.updateClient(clientDTO, clientId);
        return new ResponseEntity<>(updatedClientDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity <ClientDTO> deleteClient(@PathVariable UUID clientId){
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}