package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.ArticleDAO;
import cn.hc.xiaosi.dto.*;
import cn.hc.xiaosi.entity.Article;
import cn.hc.xiaosi.entity.Record;
import cn.hc.xiaosi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName ArticleServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/515:51
 */
@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

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
    public ArrayList<ArticleOutputDTO> clientFindByCateTag(ArticleCateTagInputDTO articleCateTagInputDTO) {
        Article article = articleCateTagInputDTO.convertToArticle();
        Iterator iterator = articleDAO.findUsingByEnCategoryEnTags(article).iterator();
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
    public Message controlSaveArticle(ArticleInputDTO articleInputDTO) {
        Article article = articleInputDTO.convertToArticle();
        Integer result = articleDAO.saveArticle(article);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
    }

    @Override
    public Message controlDeleteArticle(ArticleStatusInputDTO articleStatusInputDTO) {
        Article article = articleStatusInputDTO.convertToArticle();
        Integer result = articleDAO.deleteArticle(article);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }

    @Override
    public Message controlUpdateArticle(ArticleInputDTO articleInputDTO) {
        Article article = articleInputDTO.convertToArticle();
        Integer result = articleDAO.updateArticle(article);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }
}
