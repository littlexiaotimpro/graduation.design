package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticlePrimaryKeyInputDTO extends AbstractInputDTO<ArticlePrimaryKeyInputDTO,Article> {
    private String enarticle;

    @Override
    public Article convertToEntity() {
        final DTOConvert<ArticlePrimaryKeyInputDTO, Article> converter = converter();
        converter.setEntity(new Article());
        return converter.convert(this);
    }
}
