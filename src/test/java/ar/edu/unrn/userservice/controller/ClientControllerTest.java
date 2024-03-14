package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.dto.AuthResponseDto;
import ar.edu.unrn.userservice.dto.ClientDto;
import ar.edu.unrn.userservice.security.service.UserDetailsServiceImpl;
import ar.edu.unrn.userservice.service.ClientService;
import ar.edu.unrn.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.webjars.NotFoundException;

import java.util.Base64;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new Gson();

    @MockBean
    private ClientService clientService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private ClientController clientController;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testUserControllerFailed() throws Exception {
        mockMvc.perform(get("/clients/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetClient() throws Exception {

        // Mock del servicio
        ClientService clientServiceMock = mock(ClientService.class);

        // Crear una instancia del controlador con el mock del servicio
        ClientController clientController = new ClientController(clientServiceMock);


        // Configurar el servicio para simular la obtención del cliente por ID
        ClientDto clientDto = new ClientDto();
        clientDto.setId("1");
        clientDto.setName("Nombre de cliente");
        when(clientService.getClientById(1L)).thenReturn(clientDto);

        // Configurar el controlador
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        // Realizar la solicitud GET
        mockMvc.perform(get("/clients/1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes())))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientById() throws Exception {
        // Configurar el mock de autenticación
        Authentication authentication = new UsernamePasswordAuthenticationToken("rodrigoa", "Rodri123");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        // Configurar el mock de UserDetails
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username("rodrigoa")
                .password("Rodri123")
                .roles("CLIENTE")
                .build();
        when(userDetailsService.loadUserByUsername("rodrigoa")).thenReturn(userDetails);

        UserService userServiceMock = Mockito.mock(UserService.class);

        AuthResponseDto authResponse = new AuthResponseDto(); // Aquí debes definir el objeto que esperas recibir en la respuesta
        authResponse.setToken("token");
        when(userServiceMock.autenticate(any())).thenReturn(authResponse);

        // Configurar la autenticación manualmente
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Realizar la solicitud GET con la autenticación básica
        MvcResult result = mockMvc.perform(get("/clients/1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes())))
                .andExpect(status().is4xxClientError())
                .andReturn();

    }


   /* @Test
    public void testUserControllerSuccess() throws Exception {
        mockMvc.perform(get("/clients/1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes())))
                .andExpect(status().is2xxSuccessful());
    }*/

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

        /*@Test
        public void testUpdateClientSuccess() throws Exception {
            ClientDto clientDto = new ClientDto("1", "Pepo-ito", "Arce", "DNI", "38083954", null);

            doNothing().when(clientService).update(clientDto);

            mockMvc.perform(put("/clients")
                            .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes()))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(clientDto)))
                    .andExpect(status().is2xxSuccessful());
        }*/

    @Test
    public void testUpdateClientSuccess() throws Exception {
        ClientDto clientDto = new ClientDto("1", "Pepo-ito", "Arce", "DNI", "38083954", null);

        doNothing().when(clientService).update(clientDto);

        mockMvc.perform(put("/clients")
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("rodrigoa:Rodri123".getBytes()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateClient() throws Exception {

        // Mock del servicio
        ClientService clientServiceMock = mock(ClientService.class);

        // Crear una instancia del controlador con el mock del servicio
        ClientController clientController = new ClientController(clientServiceMock);

        // Configurar el servicio para simular la actualización del cliente
        doNothing().when(clientService).update(any(ClientDto.class));

        // Configurar el controlador
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        ClientDto clientDto = new ClientDto();
        clientDto.setId("3");
        clientDto.setName("Pepo-ito");
        clientDto.setLastname("Arce");
        clientDto.setDocumentType("DNI");
        clientDto.setDocumentNumber("38083954");
        clientDto.setDateOfBirth(null);

        // Realizar la solicitud PUT
        mockMvc.perform(put("/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(clientDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateClientNotFound() throws Exception {

        // Mock del servicio
        ClientService clientServiceMock = mock(ClientService.class);
        doThrow(new RuntimeException()).when(clientServiceMock).update(any(ClientDto.class));

        // Crear una instancia del controlador con el mock del servicio
        ClientController clientController = new ClientController(clientServiceMock);

        // Configurar el servicio para simular la actualización del cliente

        // Configurar el controlador
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        ClientDto clientDto = new ClientDto();
        clientDto.setId("3");
        clientDto.setName("Pepo-ito");
        clientDto.setLastname("Arce");
        clientDto.setDocumentType("DNI");
        clientDto.setDocumentNumber("38083954");
        clientDto.setDateOfBirth(null);

        // Realizar la solicitud PUT
        mockMvc.perform(put("/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(clientDto)))
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
