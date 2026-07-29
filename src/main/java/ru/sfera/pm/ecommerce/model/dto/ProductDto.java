package ru.sfera.pm.ecommerce.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductDto {

    @Schema(title = "Уникальный идентификатор")
    private Long id;

    @Schema(title = "Название товара")
    String name;

    @Schema(title = "Описание товара")
    String Description;

    @Schema(title = "Цена товара")
    BigDecimal price;

    @Schema(title = "Количество на складе")
    Integer stockQuantity;

    @Schema(title = "Дата создания")
    LocalDateTime createdAt;

    @Schema(title = "Дата обновления")
    LocalDateTime updatedAt;

    @Schema(title = "id категории")
    Long categoryId;

    @Schema(title = "Название категории")
    String categoryName;

}
