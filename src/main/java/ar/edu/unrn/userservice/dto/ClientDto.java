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
  private UserDto userDto;

  /**
   * Crea un nuevo cliente con la información proporcionada.
   *
   * @param id el identificador único del cliente
   * @param name el nombre del cliente
   * @param lastname el apellido del cliente
   * @param documentType el tipo de documento del cliente
   * @param documentNumber el número de documento del cliente
   * @param dateOfBirth la fecha de nacimiento del cliente
   */
  public ClientDto(String id, String name, String lastname, String documentType,
                   String documentNumber, String dateOfBirth) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.documentType = documentType;
    this.documentNumber = documentNumber;
    this.dateOfBirth = dateOfBirth;
  }

  public ClientDto() {
  }

  public String toJsonString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
