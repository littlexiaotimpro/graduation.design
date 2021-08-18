package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleControlOutputDTO extends AbstractOutputDTO<ArticleControlOutputDTO, Article> {
    private String enArticle;

    private String enCategory;

    private String title;

    private String author;

    private String summary;

    private Date createTime;


    @Override
    public ArticleControlOutputDTO convertToDTO(Article article) {
        DTOConvert<ArticleControlOutputDTO, Article> converter = converter();
        converter.setDto(new ArticleControlOutputDTO());
        return converter.reverse().convert(article);
    }
}
