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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        mockMvc.perform(get("/clients/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUserControllerSuccess() throws Exception {
        mockMvc.perform(get("/clients/1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateClientUserUnauthorized() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setId("3");
        clientDto.setName("Pepo-ito");
        clientDto.setLastname("Arce");
        clientDto.setDocumentType("DNI");
        clientDto.setDocumentNumber("38083954");
        clientDto.setDateOfBirth(null);

        doThrow(new RuntimeException("Error de servicio")).when(clientService).update(clientDto);

        mockMvc.perform(put("/clients")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateClientSuccess() throws Exception {
        ClientDto clientDto = new ClientDto("1", "Pepo-ito", "Arce", "DNI", "38083954", null);

        doNothing().when(clientService).update(clientDto);

        mockMvc.perform(put("/clients")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDto)))
                .andExpect(status().is4xxClientError());
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
