package ru.sfera.pm.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sfera.pm.ecommerce.model.dto.CategoryDto;
import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.service.CategoryService;
import ru.sfera.pm.ecommerce.service.EcommerceService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final EcommerceService ecommerceService;

    @GetMapping
    @Operation(summary = "Получить список категорий")
    public ResponseEntity<Page<CategoryDto>> getAllCategories(@ParameterObject Pageable pageable) {
        log.debug("Запрос на получение списка всех категорий. Параметры страницы: {}", pageable);
        return ResponseEntity.ok(categoryService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию о категории по ID")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        log.debug("Запрос на получение информации о категории по id: {}", id);
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping("/{id}/products")
    @Operation(summary = "Получить список товаров по ID категории")
    public ResponseEntity<Page<ProductDto>> getProductsByCategory(@PathVariable Long id,
                                                                  @ParameterObject Pageable pageable) {
        log.debug("Запрос на получение списка товаров по id категории: {}", id);
        return ResponseEntity.ok(ecommerceService.getProductsByCategory(id, pageable));
    }

    @PostMapping
    @Operation(summary = "Создать категорию")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        log.debug("Запрос на создание категории. Данные: {}", categoryDto);
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить категорию по ID")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("Запрос на удаление категории с id: {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
