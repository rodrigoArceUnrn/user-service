package ar.edu.unrn.userservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDTO {

    private String id;
    private String name;
    private String lastname;
    private String documentType;
    private String document;
    private LocalDate dateOfBirth;
}
