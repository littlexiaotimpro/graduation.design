package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.service.LogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @ClassName LogController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/1917:06
 */
@RestController
@RequestMapping(value = "log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端日志输出")
    public ArrayList<LogBean> categories() {
        return logService.controlFindAll();
    }

}
