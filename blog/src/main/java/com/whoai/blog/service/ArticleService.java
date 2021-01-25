package com.whoai.blog.service;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.*;
import com.whoai.blog.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/515:50
 */
public interface ArticleService {
    /**
     * 客户端查询推荐的所有文章
     *
     * @return
     */
    ArrayList<ArticleOutputDTO> clientFindAll();

    /**
     * 客户端通过主键查询文章
     * 解析md文件地址，展示文章内容
     *
     * @param articlePrimaryKeyInputDTO
     * @return
     */
    String clientFindByEnArticle(ArticlePrimaryKeyInputDTO articlePrimaryKeyInputDTO);

    /**
     * 客户端通过分类，标签查询文章
     *
     * @param articleCateTagInputDTO
     * @return
     */
    ArrayList<ArticleOutputDTO> clientFindByCateTag(ArticleCateTagInputDTO articleCateTagInputDTO);

    /**
     * 客户端通过全局搜索查询文章
     *
     * @param recordInputDTO
     * @return
     */
    ArrayList<ArticleOutputDTO> clientFindByRecord(RecordInputDTO recordInputDTO);

    /**
     * 管理端查询所有文章
     *
     * @return
     */
    ArrayList<Article> controlFindAll();

    /**
     * 管理端获取启用文章
     *
     * @return
     */
    ArrayList<ArticleControlOutputDTO> controlFindUsing();

    /**
     * 上传文件
     *
     * @param file
     * @param category
     * @param request
     * @return
     */
    String controlSaveFile(MultipartFile file, String category, HttpServletRequest request) throws IOException;

    /**
     * 新增文章
     *
     * @param articleInputDTO
     * @param request
     * @return
     */
    Message controlSaveArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request);

    /**
     * 启用，禁用文章
     *
     * @param articleStatusInputDTO
     * @param request
     * @return
     */
    Message controlDeleteArticle(ArticleStatusInputDTO articleStatusInputDTO, HttpServletRequest request);

    /**
     * 编辑文章信息
     *
     * @param articleInputDTO
     * @param request
     * @return
     */
    Message controlUpdateArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request);

}
