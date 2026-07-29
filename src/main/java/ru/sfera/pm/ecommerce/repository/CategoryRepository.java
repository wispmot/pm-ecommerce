package ru.sfera.pm.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sfera.pm.ecommerce.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
