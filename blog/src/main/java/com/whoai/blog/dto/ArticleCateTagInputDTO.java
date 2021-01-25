package com.whoai.blog.dto;

import com.whoai.blog.entity.Article;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ArticleCateTagInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/515:36
 */
@Data
public class ArticleCateTagInputDTO {
    private String encategory;

    private String entag;


    /**
     * 正向转化
     *
     * @return
     */
    public Article convertToArticle() {
        ArticleCateTagInputDTOConvert articleCateTagInputDTOConvert = new ArticleCateTagInputDTOConvert();
        Article convert = articleCateTagInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param article
     * @return
     */
    public ArticleCateTagInputDTO convertFor(Article article) {
        ArticleCateTagInputDTOConvert articleCateTagInputDTOConvert = new ArticleCateTagInputDTOConvert();
        ArticleCateTagInputDTO convert = articleCateTagInputDTOConvert.reverse().convert(article);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class ArticleCateTagInputDTOConvert extends Converter<ArticleCateTagInputDTO, Article> {
        @Override
        protected Article doForward(ArticleCateTagInputDTO articleCateTagInputDTO) {
            Article article = new Article();
            BeanUtils.copyProperties(articleCateTagInputDTO, article);
            return article;
        }

        @Override
        protected ArticleCateTagInputDTO doBackward(Article article) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
