package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.dto.ClientDTO;
import ar.edu.unrn.userservice.exception.ClientException;
import ar.edu.unrn.userservice.service.ClientService;
import ar.edu.unrn.userservice.service.impl.RabbitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/clients")
public class ClientController {

    final ClientService clientService;

    final RabbitService rabbitService;

    public ClientController(ClientService clientService, RabbitService rabbitService) {
        this.clientService = clientService;
        this.rabbitService = rabbitService;
    }


    @PutMapping()
    public ResponseEntity updateClient(@RequestBody ClientDTO clientDTO) {
        try {
            return ResponseEntity.ok().body(clientService.update(clientDTO));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(clientService.getClientById(id));
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/testRabbit/{message}")
    public ResponseEntity sendRabbit(@PathVariable String message) {
        try {
            rabbitService.sendToRabbit(message);
            return ResponseEntity.ok().build();
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
