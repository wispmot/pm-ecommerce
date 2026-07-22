package ru.sfera.pm.ecommerce.service.imple;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.convert.ConversionService;

import ru.sfera.pm.ecommerce.exceptions.NotFoundException;
import ru.sfera.pm.ecommerce.exceptions.ValidationException;
import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.model.entity.Product;
import ru.sfera.pm.ecommerce.repository.ProductRepository;
import ru.sfera.pm.ecommerce.service.EcommerceService;

import java.util.Objects;

import static java.util.Objects.isNull;

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
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Товара с таким ID не существует"));

        return conversionService.convert(product, ProductDto.class);
    }

    @Override
    public Page<ProductDto> getInStock(Pageable pageable) {
        return productRepository.findByStockQuantityGreaterThan(0, pageable)
                .map(product -> conversionService.convert(product, ProductDto.class));
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        checkProductName(productDto.getName());

        Product product = conversionService.convert(productDto, Product.class);
        Product saved = productRepository.save(Objects.requireNonNull(product));

        return conversionService.convert(saved, ProductDto.class);
    }

    private void checkProductName(String name) {
        if (productRepository.existsByNameIgnoreCase(name)) {
            throw new ValidationException("Товар с таким именем уже существует");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Товара с таким ID не существует"));

        productRepository.delete(product);
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
