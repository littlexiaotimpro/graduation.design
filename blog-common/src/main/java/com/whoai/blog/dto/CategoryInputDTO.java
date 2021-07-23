package com.whoai.blog.dto;

import com.whoai.blog.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryInputDTO extends AbstractInputDTO<CategoryInputDTO, Category> {
    /**
     * 类别标识
     */
    private String encategory;

    /**
     * 导航标识
     */
    private String ennav;

    /**
     * 名称
     */
    private String caption;

    /**
     * 序号
     */
    private Integer catelevel;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    @Override
    public Category convertToEntity() {
        final DTOConvert<CategoryInputDTO, Category> converter = converter();
        converter.setEntity(new Category());
        return converter.convert(this);
    }

}
