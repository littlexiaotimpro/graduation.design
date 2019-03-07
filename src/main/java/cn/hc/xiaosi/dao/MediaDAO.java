package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Media;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * MediaDAO继承基类
 */
@Repository
public interface MediaDAO extends MyBatisBaseDao<Media, String> {

    /**
     * 管理端输出
     *
     * @return
     */
    ArrayList<Media> findAll();

    /**
     * 新增媒体
     *
     * @param media
     * @return
     */
    Integer saveMedia(@Param("media") Media media);

    /**
     * 启用，禁用媒体
     *
     * @param media
     * @return
     */
    Integer deleteMedia(@Param("media") Media media);

    /**
     * 编辑媒体信息
     *
     * @param media
     * @return
     */
    Integer updateMedia(@Param("media") Media media);

    /**
     * 客户端通过分类，标签查询
     *
     * @param media
     * @return
     */
    ArrayList<Media> findUsingByEnCateTag(@Param("media") Media media);

}