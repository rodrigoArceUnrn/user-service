package ar.edu.unrn.userservice.service.impl;

import ar.edu.unrn.userservice.dto.ClientDTO;
import ar.edu.unrn.userservice.model.Client;
import ar.edu.unrn.userservice.rabbitmq.Producer;
import ar.edu.unrn.userservice.repository.ClientRepository;
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
        return convertToDTO(client);
    }

    @Override
    public Client getClientByUserId(Long id) {
        return clientRepository.findClientByUserId(id);
    }

    private Client convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    private ClientDTO convertToDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }


}
