package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * CategoryDAO继承基类
 */
@Repository
public interface CategoryDAO extends MyBatisBaseDao<Category, String> {
}