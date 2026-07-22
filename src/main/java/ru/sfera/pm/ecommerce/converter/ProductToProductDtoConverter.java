package ru.sfera.pm.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.model.entity.Product;

@Component
@RequiredArgsConstructor
public class ProductToProductDtoConverter implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }
}
