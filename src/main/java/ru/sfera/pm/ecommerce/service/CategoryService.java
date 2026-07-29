package ru.sfera.pm.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sfera.pm.ecommerce.model.dto.CategoryDto;

public interface CategoryService {
    Page<CategoryDto> getAll(Pageable pageable);

    CategoryDto getCategory(Long id);

    CategoryDto createCategory(CategoryDto categoryDto);

    void deleteCategory(Long id);
}
