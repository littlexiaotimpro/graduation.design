package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.ArticleDAO;
import cn.hc.xiaosi.dto.*;
import cn.hc.xiaosi.entity.Article;
import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.entity.Record;
import cn.hc.xiaosi.service.ArticleService;
import cn.hc.xiaosi.service.LogService;
import cn.hc.xiaosi.utils.JWTUtil;
import cn.hc.xiaosi.utils.MDUtil;
import cn.hc.xiaosi.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName ArticleServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/515:51
 */
@Service
@Slf4j
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private LogService logService;

    @Override
    public ArrayList<ArticleOutputDTO> clientFindAll() {
        ArrayList<ArticleOutputDTO> arrayList = new ArrayList<ArticleOutputDTO>();
        Iterator iterator = articleDAO.findAllUsing().iterator();
        while (iterator.hasNext()) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO = articleOutputDTO.convertFor((Article) iterator.next());
            arrayList.add(articleOutputDTO);
        }
        return arrayList;
    }


    @Override
    public String clientFindByEnArticle(ArticlePrimaryKeyInputDTO articlePrimaryKeyInputDTO) {
        Article article = articlePrimaryKeyInputDTO.convertToArticle();
        Article articleHtml = articleDAO.findByEnArticle(article);
        String html = null;
        try {
            html = MDUtil.changeMDToHtml(articleHtml.getFileurl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }

    @Override
    public ArrayList<ArticleOutputDTO> clientFindByCateTag(ArticleCateTagInputDTO articleCateTagInputDTO) {
        Article article = articleCateTagInputDTO.convertToArticle();
        Iterator iterator;
        if (articleCateTagInputDTO.getEncategory().equals("recommend")) {
            iterator = articleDAO.findAllUsing().iterator();
        } else {
            iterator = articleDAO.findUsingByEnCategoryEnTags(article).iterator();
        }
        ArrayList<ArticleOutputDTO> arrayList = new ArrayList<ArticleOutputDTO>();
        while (iterator.hasNext()) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO = articleOutputDTO.convertFor((Article) iterator.next());
            arrayList.add(articleOutputDTO);
        }
        return arrayList;
    }

    @Override
    public ArrayList<ArticleOutputDTO> clientFindByRecord(RecordInputDTO recordInputDTO) {
        Record record = recordInputDTO.convertToRecord();
        Iterator iterator = articleDAO.findUsingByRecord(record).iterator();
        ArrayList<ArticleOutputDTO> arrayList = new ArrayList<ArticleOutputDTO>();
        while (iterator.hasNext()) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO = articleOutputDTO.convertFor((Article) iterator.next());
            arrayList.add(articleOutputDTO);
        }
        return arrayList;
    }

    @Override
    public ArrayList<Article> controlFindAll() {
        return articleDAO.findAll();
    }

    @Override
    public ArrayList<ArticleControlOutputDTO> controlFindUsing() {
        return articleDAO.findUsing();
    }

    @Override
    public String controlSaveFile(MultipartFile file, String category, HttpServletRequest request) throws IOException {
        return UploadUtil.getFileUrl(file, category, logService, request);
    }

    @Override
    public Message controlSaveArticle(ArticleInputDTO articleInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试新增文章数据。", operator);
            Article article = articleInputDTO.convertToArticle();
            Integer result = articleDAO.saveArticle(article);
            if (result == null || result == 0) {
                log.info("管理员[{}]新增文章数据失败", operator);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增文章数据失败");
                message.setCode(-1).setMsg("添加失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]新增文章数据成功，新增数据为：[{}]", operator, articleInputDTO);
                }
                log.info("管理员[{}]新增文章数据成功，新增数据为：[{}]，影响结果数：[{}]", operator, articleInputDTO, result);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增文章数据成功，新增数据为：" + articleInputDTO);
                message.setCode(1).setMsg("添加成功!");
            }
            logService.saveLog(logBean);
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
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改文章数据状态。", operator);
            Article article = articleStatusInputDTO.convertToArticle();
            Integer result = articleDAO.deleteArticle(article);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改文章数据状态失败", operator);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改文章数据状态失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改文章数据状态成功，修改的文章数据为：enMedia=[{}], status=[{}]", operator, articleStatusInputDTO.getEnarticle(), articleStatusInputDTO.getStatus());
                }
                log.info("管理员[{}]修改文章数据状态成功，修改的文章数据为：enMedia=[{}], status=[{}]，影响结果数：[{}]", operator, articleStatusInputDTO.getEnarticle(), articleStatusInputDTO.getStatus(), result);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改文章数据状态成功，修改的文章数据为：" + articleStatusInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
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
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改文章数据。", operator);
            Article article = articleInputDTO.convertToArticle();
            Integer result = articleDAO.updateArticle(article);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改文章数据失败", operator);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改文章数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改文章数据成功，修改的文章数据为：[{}]", operator, articleInputDTO);
                }
                log.info("管理员[{}]修改文章数据成功，修改的文章数据为：[{}]，影响结果数：[{}]", operator, articleInputDTO, result);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改文章数据成功，修改的文章数据为：" + articleInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }
}
