package com.codewithmosh.store.products;

import com.codewithmosh.store.products.dto.request.ProductFilterDto;
import com.codewithmosh.store.products.dto.response.ProductResponse;
import com.codewithmosh.store.products.entities.Product;
import com.codewithmosh.store.products.repositories.ProductRepository;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public Iterable<ProductResponse> getAllProduct(
        @Valid @RequestParam ProductFilterDto dto
    ) {
        List<Product> products;
        var categoryId = dto.getCategoryId();
        if (categoryId != null) products = productRepository.findByCategoryId(
            categoryId
        );
        else products = productRepository.findAllWithCategory();

        return products.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) return ResponseEntity.notFound().build();
        var pDto = productMapper.toDto(product);
        return ResponseEntity.ok(pDto);
    }
}
