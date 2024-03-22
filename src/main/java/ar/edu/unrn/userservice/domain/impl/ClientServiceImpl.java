package ar.edu.unrn.userservice.domain.impl;

import ar.edu.unrn.userservice.controller.dto.ClientDto;
import ar.edu.unrn.userservice.model.Client;
import ar.edu.unrn.userservice.data.repository.ClientRepository;
import ar.edu.unrn.userservice.domain.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * ClientServiceImpl.
 */
@Service
public class ClientServiceImpl implements ClientService {
  final ClientRepository clientRepository;

  final RabbitService rabbitService;

  private final ModelMapper modelMapper;

  /**
   * ClientServiceImpl.
  */
  public ClientServiceImpl(ClientRepository clientRepository,
                           RabbitService rabbitService, ModelMapper modelMapper) {
    this.clientRepository = clientRepository;
    this.rabbitService = rabbitService;
    this.modelMapper = modelMapper;
  }

  @Override
  public void update(ClientDto clientDto) {
    Client oldClient = clientRepository.findClientById(Long.valueOf(clientDto.getId()));
    Client newClient = modelMapper.map(clientDto, Client.class);
    newClient.setUser(oldClient.getUser());
    Client result = clientRepository.save(newClient);
    rabbitService.sendClientUpdateMessage(modelMapper.map(result, ClientDto.class));
  }

  @Override
  public ClientDto getClientById(Long id) {
    Client client = clientRepository.findClientById(id);
    return convertToDto(client);
  }

  @Override
  public Client getClientByUserId(Long id) {
    return clientRepository.findClientByUserId(id);
  }

  private Client convertToEntity(ClientDto clientDto) {
    return modelMapper.map(clientDto, Client.class);
  }

  private ClientDto convertToDto(Client client) {
    return modelMapper.map(client, ClientDto.class);
  }
}