package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.domain.service.ClientService;
import ar.edu.unrn.userservice.controller.dto.ClientDto;
import ar.edu.unrn.userservice.model.Client;
import ar.edu.unrn.userservice.domain.rabbitmq.Producer;
import ar.edu.unrn.userservice.data.repository.ClientRepository;
import ar.edu.unrn.userservice.domain.impl.ClientServiceImpl;
import ar.edu.unrn.userservice.domain.impl.RabbitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTest {
    private ClientRepository clientRepository;
    private ClientService clientService;
    private RabbitService rabbitService;
    private ModelMapper modelMapper;
    @Mock
    private Producer producer;

    @BeforeEach
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
        rabbitService = mock(RabbitService.class);
        modelMapper = new ModelMapper();
        clientService = new ClientServiceImpl(clientRepository, rabbitService, modelMapper);
    }

    @Test
    public void testUpdate() {
        Client oldClient = new Client();
        oldClient.setId(1L);
        oldClient.setDateOfBirth(null);
        oldClient.setName("Pepo-ito");
        oldClient.setLastname("arce");
        oldClient.setDocumentNumber("38083954");
        oldClient.setDocumentType("DNI");

        ClientDto newClientDto = new ClientDto();
        newClientDto.setId("1");
        newClientDto.setName("Pepo-ito2");

        Client newClient = modelMapper.map(newClientDto, Client.class);

        ClientRepository clientRepository = mock(ClientRepository.class);
        when(clientRepository.findClientById(1L)).thenReturn(oldClient);
        when(clientRepository.save(any(Client.class))).thenReturn(newClient);

        doNothing().when(producer).send(anyString());
        clientService = new ClientServiceImpl(clientRepository, rabbitService, modelMapper);

        clientService.update(newClientDto);

        // Verificar que se haya llamado al método findClientById en el repositorio con el ID correcto
        verify(clientRepository, times(1)).findClientById(1L);

        // Verificar que se haya llamado al método save en el repositorio
        verify(clientRepository, times(1)).save(any(Client.class));

        // Verificar que se haya llamado al método sendClientUpdateMessage en RabbitService
        verify(rabbitService, times(1)).sendClientUpdateMessage(any(ClientDto.class));
    }

    @Test
    public void testGetClientById() {

        Client client = new Client();
        client.setId(1L);
        client.setDateOfBirth(null);
        client.setName("Pepo-ito");
        client.setLastname("arce");
        client.setDocumentNumber("38083954");
        client.setDocumentType("DNI");

        ClientDto clientDto = modelMapper.map(client, ClientDto.class);

        ClientRepository clientRepository = mock(ClientRepository.class);
        when(clientRepository.findClientById(1L)).thenReturn(client);

        ClientService clientService = new ClientServiceImpl(clientRepository, rabbitService, modelMapper);

        Long clientId = 1L;
        // Llamar al método getClientById en el servicio y verificar si devuelve el cliente correcto
        Assertions.assertEquals(clientDto, clientService.getClientById(clientId));
    }

    @Test
    public void testGetClientByUserId() {
        ClientService clientService = new ClientServiceImpl(clientRepository, rabbitService, modelMapper);

        Long userId = 456L;

        Client client = new Client();
        client.setId(789L);

        // Cuando se llama al método getClientByUserId en el repositorio, devuelve el cliente simulado
        when(clientRepository.findClientByUserId(userId)).thenReturn(client);

        // Llamar al método getClientByUserId en el servicio y verificar si devuelve el cliente correcto
        Assertions.assertEquals(client, clientService.getClientByUserId(userId));
    }
}