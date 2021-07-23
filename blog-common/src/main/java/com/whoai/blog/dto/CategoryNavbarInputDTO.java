package com.whoai.blog.dto;

import com.whoai.blog.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryNavbarInputDTO extends AbstractInputDTO<CategoryNavbarInputDTO, Category> {
    /**
     * 导航标识
     */
    private String ennav;

    @Override
    public Category convertToEntity() {
        final DTOConvert<CategoryNavbarInputDTO, Category> converter = converter();
        converter.setEntity(new Category());
        return converter.convert(this);
    }
}
