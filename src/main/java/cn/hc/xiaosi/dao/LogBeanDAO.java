package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.LogBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * LogBeanDAO继承基类
 */
@Repository
public interface LogBeanDAO extends MyBatisBaseDao<LogBean, Integer> {

    /**
     * 管理端获取所有日志
     *
     * @return
     */
    ArrayList<LogBean> findAll();

    /**
     * 新增日志
     *
     * @param logBean
     * @return
     */
    Integer saveLog(@Param("logBean") LogBean logBean);

}