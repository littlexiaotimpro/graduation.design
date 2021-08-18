package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleCateTagInputDTO extends AbstractInputDTO<ArticleCateTagInputDTO, Article> {
    private String enCategory;

    private String enTag;

    @Override
    public Article convertToEntity() {
        DTOConvert<ArticleCateTagInputDTO, Article> converter = converter();
        converter.setEntity(new Article());
        return converter.convert(this);
    }

}
