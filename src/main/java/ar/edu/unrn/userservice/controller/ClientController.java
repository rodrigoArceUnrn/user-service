package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.dto.ClientDTO;
import ar.edu.unrn.userservice.exception.ClientException;
import ar.edu.unrn.userservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/clients")
public class ClientController {

    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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


}
