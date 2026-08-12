package com.codewithmosh.store.products.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * ProductFilterDto
 */
@Data
public class ProductFilterDto {

    @Min(1)
    @Max(255)
    private Byte categoryId;
}
