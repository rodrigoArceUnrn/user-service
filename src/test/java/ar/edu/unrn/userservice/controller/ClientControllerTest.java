package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.dto.ClientDto;
import ar.edu.unrn.userservice.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testUserControllerFailed() throws Exception {
        mockMvc.perform(get("/clients/1")) // Ruta del controlador que quieres probar
                .andExpect(status().is4xxClientError()); // Verifica que la respuesta sea OK (código HTTP 200)
    }

    @Test
    public void testUserControllerSuccess() throws Exception {
        mockMvc.perform(get("/clients/1") // Ruta del controlador que quieres probar
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes()))) // Agregar usuario y contraseña aquí
                .andExpect(status().is2xxSuccessful()); // Verifica que la respuesta sea OK (código HTTP 200)
    }

    @Test
    public void testUpdateClientUserUnauthorized() throws Exception {
        // Simula un objeto ClientDto
        ClientDto clientDto = new ClientDto("3", "Pepo-ito", "Arce", "DNI", "38083954", null);

        // Configura el comportamiento del servicio mock clientService
        doThrow(new RuntimeException("Error de servicio")).when(clientService).update(clientDto);

        // Realiza una solicitud PUT simulada al controlador con el objeto ClientDto
        mockMvc.perform(put("/clients")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes())) // Agregar usuario y contraseña aquí
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDto))) // Establece el contenido del cuerpo de la solicitud
                .andExpect(status().isNotFound());// Verifica que se devuelve una respuesta HTTP 200
    }

    @Test
    public void testUpdateClientSuccess() throws Exception {
        // Simula un objeto ClientDto
        ClientDto clientDto = new ClientDto("1", "Pepo-ito", "Arce", "DNI", "38083954", null);

        // Configura el comportamiento del servicio mock clientService
        doNothing().when(clientService).update(clientDto);

        // Realiza una solicitud PUT simulada al controlador con el objeto ClientDto
        mockMvc.perform(put("/clients")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes())) // Agregar usuario y contraseña aquí
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDto))) // Establece el contenido del cuerpo de la solicitud
                .andExpect(status().is2xxSuccessful()); // Verifica que se devuelve una respuesta HTTP 200
    }



    // Método para convertir un objeto a una cadena JSON
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
