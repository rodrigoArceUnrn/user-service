package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.controller.dto.ClientDto;
import ar.edu.unrn.userservice.domain.service.ClientService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Client Controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clients")
public class ClientController {
  private final ClientService clientService;
  private final Gson gson;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
    this.gson = new Gson();
  }

  /**
   * Update a client.
   *
   * @param clientDto The client data to update.
   * @return ResponseEntity with HTTP status OK if successful.
  */
  @PutMapping
  @PreAuthorize("hasRole('ROLE_CLIENTE')")
  public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto) {
    try {
      clientService.update(clientDto);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Get a client by ID.
   *
   * @param id The ID of the client.
   * @return ResponseEntity with HTTP status OK and the client data if found.
  */
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_CLIENTE')")
  public ResponseEntity<?> getClient(@PathVariable Long id) {
    return ResponseEntity.ok().body(clientService.getClientById(id));
  }
}
