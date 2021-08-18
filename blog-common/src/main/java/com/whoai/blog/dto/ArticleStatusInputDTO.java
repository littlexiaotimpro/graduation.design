package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleStatusInputDTO extends AbstractInputDTO<ArticleStatusInputDTO,Article> {
    private String enArticle;

    private Integer status;

    @Override
    public Article convertToEntity() {
        final DTOConvert<ArticleStatusInputDTO, Article> converter = converter();
        converter.setEntity(new Article());
        return converter.convert(this);
    }
}
