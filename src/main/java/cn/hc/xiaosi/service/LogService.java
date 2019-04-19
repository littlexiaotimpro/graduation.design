package cn.hc.xiaosi.service;

import cn.hc.xiaosi.entity.LogBean;

import java.util.ArrayList;

/**
 * @ClassName LogService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/1917:01
 */
public interface LogService {

    ArrayList<LogBean> controlFindAll();

    void saveLog(LogBean logBean);

}
