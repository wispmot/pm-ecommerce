package ru.sfera.pm.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.sfera.pm.ecommerce.model.dto.CategoryDto;
import ru.sfera.pm.ecommerce.model.entity.Category;

@Component
public class CategoryDtoToCategoryConverter implements Converter<CategoryDto, Category>{

    @Override
    public Category convert(CategoryDto dto){
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
