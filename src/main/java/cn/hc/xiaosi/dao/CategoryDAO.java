package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * CategoryDAO继承基类
 */
@Repository
public interface CategoryDAO extends MyBatisBaseDao<Category, String> {

    /**
     * 管理端获取所有分类
     *
     * @return
     */
    ArrayList<Category> findAll();

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    Integer saveCategory(@Param("category") Category category);

    /**
     * 启用，禁用分类
     *
     * @param category
     * @return
     */
    Integer deleteCategory(@Param("category") Category category);

    /**
     * 编辑分类信息
     *
     * @param category
     * @return
     */
    Integer updateCategory(@Param("category") Category category);

    /**
     * 客户端通过导航查询其下的分类
     *
     * @param category
     * @return
     */
    ArrayList<Category> findByEnNav(@Param("category") Category category);

}