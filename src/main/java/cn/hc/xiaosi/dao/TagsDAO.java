package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Tags;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * TagsDAO继承基类
 */
@Repository
public interface TagsDAO extends MyBatisBaseDao<Tags, String> {

    /**
     * 管理端查询所有标签
     *
     * @return
     */
    ArrayList<Tags> findAll();

    /**
     * 新增标签
     *
     * @param tags
     * @return
     */
    Integer saveTags(@Param("tags") Tags tags);

    /**
     * 启用，禁用标签
     *
     * @param tags
     * @return
     */
    Integer deleteTags(@Param("tags") Tags tags);

    /**
     * 编辑标签信息
     *
     * @param tags
     * @return
     */
    Integer updateTags(@Param("tags") Tags tags);

    /**
     * 客户端通过分类查询其下的标签
     *
     * @param tags
     * @return
     */
    ArrayList<Tags> findByEnCategory(@Param("tags") Tags tags);

}