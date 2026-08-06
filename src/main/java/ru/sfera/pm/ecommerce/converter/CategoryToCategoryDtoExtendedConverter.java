package ru.sfera.pm.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.sfera.pm.ecommerce.model.dto.CategoryDtoExtended;
import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.model.entity.Category;
import ru.sfera.pm.ecommerce.model.entity.Product;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class CategoryToCategoryDtoExtendedConverter implements Converter<Category, CategoryDtoExtended> {

    private final Converter<Product, ProductDto> productConverter;

    @Override
    public CategoryDtoExtended convert(Category category){
        CategoryDtoExtended dto = new CategoryDtoExtended();
        dto.setId(category.getId());
        dto.setName(category.getName());

        if (category.getProducts() != null){
            List<ProductDto> productsDto = category.getProducts().stream()
                    .map(productConverter::convert)
                    .toList();

            dto.setProducts(productsDto);
        }
        return dto;
    }

}
