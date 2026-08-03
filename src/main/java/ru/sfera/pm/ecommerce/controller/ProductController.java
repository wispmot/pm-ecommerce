package ru.sfera.pm.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.service.ProductService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Получить список товаров")
    public ResponseEntity<Page<ProductDto>> getAllProducts(@ParameterObject Pageable pageable) {
        log.debug("Запрос на получение списка всех товаров. Параметры страницы: {}", pageable);
        return ResponseEntity.ok(productService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить товар по ID")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        log.debug("Запрос на получение товара по id: {}", id);
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/in-stock")
    @Operation(summary = "Только товары в наличии")
    public ResponseEntity<Page<ProductDto>> getProductsInStock(@ParameterObject Pageable pageable){
        log.debug("Запрос на получение списка товаров в наличии. Параметры страницы: {}", pageable);
        return ResponseEntity.ok(productService.getInStock(pageable));
    }

    @PostMapping
    @Operation(summary = "Создать товар")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.debug("Запрос на создание товара. Данные: {}", productDto);
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить товар по ID")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("Запрос на удаление товара с id: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частично обновить товар по ID")
    public ResponseEntity<ProductDto> updatePatchProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        log.debug("Запрос на частичное изменение товара с id: {}", id);
        return ResponseEntity.ok(productService.updatePatchProduct(id, productDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Полностью обновить товар по ID")
    public ResponseEntity<ProductDto> updatePutProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        log.debug("Запрос на полное изменение товара с id: {}", id);
        return ResponseEntity.ok(productService.updatePutProduct(id, productDto));
    }
}
