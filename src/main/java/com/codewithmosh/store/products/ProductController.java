package com.codewithmosh.store.products;

import com.codewithmosh.store.products.dto.request.ProductCreateRequest;
import com.codewithmosh.store.products.dto.request.ProductFilterDto;
import com.codewithmosh.store.products.dto.response.ProductDto;
import com.codewithmosh.store.products.entities.Product;
import com.codewithmosh.store.products.repositories.CategoryRepository;
import com.codewithmosh.store.products.repositories.ProductRepository;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * ProductController
 */
@RestController
@RequestMapping("/products")
@Validated
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public Iterable<ProductDto> getAllProduct(
        @Valid @RequestParam(required = false) ProductFilterDto dto
    ) {
        List<Product> products;
        if (dto != null && dto.getCategoryId() != null) {
            var categoryId = dto.getCategoryId();
            products = productRepository.findByCategoryId(categoryId);
        } else products = productRepository.findAllWithCategory();

        return products.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) return ResponseEntity.notFound().build();
        var pDto = productMapper.toDto(product);
        return ResponseEntity.ok(pDto);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
        @Valid @RequestBody ProductCreateRequest dto,
        UriComponentsBuilder uriBuilder
    ) {
        var category = categoryRepository
            .findById(dto.categoyId())
            .orElse(null);
        if (category == null) return ResponseEntity.badRequest().build();

        var product = productMapper.toEntity(dto);
        product.setCategory(category);

        productRepository.save(product);
        var newProductDto = productMapper.toDto(product);

        var uri = uriBuilder
            .path("/products/{id}")
            .buildAndExpand(newProductDto.id())
            .toUri();

        return ResponseEntity.created(uri).body(newProductDto);
    }

    public ResponseEntity<ProductDto> updateProduct(
        @PathVariable Long id,
        @Valid @RequestBody ProductDto dto
    ) {
        var category = categoryRepository
            .findById(dto.categoryId())
            .orElse(null);
        if (category == null) return ResponseEntity.badRequest().build();

        var product = productRepository.findById(id).orElse(null);
        if (product == null) return ResponseEntity.notFound().build();

        productMapper.update(dto, product);
        product.setCategory(category);
        productRepository.save(product);

        var body = productMapper.toDto(product);

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) return ResponseEntity.notFound().build();

        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
