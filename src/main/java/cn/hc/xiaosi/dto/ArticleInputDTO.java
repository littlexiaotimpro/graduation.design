package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Article;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ArticleInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/515:32
 */
@Data
public class ArticleInputDTO {
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

    /**
     * 正向转化
     *
     * @return
     */
    public Article convertToArticle() {
        ArticleInputDTOConvert articleInputDTOConvert = new ArticleInputDTOConvert();
        Article convert = articleInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param article
     * @return
     */
    public ArticleInputDTO convertFor(Article article) {
        ArticleInputDTOConvert articleInputDTOConvert = new ArticleInputDTOConvert();
        ArticleInputDTO convert = articleInputDTOConvert.reverse().convert(article);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class ArticleInputDTOConvert extends Converter<ArticleInputDTO, Article> {
        @Override
        protected Article doForward(ArticleInputDTO articleInputDTO) {
            Article article = new Article();
            BeanUtils.copyProperties(articleInputDTO, article);
            return article;
        }

        @Override
        protected ArticleInputDTO doBackward(Article article) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
