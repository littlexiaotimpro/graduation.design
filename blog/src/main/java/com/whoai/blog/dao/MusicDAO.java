package com.whoai.blog.dao;

import com.whoai.blog.entity.Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * MusicDAO继承基类
 */
@Repository
public interface MusicDAO extends MyBatisBaseDao<Music, String> {

    /**
     * 管理端输出
     *
     * @return
     */
    ArrayList<Music> findAll();

    /**
     * 新增音乐
     *
     * @param music
     * @return
     */
    Integer saveMusic(@Param("music") Music music);

    /**
     * 启用，禁用音乐
     *
     * @param music
     * @return
     */
    Integer deleteMusic(@Param("music") Music music);

    /**
     * 编辑音乐信息
     *
     * @param music
     * @return
     */
    Integer updateMusic(@Param("music") Music music);

    /**
     * 客户端根据分类，标签查询
     *
     * @param music
     * @return
     */
    ArrayList<Music> findUsingByEnCateTag(@Param("music") Music music);

}