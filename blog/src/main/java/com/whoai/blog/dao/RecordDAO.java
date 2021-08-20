package com.whoai.blog.dao;

import com.whoai.blog.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordDAO {

    /**
     * 获取所有记录
     */
    List<Record> findAll();

    /**
     * 新增记录
     *
     * @param record 搜索记录
     */
    Integer saveRecord(@Param("record") Record record);

    /**
     * 查询已有记录
     * 实现搜索智能提示搜索频率最大的关键字
     */
    List<Object> autoComplete();


}