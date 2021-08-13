package com.whoai.blog.service.impl;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dao.ArticleDAO;
import com.whoai.blog.dto.*;
import com.whoai.blog.entity.Article;
import com.whoai.blog.entity.Record;
import com.whoai.blog.service.ArticleService;
import com.whoai.blog.utils.JWTUtil;
import com.whoai.blog.utils.MDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List<ArticleOutputDTO> clientFindAll() {
        List<ArticleOutputDTO> arrayList = new ArrayList<ArticleOutputDTO>();
        for (Article article : articleDAO.findUsing()) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO = articleOutputDTO.convertToDTO(article);
            arrayList.add(articleOutputDTO);
        }
        return arrayList;
    }


    @Override
    public String clientFindByEnArticle(ArticlePrimaryKeyInputDTO articlePrimaryKeyInputDTO) {
        Article article = articlePrimaryKeyInputDTO.convertToEntity();
        Article articleHtml = articleDAO.findByEnArticle(article);
        return MDUtil.changeMDToHtml(articleHtml.getFileUrl());
    }

    @Override
    public List<ArticleOutputDTO> clientFindByCateTag(ArticleCateTagInputDTO articleCateTagInputDTO) {
        Article article = articleCateTagInputDTO.convertToEntity();
        Iterator<Article> iterator;
        if (articleCateTagInputDTO.getEncategory().equals("recommend")) {
            iterator = articleDAO.findUsing().iterator();
        } else {
            iterator = articleDAO.findUsingByEnCategoryEnTags(article).iterator();
        }
        List<ArticleOutputDTO> arrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO = articleOutputDTO.convertToDTO(iterator.next());
            arrayList.add(articleOutputDTO);
        }
        return arrayList;
    }

    @Override
    public List<ArticleOutputDTO> clientFindByRecord(RecordInputDTO recordInputDTO) {
        Record record = recordInputDTO.convertToRecord();
        Iterator<Article> iterator = articleDAO.findUsingByRecord(record).iterator();
        List<ArticleOutputDTO> arrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO = articleOutputDTO.convertToDTO(iterator.next());
            arrayList.add(articleOutputDTO);
        }
        return arrayList;
    }

    @Override
    public List<Article> controlFindAll() {
        return articleDAO.findAll();
    }

    @Override
    public List<Article> controlFindUsing() {
        return articleDAO.findUsing();
    }

    @Override
    public String controlSaveFile(MultipartFile file, String category, HttpServletRequest request) throws IOException {
        // TODO
        return null;
    }

    @Override
    public Message controlSaveArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            log.info("管理员[{}]尝试新增文章数据。", operator);
            Article article = articleInputDTO.convertToEntity();
            Integer result = articleDAO.saveArticle(article);
            if (result == null || result == 0) {
                log.info("管理员[{}]新增文章数据失败", operator);
                message.setCode(-1).setMsg("添加失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]新增文章数据成功，新增数据为：[{}]", operator, articleInputDTO);
                }
                log.info("管理员[{}]新增文章数据成功，新增数据为：[{}]，影响结果数：[{}]", operator, articleInputDTO, result);
                message.setCode(1).setMsg("添加成功!");
            }
        }
        return message;
    }

    @Override
    public Message controlDeleteArticle(ArticleStatusInputDTO articleStatusInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            log.info("管理员[{}]尝试修改文章数据状态。", operator);
            Article article = articleStatusInputDTO.convertToEntity();
            Integer result = articleDAO.deleteArticle(article);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改文章数据状态失败", operator);
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改文章数据状态成功，修改的文章数据为：enMedia=[{}], status=[{}]", operator, articleStatusInputDTO.getEnarticle(), articleStatusInputDTO.getStatus());
                }
                log.info("管理员[{}]修改文章数据状态成功，修改的文章数据为：enMedia=[{}], status=[{}]，影响结果数：[{}]", operator, articleStatusInputDTO.getEnarticle(), articleStatusInputDTO.getStatus(), result);
                message.setCode(1).setMsg("操作成功!");
            }
        }
        return message;
    }

    @Override
    public Message controlUpdateArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            log.info("管理员[{}]尝试修改文章数据。", operator);
            Article article = articleInputDTO.convertToEntity();
            Integer result = articleDAO.updateArticle(article);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改文章数据失败", operator);
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改文章数据成功，修改的文章数据为：[{}]", operator, articleInputDTO);
                }
                log.info("管理员[{}]修改文章数据成功，修改的文章数据为：[{}]，影响结果数：[{}]", operator, articleInputDTO, result);
                message.setCode(1).setMsg("操作成功!");
            }
        }
        return message;
    }
}
