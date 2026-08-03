package ru.sfera.pm.ecommerce.service.imple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.convert.ConversionService;

import ru.sfera.pm.ecommerce.exceptions.NotFoundException;
import ru.sfera.pm.ecommerce.exceptions.ValidationException;
import ru.sfera.pm.ecommerce.model.dto.ProductDto;
import ru.sfera.pm.ecommerce.model.entity.Category;
import ru.sfera.pm.ecommerce.model.entity.Product;
import ru.sfera.pm.ecommerce.repository.CategoryRepository;
import ru.sfera.pm.ecommerce.repository.ProductRepository;
import ru.sfera.pm.ecommerce.service.ProductService;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ConversionService conversionService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private static final String PRODUCT_EXIST = "Товар с таким именем уже существует";
    private static final String PRODUCT_NOT_FOUND = "Товара с таким ID не существует";
    private static final String CATEGORY_NOT_FOUND_NAME = "Категории с таким именем не существует";

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> conversionService.convert(product, ProductDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND, id));

        return conversionService.convert(product, ProductDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getInStock(Pageable pageable) {
        return productRepository.findByStockQuantityGreaterThan(0, pageable)
                .map(product -> conversionService.convert(product, ProductDto.class));
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        checkProductName(productDto.getName());
        Category category = getCategoryByName(productDto.getCategoryName());

        Product product = conversionService.convert(productDto, Product.class);
        Objects.requireNonNull(product).setCategory(category);
        Product saved = productRepository.save(product);

        log.info("Создан новый товар с id: {}", saved.getId());
        return conversionService.convert(saved, ProductDto.class);
    }

    private void checkProductName(String name) {
        if (productRepository.existsByNameIgnoreCase(name)) {
            throw new ValidationException(PRODUCT_EXIST);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND, id));

        productRepository.delete(product);
        log.info("Удален товар с id: {}", product.getId());
    }

    @Override
    @Transactional
    public ProductDto updatePatchProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND, id));

        if (productDto.getName() != null){
            if (!product.getName().equalsIgnoreCase(productDto.getName())){
                checkProductName(productDto.getName());
            }
        }

        Category category = getCategoryByName(productDto.getCategoryName());

        patchProduct(product, productDto, category);
        productRepository.save(product);
        log.info("Частично изменен товар с id: {}", product.getId());
        return conversionService.convert(product, ProductDto.class);
    }

    private void patchProduct(Product product, ProductDto dto, Category category){
        if (dto.getName() != null) product.setName(dto.getName());
        if (dto.getPrice() != null) product.setPrice(dto.getPrice());
        if (dto.getDescription() != null) product.setDescription(dto.getDescription());
        if (dto.getStockQuantity() != null) product.setStockQuantity(dto.getStockQuantity());
        if (category != null) product.setCategory(category);
    }

    @Override
    @Transactional
    public ProductDto updatePutProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND, id));

        if (!product.getName().equalsIgnoreCase(productDto.getName())){
            checkProductName(productDto.getName());
        }
        Category category = getCategoryByName(productDto.getCategoryName());

        product.copy(Objects.requireNonNull(conversionService.convert(productDto, Product.class)));
        product.setCategory(category);
        productRepository.save(product);
        log.info("Полностью изменен товар с id: {}", product.getId());
        return conversionService.convert(product, ProductDto.class);
    }

    private Category getCategoryByName(String categoryName) {
        if (categoryName == null) {
            return null;
        }

        return categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_NAME, categoryName));
    }

}
