package com.example.order_jpa.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Data
public class ProductCreateDto {
    @NotNull
    @NotBlank
    private String name;
    @Range(min=100, max=1_000_000)
    private int price;
    @Max(9999)
    private int quantity;
}
