package com.example.be_java_hisp_w19_g2.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDTO {
  @NotBlank(message = "El id de usuario no puede estar vacío.")
  @Min(value = 1, message = "El id de usuario debe ser mayor a cero.")
  Integer userId;

  @Size(max = 15, message = "La longitud del nombre de usuario no puede superar los 15 caracteres.")
  String userName;
}
