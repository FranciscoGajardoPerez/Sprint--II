package com.example.be_java_hisp_w19_g2.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Post {
    private Integer postId;
    private Integer userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date = LocalDate.now();
    private Product product;
    private Integer category;
    private Double price;

    public Post(Integer postId, Integer userId, LocalDate date, Integer category, Double price) {
        this.postId = postId;
        this.userId = userId;
        this.date = date;
        this.category = category;
        this.price = price;
    }
}
