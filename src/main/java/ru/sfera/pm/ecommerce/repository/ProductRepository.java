package ru.sfera.pm.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfera.pm.ecommerce.model.entity.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Boolean existsByNameIgnoreCase(String name);

    Page<Product> findByStockQuantityGreaterThan(int quantity, Pageable pageable);
}
