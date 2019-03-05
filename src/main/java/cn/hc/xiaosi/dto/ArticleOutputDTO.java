package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Article;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @ClassName ArticleOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/515:11
 */
@Data
public class ArticleOutputDTO {
    private String enarticle;

    private String encategory;

    private String entag;

    private String title;

    private String author;

    private String summary;

    private Integer readnum;

    private Date createtime;

    /**
     * 正向转化
     *
     * @return
     */
    public Article convertToArticle() {
        ArticleOutputDTOConvert articleOutputDTOConvert = new ArticleOutputDTOConvert();
        Article convert = articleOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param article
     * @return
     */
    public ArticleOutputDTO convertFor(Article article) {
        ArticleOutputDTOConvert articleOutputDTOConvert = new ArticleOutputDTOConvert();
        ArticleOutputDTO convert = articleOutputDTOConvert.reverse().convert(article);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class ArticleOutputDTOConvert extends Converter<ArticleOutputDTO, Article> {
        @Override
        protected Article doForward(ArticleOutputDTO articleOutputDTO) {
            throw new AssertionError("不支持正向转化方法");
        }

        @Override
        protected ArticleOutputDTO doBackward(Article article) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            BeanUtils.copyProperties(article, articleOutputDTO);
            return articleOutputDTO;
        }
    }

}
