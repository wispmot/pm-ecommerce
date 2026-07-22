package ru.sfera.pm.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.service.EcommerceService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class EcommerceController {

    private final EcommerceService ecommerceService;

    @GetMapping
    @Operation(summary = "Получить список товаров")
    public ResponseEntity<Page<ProductDto>> getAllProducts(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(ecommerceService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить товар по ID")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(ecommerceService.getProduct(id));
    }

    @GetMapping("/in-stock")
    @Operation(summary = "Только товары в наличии")
    public ResponseEntity<Page<ProductDto>> getProductsInStock(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(ecommerceService.getInStock(pageable));
    }

    @PostMapping
    @Operation(summary = "Создать товар")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(ecommerceService.createProduct(productDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить товар по ID")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        ecommerceService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частично обновить товар по ID")
    public ResponseEntity<ProductDto> updatePatchProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(ecommerceService.updatePatchProduct(id, productDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Полностью обновить товар по ID")
    public ResponseEntity<ProductDto> updatePutProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(ecommerceService.updatePutProduct(id, productDto));
    }
}
