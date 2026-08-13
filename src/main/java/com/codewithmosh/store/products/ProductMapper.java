package com.codewithmosh.store.products;

import com.codewithmosh.store.products.dto.request.ProductCreateRequest;
import com.codewithmosh.store.products.dto.response.ProductDto;
import com.codewithmosh.store.products.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * StoreApplication
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product p);

    Product toEntity(ProductCreateRequest dto);

    @Mapping(target = "id", ignore = true)
    void update(ProductDto dto, @MappingTarget Product product);
}
