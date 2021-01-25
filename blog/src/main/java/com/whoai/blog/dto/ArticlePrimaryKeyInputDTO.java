package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ArticlePrimaryKeyInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/615:02
 */
@Data
public class ArticlePrimaryKeyInputDTO {
    private String enarticle;

    /**
     * 正向转化
     *
     * @return
     */
    public Article convertToArticle() {
        ArticlePrimaryKeyInputDTOCovert articlePrimaryKeyInputDTOCovert = new ArticlePrimaryKeyInputDTOCovert();
        Article convert = articlePrimaryKeyInputDTOCovert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param article
     * @return
     */
    public ArticlePrimaryKeyInputDTO convertFor(Article article) {
        ArticlePrimaryKeyInputDTOCovert articlePrimaryKeyInputDTOCovert = new ArticlePrimaryKeyInputDTOCovert();
        ArticlePrimaryKeyInputDTO convert = articlePrimaryKeyInputDTOCovert.reverse().convert(article);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class ArticlePrimaryKeyInputDTOCovert extends Converter<ArticlePrimaryKeyInputDTO, Article> {
        @Override
        protected Article doForward(ArticlePrimaryKeyInputDTO articlePrimaryKeyInputDTO) {
            Article article = new Article();
            BeanUtils.copyProperties(articlePrimaryKeyInputDTO, article);
            return article;
        }

        @Override
        protected ArticlePrimaryKeyInputDTO doBackward(Article article) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
