package com.example.be_java_hisp_w19_g2.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.GregorianCalendar;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostDTO {
    @NotBlank(message = "El id no puede estar vacío.")
    @Min(value = 1, message = "El id debe ser mayor a cero.")
    Integer userId;

    @NotBlank(message = "La fecha no puede estar vacía.")
    @NotNull(message = "La fecha no puede estar vacía.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date = LocalDate.now();

    ProductDTO product;

    @NotBlank(message = "El campo categoría no puede estar vacío.")
    Integer category;

    @NotNull(message = "El campo precio no puede estar vacío.")
    @DecimalMin(value = "0.0", message = "El precio minimo por producto es de 0.0")
    @DecimalMax(value = "10000000.0", message = "El precio máximo por producto es de 10.000.000")
    Double price;
}