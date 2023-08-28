package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.dto.ClientDTO;
import ar.edu.unrn.userservice.model.Client;

public interface ClientService {
    void update(ClientDTO clientDTO);

    ClientDTO getClientById(Long id);

    Client getClientByUserId(Long id);

}
