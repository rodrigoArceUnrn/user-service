package ar.edu.unrn.userservice.service.impl;

import ar.edu.unrn.userservice.dto.ClientDTO;
import ar.edu.unrn.userservice.exception.ClientException;
import ar.edu.unrn.userservice.rabbitmq.Producer;
import ar.edu.unrn.userservice.repository.ClientRepository;
import ar.edu.unrn.userservice.security.entity.Client;
import ar.edu.unrn.userservice.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    final
    ClientRepository clientRepository;

    Producer producer = new Producer();

    private final ModelMapper modelMapper;


    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) {
        Client client = clientRepository.save(convertToEntity(clientDTO));
        return convertToDTO(client);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findClientById(id);
        if (client == null) throw new ClientException("No existe cliente");
        return convertToDTO(client);    }

    private Client convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    private ClientDTO convertToDTO(Client client) {
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
        return clientDTO;
    }


}
