package com.whoai.blog.service.impl;

import com.whoai.blog.dao.ArticleDAO;
import com.whoai.blog.dto.*;
import com.whoai.blog.entity.Article;
import com.whoai.blog.exception.ResourcesNotFoundException;
import com.whoai.blog.file.FileService;
import com.whoai.blog.service.ArticleService;
import com.whoai.blog.utils.JWTUtil;
import com.whoai.blog.utils.MDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;
    private final FileService fileService;

    public ArticleServiceImpl(ArticleDAO articleDAO, FileService fileService) {
        this.articleDAO = articleDAO;
        this.fileService = fileService;
    }

    @Override
    public List<Article> findAll(HttpServletRequest request) {
        final String operator = JWTUtil.parseCookies(request);
        if (Objects.isNull(operator)) {
            throw new ResourcesNotFoundException("用户未登录，无法操作");
        }
        return articleDAO.findAll();
    }

    @Override
    public List<ArticleOutputDTO> findUsing() {
        final List<Article> articles = articleDAO.findUsing();
        return transferArticleOutputs(articles);
    }

    @Override
    public String markdownToHtml(ArticlePrimaryKeyInputDTO primaryKeyInputDTO) {
        final String enArticle = primaryKeyInputDTO.getEnArticle();
        final Article article = articleDAO.findByEnArticle(enArticle);
        if (Objects.isNull(article) || Objects.isNull(article.getFileUrl())) {
            throw new ResourcesNotFoundException("数据资源不存在!");
        }
        return MDUtil.changeMDToHtml(article.getFileUrl());
    }

    @Override
    public List<ArticleOutputDTO> findByEnCategoryOrEnTag(ArticleCateTagInputDTO articleCateTagInputDTO) {
        final String enCategory = articleCateTagInputDTO.getEnCategory();
        final String enTag = articleCateTagInputDTO.getEnTag();
        final List<Article> articles;
        if (Objects.isNull(enTag)) {
            articles = articleDAO.findByEnCategory(enCategory);
        } else {
            articles = articleDAO.findByEnCategoryEnTag(enCategory, enTag);
        }
        return transferArticleOutputs(articles);
    }

    @Override
    public List<ArticleOutputDTO> findByRecord(RecordInputDTO recordInputDTO) {
        final List<Article> articles = articleDAO.findByKeyword(recordInputDTO.getKeyword());
        return transferArticleOutputs(articles);
    }

    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request){
        final String operator = JWTUtil.parseCookies(request);
        if (Objects.isNull(operator)) {
            throw new ResourcesNotFoundException("用户未登录，无法操作");
        }
        final String fileName = fileService.storeFile(file);
        final String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/")
                .path(fileName)
                .toUriString();
        // 返回文件的可访问地址(非下载地址)
        return fileUrl;
    }

    @Override
    public Integer saveArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request) {
        final String operator = JWTUtil.parseCookies(request);
        if (Objects.isNull(operator)) {
            throw new ResourcesNotFoundException("用户未登录，无法操作");
        }
        final Article article = articleInputDTO.convertToEntity();
        return articleDAO.saveArticle(article);
    }

    @Override
    public Integer deleteArticle(ArticlePrimaryKeyInputDTO primaryKeyInputDTO, HttpServletRequest request) {
        final String operator = JWTUtil.parseCookies(request);
        if (Objects.isNull(operator)) {
            throw new ResourcesNotFoundException("用户未登录，无法操作");
        }
        final Article article = primaryKeyInputDTO.convertToEntity();
        return articleDAO.deleteArticle(article);
    }

    @Override
    public Integer updateArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request) {
        final String operator = JWTUtil.parseCookies(request);
        if (Objects.isNull(operator)) {
            throw new ResourcesNotFoundException("用户未登录，无法操作");
        }
        final Article article = articleInputDTO.convertToEntity();
        return articleDAO.updateArticle(article);
    }

    private List<ArticleOutputDTO> transferArticleOutputs(List<Article> articles) {
        Supplier<ArticleOutputDTO> supplier = ArticleOutputDTO::new;
        return articles.stream()
                .map(article -> {
                    final ArticleOutputDTO articleOutputDTO = supplier.get();
                    return articleOutputDTO.convertToDTO(article);
                }).collect(Collectors.toList());
    }
}
