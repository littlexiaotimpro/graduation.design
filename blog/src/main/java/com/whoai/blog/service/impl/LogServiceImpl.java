package com.whoai.blog.service.impl;

import com.whoai.blog.dao.LogBeanDAO;
import com.whoai.blog.entity.LogBean;
import com.whoai.blog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @ClassName LogServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/1917:04
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogBeanDAO logBeanDAO;

    @Override
    public ArrayList<LogBean> controlFindAll() {
        log.info("管理端打印所有日志信息");
        return logBeanDAO.findAll();
    }

    @Override
    public void saveLog(LogBean logBean) {
        Integer result = logBeanDAO.saveLog(logBean);
        if (result == null || result == 0) {
            log.info("日志生成失败");
        } else {
            if (log.isDebugEnabled()) {
                log.debug("日志生成成功，影响结果数: result=[{}]", result);
            }
            log.info("日志生成成功");
        }
    }
}
