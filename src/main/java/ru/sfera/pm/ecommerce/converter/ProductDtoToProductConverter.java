package ru.sfera.pm.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.model.entity.Product;

@Component
@RequiredArgsConstructor
public class ProductDtoToProductConverter implements Converter<ProductDto, Product>{

    @Override
    public Product convert(ProductDto dto) {
        Product product = new Product();
        //product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCreatedAt(dto.getCreatedAt());
        product.setUpdatedAt(dto.getUpdatedAt());
        return product;
    }

}
