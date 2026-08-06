package ru.sfera.pm.ecommerce.service.imple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sfera.pm.ecommerce.exceptions.NotFoundException;
import ru.sfera.pm.ecommerce.exceptions.ValidationException;
import ru.sfera.pm.ecommerce.model.dto.CategoryDto;
import ru.sfera.pm.ecommerce.model.dto.CategoryDtoExtended;
import ru.sfera.pm.ecommerce.model.entity.Category;
import ru.sfera.pm.ecommerce.repository.CategoryRepository;
import ru.sfera.pm.ecommerce.service.CategoryService;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ConversionService conversionService;
    private final CategoryRepository categoryRepository;

    private static final String CATEGORY_NOT_FOUND = "Категории с таким id не существует";
    private static final String CATEGORY_EXIST = "Категория с таким именем уже существует";

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDto> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(category -> conversionService.convert(category, CategoryDto.class));
    }

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        checkCategoryName(categoryDto.getName());

        Category category = conversionService.convert(categoryDto, Category.class);
        Category saved = categoryRepository.save(Objects.requireNonNull(category));

        log.info("Создана новая категория с id: {}", saved.getId());
        return conversionService.convert(saved, CategoryDto.class);
    }

    private void checkCategoryName(String name) {
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new ValidationException(CATEGORY_EXIST);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND, id));

        categoryRepository.delete(category);
        log.info("Удалена категория (а также все товары, относящиеся к ней) с id: {}", category.getId());

    }

    @Override
    public CategoryDtoExtended getCategoryWithProducts(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND, id));

        return conversionService.convert(category, CategoryDtoExtended.class);
    }
}
