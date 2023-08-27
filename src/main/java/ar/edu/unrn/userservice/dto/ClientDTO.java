package ar.edu.unrn.userservice.dto;

import com.google.gson.Gson;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDTO {

    private String id;
    private String name;
    private String lastname;
    private String documentType;
    private String documentNumber;
    private LocalDate dateOfBirth;

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
