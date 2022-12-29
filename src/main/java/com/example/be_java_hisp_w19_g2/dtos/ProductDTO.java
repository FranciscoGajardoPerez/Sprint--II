package com.example.be_java_hisp_w19_g2.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductDTO {

  @NotBlank(message = "El id de producto no puede estar vacío.")
  @Min(value = 1, message = "El id de producto debe ser mayor a cero.")
  Integer productId;

  @NotBlank(message = "El nombre de producto no puede estar vacío.")
  @Size(max = 40, message = "La longitud del nombre del producto no puede superar los 40 caracteres.")
  @Pattern(regexp="^[a-zA-Z0-9]*$",
          message = "El nombre de producto no puede contener caracteres especiales.")
  String productName;

  @NotBlank(message = "El tipo de producto no puede estar vacío.")
  @Size(max = 15, message = "La longitud del tipo de producto no puede superar los 15 caracteres.")
  @Pattern(regexp="^[^%&\\$\\s]+$",
          message = "El tipo de producto no puede contener caracteres especiales, ni espacios.")
  String type;

  @NotBlank(message = "El campo marca de producto no puede estar vacío.")
  @Size(max = 20, message = "La longitud de la marca no puede superar los 20 caracteres.")
  @Pattern(regexp="^[a-zA-Z0-9]*$",
          message = "La marca de producto no puede contener caracteres especiales.")
  String brand;

  @NotBlank(message = "El campo color de producto no puede estar vacío.")
  @Size(max = 15, message = "La longitud del color de producto no puede superar los 15 caracteres.")
  @Pattern(regexp="^[^%&\\$\\s]+$",
          message = "El color de producto no puede contener caracteres especiales, ni espacios.")
  String color;

  @Size(max = 80, message = "La longitud de la notas de producto no puede superar los 80 caracteres.")
  @Pattern(regexp="^[a-zA-Z0-9]*$",
          message = "Las notas de producto no pueden contener caracteres especiales.")
  String notes;
}
