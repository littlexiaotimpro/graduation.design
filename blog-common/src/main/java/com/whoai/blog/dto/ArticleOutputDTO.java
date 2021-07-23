package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleOutputDTO extends AbstractOutputDTO<ArticleOutputDTO, Article> {
    private String enarticle;

    private String encategory;

    private String entag;

    private String title;

    private String author;

    private String summary;

    /**
     * 文件路径
     */
    private String fileurl;

    private Integer readnum;

    private Date createtime;

    @Override
    public ArticleOutputDTO convertToDTO(Article article) {
        final DTOConvert<ArticleOutputDTO, Article> converter = converter();
        converter.setDto(new ArticleOutputDTO());
        return converter.reverse().convert(article);
    }
}
