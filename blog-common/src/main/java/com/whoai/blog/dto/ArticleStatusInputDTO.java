package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ArticleStatusInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/515:24
 */
@Data
public class ArticleStatusInputDTO {
    private String enarticle;

    private Integer status;

    /**
     * 正向转化
     *
     * @return
     */
    public Article convertToArticle() {
        ArticleStatusInputDTOConvert articleStatusInputDTOConvert = new ArticleStatusInputDTOConvert();
        Article convert = articleStatusInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param article
     * @return
     */
    public ArticleStatusInputDTO convertFor(Article article) {
        ArticleStatusInputDTOConvert articleStatusInputDTOConvert = new ArticleStatusInputDTOConvert();
        ArticleStatusInputDTO convert = articleStatusInputDTOConvert.reverse().convert(article);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class ArticleStatusInputDTOConvert extends Converter<ArticleStatusInputDTO, Article> {
        @Override
        protected Article doForward(ArticleStatusInputDTO articleStatusInputDTO) {
            Article article = new Article();
            BeanUtils.copyProperties(articleStatusInputDTO, article);
            return article;
        }

        @Override
        protected ArticleStatusInputDTO doBackward(Article article) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
