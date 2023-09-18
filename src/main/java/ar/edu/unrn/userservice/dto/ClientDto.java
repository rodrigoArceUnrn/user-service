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

  public String toJsonString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
