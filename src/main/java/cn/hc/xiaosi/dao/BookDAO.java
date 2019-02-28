package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Book;
import org.springframework.stereotype.Repository;

/**
 * BookDAO继承基类
 */
@Repository
public interface BookDAO extends MyBatisBaseDao<Book, String> {
}