package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleOutputDTO extends AbstractOutputDTO<ArticleOutputDTO, Article> {
    private String enArticle;

    private String enCategory;

    private String enTag;

    private String title;

    private String author;

    private String summary;

    /**
     * 文件路径
     */
    private String fileUrl;

    private Integer readNum;

    private Date createTime;

    @Override
    public ArticleOutputDTO convertToDTO(Article article) {
        final DTOConvert<ArticleOutputDTO, Article> converter = converter();
        converter.setDto(new ArticleOutputDTO());
        return converter.reverse().convert(article);
    }
}
