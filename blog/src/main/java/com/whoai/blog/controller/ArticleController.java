package com.whoai.blog.controller;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.*;
import com.whoai.blog.entity.Article;
import com.whoai.blog.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

/**
 * @className ArticleController
 * @description 文章
 * @author XiaoSi
 * @date 2019/3/516:14
 */
@RestController
@RequestMapping(value = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    /**
//     * 管理端
//     *
//     * @return
//     */
//    @PostMapping(value = "control")
//    @ApiOperation(value = "管理端输出")
//    public List<Article> articles() {
//        return articleService.controlFindAll();
//    }
//
//    @PostMapping(value = "using")
//    @ApiOperation(value = "管理端获取启用文章")
//    public List<Article> articleUsing() {
//        return articleService.controlFindUsing();
//    }
//
//    @PostMapping(value = "file")
//    @ApiOperation(value = "上传文件")
//    public String saveFile(@PathParam("articleUrl") MultipartFile file, @PathParam("category") String category, HttpServletRequest request) throws IOException {
//        return articleService.controlSaveFile(file, category, request);
//    }
//
//    @PostMapping(value = "save")
//    @ApiOperation(value = "新增文章")
//    public Message
//    saveArticle(@RequestBody ArticleInputDTO articleInputDTO, HttpServletRequest request) {
//        return articleService.controlSaveArticle(articleInputDTO, request);
//    }
//
//    @PostMapping(value = "delete")
//    @ApiOperation(value = "启用，禁用文章")
//    public Message deleteArticle(@RequestBody ArticleStatusInputDTO articleStatusInputDTO, HttpServletRequest request) {
//        return articleService.controlDeleteArticle(articleStatusInputDTO, request);
//    }
//
//    @PostMapping(value = "update")
//    @ApiOperation(value = "编辑文章信息")
//    public Message updateArticle(@RequestBody ArticleInputDTO articleInputDTO, HttpServletRequest request) {
//        return articleService.controlUpdateArticle(articleInputDTO, request);
//    }
//
//    /**
//     * 客户端
//     *
//     * @return
//     */
//    @PostMapping(value = "client")
//    @ApiOperation(value = "客户端输出")
//    public List<ArticleOutputDTO> articleOutputDTOS() {
//        return articleService.clientFindAll();
//    }
//
//    @PostMapping(value = "primary")
//    @ApiOperation(value = "客户端主键查询")
//    public String articlePrimaryKey(@RequestBody ArticlePrimaryKeyInputDTO articlePrimaryKeyInputDTO) {
//        return articleService.clientFindByEnArticle(articlePrimaryKeyInputDTO);
//    }
//
//    @PostMapping(value = "cate_tag")
//    @ApiOperation(value = "客户端根据分类标签输出")
//    public List<ArticleOutputDTO> articleOutputByCateTag(@RequestBody ArticleCateTagInputDTO articleCateTagInputDTO) {
//        return articleService.clientFindByCateTag(articleCateTagInputDTO);
//    }
//
//    @PostMapping(value = "record")
//    @ApiOperation(value = "客户端根据全局搜索输出")
//    public List<ArticleOutputDTO> articleOutputByRecord(@RequestBody RecordInputDTO recordInputDTO) {
//        return articleService.clientFindByRecord(recordInputDTO);
//    }

}
