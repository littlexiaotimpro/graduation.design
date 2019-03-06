package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Article;
import cn.hc.xiaosi.entity.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * ArticleDAO继承基类
 */
@Repository
public interface ArticleDAO extends MyBatisBaseDao<Article, String> {

    /**
     * 管理端获取所有文章
     *
     * @return
     */
    ArrayList<Article> findAll();

    /**
     * 新增文章
     *
     * @param article
     * @return
     */
    Integer saveArticle(@Param("article") Article article);

    /**
     * 启用，禁用文章
     *
     * @param article
     * @return
     */
    Integer deleteArticle(@Param("article") Article article);

    /**
     * 编辑文章信息
     *
     * @param article
     * @return
     */
    Integer updateArticle(@Param("article") Article article);

    /**
     * 查询所有启用的文章
     *
     * @return
     */
    ArrayList<Article> findAllUsing();

    /**
     * 通过主键查询文章
     *
     * @param article
     * @return
     */
    Article findByEnArticle(@Param("article") Article article);

    /**
     * 通过分类查询其下的所有文章
     *
     * @param article
     * @return
     */
    ArrayList<Article> findUsingByEnCategory(@Param("article") Article article);

    /**
     * 通过分类，标签查询其下的所有文章
     *
     * @param article
     * @return
     */
    ArrayList<Article> findUsingByEnCategoryEnTags(@Param("article") Article article);

    /**
     * 通过全局搜索，检索分类，标签，标题，简介等进行模糊查询
     *
     * @param record
     * @return
     */
    ArrayList<Article> findUsingByRecord(@Param("record") Record record);

}