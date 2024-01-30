package ar.edu.unrn.userservice.dto;

import com.google.gson.Gson;
import lombok.Data;

/**
 * ClientDto.
 */
@Data
public class ClientDto {

  private String id;
  private String name;
  private String lastname;
  private String documentType;
  private String documentNumber;
  private String dateOfBirth;

  public ClientDto(String id, String name, String lastname, String documentType, String documentNumber, String dateOfBirth) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.documentType = documentType;
    this.documentNumber = documentNumber;
    this.dateOfBirth = dateOfBirth;
  }

  public String toJsonString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
