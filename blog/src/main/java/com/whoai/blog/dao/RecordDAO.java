package com.whoai.blog.dao;

import com.whoai.blog.entity.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * RecordDAO继承基类
 */
@Repository
public interface RecordDAO extends MyBatisBaseDao<Record, Integer> {

    /**
     * 管理端获取所有记录
     *
     * @return
     */
    ArrayList<Record> findAll();

    /**
     * 新增记录
     *
     * @param record
     * @return
     */
    Integer saveRecord(@Param("record") Record record);

    /**
     * 查询已有记录
     * 实现搜索智能提示搜索频率最大的关键字
     *
     * @return
     */
    ArrayList autoCompleteFind();


}