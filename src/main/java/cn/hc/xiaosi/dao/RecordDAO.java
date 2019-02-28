package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Record;
import org.springframework.stereotype.Repository;

/**
 * RecordDAO继承基类
 */
@Repository
public interface RecordDAO extends MyBatisBaseDao<Record, Integer> {
}