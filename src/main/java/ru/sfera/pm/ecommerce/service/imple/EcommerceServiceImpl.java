package ru.sfera.pm.ecommerce.service.imple;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.convert.ConversionService;

import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.model.entity.Product;
import ru.sfera.pm.ecommerce.repository.ProductRepository;
import ru.sfera.pm.ecommerce.service.EcommerceService;

@Service
@RequiredArgsConstructor
public class EcommerceServiceImpl implements EcommerceService {

    private final ConversionService conversionService;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> conversionService.convert(product, ProductDto.class));
    }

    @Override
    public ProductDto getProduct(Long id) {
        return null;
    }

    @Override
    public Page<ProductDto> getInStock(Pageable pageable) {
        return null;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public ProductDto updatePatchProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updatePutProduct(Long id, ProductDto productDto) {
        return null;
    }

}
