package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * AdminDAO继承基类
 */
@Repository
public interface AdminDAO extends MyBatisBaseDao<Admin, String> {

    /**
     * 管理员登录验证
     *
     * @param admin
     * @return
     */
    Admin checkLogin(@Param("admin") Admin admin);

}