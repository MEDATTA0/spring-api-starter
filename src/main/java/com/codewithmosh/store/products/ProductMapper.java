package com.codewithmosh.store.products;

import com.codewithmosh.store.products.dto.response.ProductResponse;
import com.codewithmosh.store.products.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * StoreApplication
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductResponse toDto(Product p);
}
