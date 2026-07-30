package ru.sfera.pm.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sfera.pm.ecommerce.model.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Boolean existsByNameIgnoreCase(String name);

    Optional<Category> findByNameIgnoreCase(String name);

}
