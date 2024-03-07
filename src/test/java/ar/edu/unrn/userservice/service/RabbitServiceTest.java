package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.dto.ClientDto;
import ar.edu.unrn.userservice.rabbitmq.Producer;
import ar.edu.unrn.userservice.repository.ClientRepository;
import ar.edu.unrn.userservice.service.impl.RabbitService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class RabbitServiceTest {

    private ClientRepository clientRepository;
    private RabbitService rabbitService;
    private ModelMapper modelMapper;
    private Queue queue;
    @Mock
    private Producer producer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        clientRepository = mock(ClientRepository.class);
        rabbitService = mock(RabbitService.class);
        modelMapper = new ModelMapper();
    }

    @Test
    public void queueTest() {
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);

        queue = mock(Queue.class);
        when(queue.getName()).thenReturn("testQueue");

        producer = new Producer(rabbitTemplate, queue);

        producer.send("testMessage");

        // Verificar que se haya llamado a convertAndSend() en RabbitTemplate con los argumentos adecuados
        verify(rabbitTemplate, times(1)).convertAndSend("testQueue", "testMessage");
    }

    @Test
    public void sendClientUpdateMessageTest() {
        Producer producer = mock(Producer.class);

        RabbitService rabbitService = new RabbitService(producer);

        ClientDto clientDto = new ClientDto();
        clientDto.setId("1");
        clientDto.setDateOfBirth(null);
        clientDto.setName("Pepo-ito");
        clientDto.setLastname("arce");
        clientDto.setDocumentNumber("38083954");
        clientDto.setDocumentType("DNI");

        String json = clientDto.toJsonString();
        rabbitService.sendClientUpdateMessage(clientDto);

        // Verificar que se haya llamado al método send de Producer con el mensaje adecuado
        assertTrue(verifyProducerSendCalledWithMessage(producer, json));
    }

    private boolean verifyProducerSendCalledWithMessage(Producer producer, String expectedMessage) {
        try {
            verify(producer, times(1)).send(expectedMessage);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }
}