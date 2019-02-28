package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Music;
import org.springframework.stereotype.Repository;

/**
 * MusicDAO继承基类
 */
@Repository
public interface MusicDAO extends MyBatisBaseDao<Music, String> {
}