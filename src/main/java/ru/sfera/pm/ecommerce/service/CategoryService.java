package ru.sfera.pm.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sfera.pm.ecommerce.model.dto.CategoryDto;
import ru.sfera.pm.ecommerce.model.dto.CategoryDtoExtended;

public interface CategoryService {
    Page<CategoryDto> getAll(Pageable pageable);

    CategoryDto createCategory(CategoryDto categoryDto);

    void deleteCategory(Long id);

    CategoryDtoExtended getCategoryWithProducts(Long id);
}
