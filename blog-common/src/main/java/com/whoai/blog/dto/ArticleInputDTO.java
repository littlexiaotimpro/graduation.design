package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleInputDTO extends AbstractInputDTO<ArticleInputDTO,Article> {
    private String enarticle;

    private String ennav;

    private String encategory;

    private String entag;

    private String adminid;

    private String title;

    private String author;

    private String summary;

    private String fileurl;

    private Integer readnum;

    private Integer status;

    @Override
    public Article convertToEntity() {
        final DTOConvert<ArticleInputDTO, Article> converter = converter();
        converter.setEntity(new Article());
        return converter.convert(this);
    }
}
