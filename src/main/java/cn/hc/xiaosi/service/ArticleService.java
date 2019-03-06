package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.*;
import cn.hc.xiaosi.entity.Article;

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
     *
     * @param articlePrimaryKeyInputDTO
     * @return
     */
    ArticleOutputDTO clientFindByEnArticle(ArticlePrimaryKeyInputDTO articlePrimaryKeyInputDTO);

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
     * 新增文章
     *
     * @param articleInputDTO
     * @return
     */
    Message controlSaveArticle(ArticleInputDTO articleInputDTO);

    /**
     * 启用，禁用文章
     *
     * @param articleStatusInputDTO
     * @return
     */
    Message controlDeleteArticle(ArticleStatusInputDTO articleStatusInputDTO);

    /**
     * 编辑文章信息
     *
     * @param articleInputDTO
     * @return
     */
    Message controlUpdateArticle(ArticleInputDTO articleInputDTO);

}
