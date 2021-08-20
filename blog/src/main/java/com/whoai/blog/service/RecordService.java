package com.whoai.blog.service;

import com.whoai.blog.dto.RecordInputDTO;
import com.whoai.blog.entity.Record;

import java.util.List;

/**
 * RecordService
 *
 * @author XiaoSi
 * @date 2019/3/5
 */
public interface RecordService {

    /**
     * 获取所有记录
     */
    List<Record> findAll();

    /**
     * 新增搜索记录
     *
     * @param recordInputDTO 搜索记录
     */
    Integer saveRecord(RecordInputDTO recordInputDTO);

    /**
     * 查询已有搜索记录
     * 按照搜索程度自动给推荐最好的结果
     */
    List<Object> autoComplete(RecordInputDTO recordInputDTO);

}
