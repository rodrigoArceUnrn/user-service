package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.dto.ClientDTO;

public interface ClientService {

    ClientDTO update(ClientDTO clientDTO);

    ClientDTO getClientById(Long id);
}
