package ar.edu.unrn.userservice.controller.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ClientMessageTest {

    @Test
    public void testClientMessage() {
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setId(1L);
        clientMessage.setName("rodrigo");

        ClientMessage clientMessage1 = new ClientMessage();
        clientMessage1.setId(1L);
        clientMessage1.setName("rodrigo");

        ClientMessage clientMessage2 = new ClientMessage();
        clientMessage2.setId(1L);
        clientMessage2.setName("rodrigo");

        assertThat(clientMessage.getId()).isEqualTo(1L);
        assertThat(clientMessage.getName()).isEqualTo("rodrigo");

        //Reflexividad
        assertEquals(clientMessage, clientMessage);

        //Simetría
        assertEquals(clientMessage, clientMessage1);
        assertEquals(clientMessage1, clientMessage);

        //Transitividad
        assertEquals(clientMessage, clientMessage1);
        assertEquals(clientMessage1, clientMessage2);
        assertEquals(clientMessage, clientMessage2);

        //consistencia
        Assertions.assertEquals(clientMessage.equals(clientMessage1), clientMessage.equals(clientMessage1));

        Assertions.assertNotEquals(null, clientMessage); // Comparación con null:
    }

    @Test
    public void testHashCodeConsistency() {
        ClientMessage clientMessage1 = new ClientMessage();
        clientMessage1.setName("rodri");
        ClientMessage clientMessage2 = new ClientMessage();
        clientMessage2.setName("rodri");

        assertEquals(clientMessage1.hashCode(), clientMessage2.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        ClientMessage clientMessage1 = new ClientMessage();
        clientMessage1.setName("rodri");
        ClientMessage clientMessage2 = new ClientMessage();
        clientMessage2.setName("alejandro");

        assertNotEquals(clientMessage1.hashCode(), clientMessage2.hashCode());
    }
}
