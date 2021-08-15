package com.whoai.blog.dao;

import com.whoai.blog.entity.Article;
import com.whoai.blog.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文本数据访问接口
 */
@Mapper
public interface ArticleDAO {

    /**
     * 新增文章
     */
    Integer saveArticle(@Param("article") Article article);

    /**
     * 删除文章
     */
    Integer deleteArticle(@Param("article") Article article);

    /**
     * 编辑文章信息
     */
    Integer updateArticle(@Param("article") Article article);

    /**
     * 获取所有文章
     */
    List<Article> findAll();

    /**
     * 获取启用的文章信息
     */
    List<Article> findUsing();

    /**
     * 通过标识查询文章
     */
    Article findByEnArticle(@Param("enArticle") String enArticle);

    /**
     * 通过分类查询其下的所有文章
     */
    List<Article> findByEnCategory(@Param("enCategory") String enCategory);

    /**
     * 通过分类，标签查询其下的所有文章
     * PS: 由于文本存在多标签的性质（逗号分隔），因此查询时SQL需要做出相应优化
     */
    List<Article> findByEnCategoryEnTag(@Param("enCategory") String enCategory, @Param("enTag") String enTag);

    /**
     * 通过全局搜索，检索分类，标签，标题，简介等进行模糊查询
     */
    List<Article> findByKeyword(@Param("keyword") String keyword);

}