package ru.sfera.pm.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.sfera.pm.ecommerce.model.dto.ProductDto;

public interface ProductService {

    Page<ProductDto> getAll(Pageable pageable);

    ProductDto getProduct(Long id);

    Page<ProductDto> getInStock(Pageable pageable);

    ProductDto createProduct(ProductDto productDto);

    void deleteProduct(Long id);

    ProductDto updatePatchProduct(Long id, ProductDto productDto);

    ProductDto updatePutProduct(Long id, ProductDto productDto);
}
