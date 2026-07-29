package ru.sfera.pm.ecommerce.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

    @Schema(title = "Уникальный идентификатор")
    private Long id;

    @Schema(title = "Название категории")
    private String name;
}
