package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.dto.ClientDto;
import ar.edu.unrn.userservice.model.Client;

/**
 * ClientService.
 */
public interface ClientService {
  void update(ClientDto clientDto);

  ClientDto getClientById(Long id);

  Client getClientByUserId(Long id);
}