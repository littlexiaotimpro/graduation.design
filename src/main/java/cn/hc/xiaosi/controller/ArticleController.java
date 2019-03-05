package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.*;
import cn.hc.xiaosi.entity.Article;
import cn.hc.xiaosi.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/516:14
 */
@RestController
@RequestMapping(value = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端输出")
    public ArrayList<Article> articles() {
        return articleService.controlFindAll();
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增文章")
    public Message saveArticle(@RequestBody ArticleInputDTO articleInputDTO) {
        return articleService.controlSaveArticle(articleInputDTO);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用文章")
    public Message deleteArticle(@RequestBody ArticleStatusInputDTO articleStatusInputDTO) {
        return articleService.controlDeleteArticle(articleStatusInputDTO);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑文章信息")
    public Message updateArticle(@RequestBody ArticleInputDTO articleInputDTO) {
        return articleService.controlUpdateArticle(articleInputDTO);
    }

    /**
     * 客户端
     *
     * @return
     */
    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端输出")
    public ArrayList<ArticleOutputDTO> articleOutputDTOS() {
        return articleService.clientFindAll();
    }

    @RequestMapping(value = "cate_tag")
    @ApiOperation(value = "客户端根据分类标签输出")
    public ArrayList<ArticleOutputDTO> articleOutputByCateTag(@RequestBody ArticleCateTagInputDTO articleCateTagInputDTO) {
        return articleService.clientFindByCateTag(articleCateTagInputDTO);
    }

    @RequestMapping(value = "cate_tag")
    @ApiOperation(value = "客户端根据全局搜索输出")
    public ArrayList<ArticleOutputDTO> articleOutputByRecord(@RequestBody RecordInputDTO recordInputDTO) {
        return articleService.clientFindByRecord(recordInputDTO);
    }

}
