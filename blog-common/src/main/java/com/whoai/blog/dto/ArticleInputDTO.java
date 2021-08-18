package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleInputDTO extends AbstractInputDTO<ArticleInputDTO,Article> {
    private String enArticle;

    private String enNav;

    private String enCategory;

    private String enTag;

    private String adminId;

    private String title;

    private String author;

    private String summary;

    private String fileUrl;

    private Integer readNum;

    private Integer status;

    @Override
    public Article convertToEntity() {
        final DTOConvert<ArticleInputDTO, Article> converter = converter();
        converter.setEntity(new Article());
        return converter.convert(this);
    }
}
