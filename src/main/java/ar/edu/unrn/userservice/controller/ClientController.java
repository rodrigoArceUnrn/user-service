package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.dto.ClientDTO;
import ar.edu.unrn.userservice.service.ClientService;
import ar.edu.unrn.userservice.service.impl.RabbitService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/clients")
public class ClientController {

    final ClientService clientService;
    final RabbitService rabbitService;

    final Gson gson;

    public ClientController(ClientService clientService, RabbitService rabbitService) {
        this.clientService = clientService;
        this.rabbitService = rabbitService;
        this.gson = new Gson();
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<?> updateClient(@RequestBody ClientDTO clientDTO) {
        try {
            clientService.update(clientDTO);
            //rabbitService.sendClientUpdateMessage(result);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<?> getClient(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }
}
