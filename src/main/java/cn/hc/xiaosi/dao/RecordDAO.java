package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Record;
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
     * @return
     */
    ArrayList<Record> findAll();

    /**
     * 新增记录
     * @param record
     * @return
     */
    Integer saveRecord(@Param("record") Record record);



}