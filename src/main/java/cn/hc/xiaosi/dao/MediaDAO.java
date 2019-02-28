package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Media;
import org.springframework.stereotype.Repository;

/**
 * MediaDAO继承基类
 */
@Repository
public interface MediaDAO extends MyBatisBaseDao<Media, String> {
}