package ru.sfera.pm.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.model.entity.Category;
import ru.sfera.pm.ecommerce.model.entity.Product;

@Component
public class ProductDtoToProductConverter implements Converter<ProductDto, Product>{

    @Override
    public Product convert(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());

        return product;
    }
}
