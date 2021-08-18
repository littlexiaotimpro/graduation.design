package com.whoai.blog.service;

import com.whoai.blog.dto.*;
import com.whoai.blog.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ArticleService 文本内容服务
 * 主要功能：
 * - 文本的增删改查
 * - 文本文件上传
 *
 * @author XiaoSi
 * @date created at 2019/3/5, updated at 2021/8/18
 */
public interface ArticleService {
    /**
     * 查询所有文章
     */
    List<Article> findAll(HttpServletRequest request);

    /**
     * 查询所有启用文章
     */
    List<ArticleOutputDTO> findUsing();

    /**
     * 客户端通过主键查询文章
     * 解析md文件地址，展示文章内容
     *
     * @param primaryKeyInputDTO 文章英文标识
     */
    String markdownToHtml(ArticlePrimaryKeyInputDTO primaryKeyInputDTO);

    /**
     * 通过分类，标签查询文章
     *
     * @param articleCateTagInputDTO 通过分类及标签查询文章
     */
    List<ArticleOutputDTO> findByEnCategoryOrEnTag(ArticleCateTagInputDTO articleCateTagInputDTO);

    /**
     * 通过全局搜索文章
     *
     * @param recordInputDTO 搜索关键字
     */
    List<ArticleOutputDTO> findByRecord(RecordInputDTO recordInputDTO);


    /**
     * 在新增文章记录的基础上，上传文章内容对应文件，填充数据
     *
     * @param file    文件
     * @param request 请求
     */
    String uploadFile(MultipartFile file, HttpServletRequest request);

    /**
     * 新增文章
     *
     * @param articleInputDTO 文本数据
     * @param request         请求
     */
    Integer saveArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request);

    /**
     * 删除文章
     *
     * @param request 请求
     */
    Integer deleteArticle(ArticlePrimaryKeyInputDTO primaryKeyInputDTO, HttpServletRequest request);

    /**
     * 编辑文章信息
     *
     * @param articleInputDTO 文本数据
     * @param request         请求
     */
    Integer updateArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request);

}
