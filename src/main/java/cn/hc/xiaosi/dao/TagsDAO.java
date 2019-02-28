package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Tags;
import org.springframework.stereotype.Repository;

/**
 * TagsDAO继承基类
 */
@Repository
public interface TagsDAO extends MyBatisBaseDao<Tags, String> {
}