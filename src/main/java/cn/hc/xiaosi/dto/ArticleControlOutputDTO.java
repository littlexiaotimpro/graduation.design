package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Article;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @ClassName ArticleControlOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/5/1316:28
 */
@Data
public class ArticleControlOutputDTO {
    private String enarticle;

    private String encategory;

    private String title;

    private String author;

    private String summary;

    private Date createtime;

    /**
     * 正向转化
     *
     * @return
     */
    public Article convertToArticle() {
        ArticleControlOutputDTOConvert articleControlOutputDTOConvert = new ArticleControlOutputDTOConvert();
        Article convert = articleControlOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param article
     * @return
     */
    public ArticleControlOutputDTO convertFor(Article article) {
        ArticleControlOutputDTOConvert articleControlOutputDTOConvert = new ArticleControlOutputDTOConvert();
        ArticleControlOutputDTO convert = articleControlOutputDTOConvert.reverse().convert(article);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class ArticleControlOutputDTOConvert extends Converter<ArticleControlOutputDTO, Article> {
        @Override
        protected Article doForward(ArticleControlOutputDTO articleControlOutputDTO) {
            throw new AssertionError("不支持正向转化方法");
        }

        @Override
        protected ArticleControlOutputDTO doBackward(Article article) {
            ArticleControlOutputDTO articleOutputDTO = new ArticleControlOutputDTO();
            BeanUtils.copyProperties(article, articleOutputDTO);
            return articleOutputDTO;
        }
    }

}
