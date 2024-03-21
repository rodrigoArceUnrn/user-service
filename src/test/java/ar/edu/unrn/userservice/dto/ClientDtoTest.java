package ar.edu.unrn.userservice.dto;

import ar.edu.unrn.userservice.controller.dto.ClientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ClientDtoTest {

    @Test
    public void testClientDto() {
        ClientDto clientDto = new ClientDto("1", "rodrigo", "arce", "dni", "38083954", "27/10/1994");

        ClientDto clientDto1 = new ClientDto("1", "rodrigo", "arce", "dni", "38083954", "27/10/1994");

        ClientDto clientDto2 = new ClientDto("1", "rodrigo", "arce", "dni", "38083954", "27/10/1994");

        assertThat(clientDto.getId()).isEqualTo("1");
        assertThat(clientDto.getName()).isEqualTo("rodrigo");
        assertThat(clientDto.getLastname()).isEqualTo("arce");
        assertThat(clientDto.getDocumentType()).isEqualTo("dni");
        assertThat(clientDto.getDocumentNumber()).isEqualTo("38083954");
        assertThat(clientDto.getDateOfBirth()).isEqualTo("27/10/1994");

        //Reflexividad
        assertEquals(clientDto, clientDto);

        //Simetría
        assertEquals(clientDto, clientDto1);
        assertEquals(clientDto1, clientDto);

        //Transitividad
        assertEquals(clientDto, clientDto1);
        assertEquals(clientDto1, clientDto2);
        assertEquals(clientDto, clientDto2);

        //consistencia
        Assertions.assertEquals(clientDto.equals(clientDto1), clientDto.equals(clientDto1));

        Assertions.assertNotEquals(null, clientDto);

    }

    @Test
    public void testHashCodeConsistency() {
        ClientDto client1 = new ClientDto();
        client1.setId("1");
        client1.setName("rodri");
        ClientDto client2 = new ClientDto();
        client2.setId("1");
        client2.setName("rodri");

        assertEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        ClientDto client1 = new ClientDto();
        client1.setId("1");
        client1.setName("rodri");
        ClientDto client2 = new ClientDto();
        client2.setId("2");
        client2.setName("mar");

        assertNotEquals(client1.hashCode(), client2.hashCode());
    }
}
