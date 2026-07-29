package ru.sfera.pm.ecommerce.service.imple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sfera.pm.ecommerce.model.dto.CategoryDto;
import ru.sfera.pm.ecommerce.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public Page<CategoryDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public CategoryDto getCategory(Long id) {
        return null;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
