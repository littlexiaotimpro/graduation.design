package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Article;
import org.springframework.stereotype.Repository;

/**
 * ArticleDAO继承基类
 */
@Repository
public interface ArticleDAO extends MyBatisBaseDao<Article, String> {
}