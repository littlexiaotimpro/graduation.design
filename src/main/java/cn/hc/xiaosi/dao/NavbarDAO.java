package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Navbar;
import org.springframework.stereotype.Repository;

/**
 * NavbarDAO继承基类
 */
@Repository
public interface NavbarDAO extends MyBatisBaseDao<Navbar, String> {
}