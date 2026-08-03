package ru.sfera.pm.ecommerce.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDtoExtended extends CategoryDto{

    @Schema(title = "Список входящих товаров")
    private List<ProductDto> products;

}
