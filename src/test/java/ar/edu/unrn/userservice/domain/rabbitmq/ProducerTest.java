package ar.edu.unrn.userservice.domain.rabbitmq;

import ar.edu.unrn.userservice.domain.rabbitmq.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProducerTest {

    private RabbitTemplate rabbitTemplateMock;
    private Queue queueMock;
    private Producer producer;

    @BeforeEach
    public void setUp() {
        rabbitTemplateMock = mock(RabbitTemplate.class);
        queueMock = mock(Queue.class);
        producer = new Producer(rabbitTemplateMock, queueMock);
    }

    @Test
    public void testSend() {
        // Configurar el comportamiento del mock de Queue
        when(queueMock.getName()).thenReturn("testQueue");

        // Invocar el método send() de la clase Producer
        String message = "Hello, RabbitMQ!";
        producer.send(message);

        // Verificar que se haya llamado al método convertAndSend del mock de RabbitTemplate con los parámetros esperados
        verify(rabbitTemplateMock).convertAndSend("testQueue", message);
    }
}
