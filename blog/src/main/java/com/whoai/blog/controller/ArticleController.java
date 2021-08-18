package com.whoai.blog.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.dto.*;
import com.whoai.blog.entity.Article;
import com.whoai.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * ArticleController
 * 文本内容控制器
 *
 * @author XiaoSi
 * @date 2019/3/5
 */
@Api("文本内容控制器")
@RestController
@RequestMapping(value = "article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 用户登录时使用
     */
    @PostMapping(value = "/find/all")
    @ApiOperation(value = "查询所有文章")
    public ResponseResult<List<Article>> articles(HttpServletRequest request) {
        final List<Article> articles = articleService.findAll(request);
        return ResponseResult.success(articles, "查询成功");
    }

    @GetMapping(value = "/find/using")
    @ApiOperation(value = "获取所有启用文章")
    public ResponseResult<List<ArticleOutputDTO>> articleUsing() {
        final List<ArticleOutputDTO> using = articleService.findUsing();
        return ResponseResult.success(using, "查询成功");
    }

    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传文件")
    public ResponseResult<String> uploadFile(@PathParam("file") MultipartFile file, HttpServletRequest request) {
        final String fileUrl = articleService.uploadFile(file, request);
        return ResponseResult.success(fileUrl, "文件上传成功！");
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "新增文章")
    public ResponseResult<Void> saveArticle(@RequestBody ArticleInputDTO articleInputDTO, HttpServletRequest request) {
        final Integer integer = articleService.saveArticle(articleInputDTO, request);
        if (integer <= 0) {
            return ResponseResult.fail("新增失败");
        }
        return ResponseResult.success(null, "新增成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除文章")
    public ResponseResult<Void> deleteArticle(@RequestBody ArticlePrimaryKeyInputDTO primaryKeyInputDTO, HttpServletRequest request) {
        final Integer integer = articleService.deleteArticle(primaryKeyInputDTO, request);
        if (integer <= 0) {
            return ResponseResult.fail("删除失败");
        }
        return ResponseResult.success(null, "删除成功");
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "编辑文章信息")
    public ResponseResult<Void> updateArticle(@RequestBody ArticleInputDTO articleInputDTO, HttpServletRequest request) {
        final Integer integer = articleService.updateArticle(articleInputDTO, request);
        if (integer <= 0) {
            return ResponseResult.fail("编辑失败");
        }
        return ResponseResult.success(null, "编辑成功");
    }

    @PostMapping(value = "/html")
    @ApiOperation(value = "文本转html")
    public ResponseResult<String> markdownToHtml(@RequestBody ArticlePrimaryKeyInputDTO articlePrimaryKeyInputDTO) {
        final String toHtml = articleService.markdownToHtml(articlePrimaryKeyInputDTO);
        return ResponseResult.success(toHtml, "转换成功");
    }

    @PostMapping(value = "/cate_tag")
    @ApiOperation(value = "根据分类标签输出")
    public ResponseResult<List<ArticleOutputDTO>> articleOutputByCateTag(@RequestBody ArticleCateTagInputDTO articleCateTagInputDTO) {
        final List<ArticleOutputDTO> articleOutputs = articleService.findByEnCategoryOrEnTag(articleCateTagInputDTO);
        return ResponseResult.success(articleOutputs, "查询成功");
    }

    @PostMapping(value = "/record")
    @ApiOperation(value = "全局搜索文本")
    public ResponseResult<List<ArticleOutputDTO>> articleOutputByRecord(@RequestBody RecordInputDTO recordInputDTO) {
        final List<ArticleOutputDTO> articleOutputs = articleService.findByRecord(recordInputDTO);
        return ResponseResult.success(articleOutputs, "查询成功");
    }

}
