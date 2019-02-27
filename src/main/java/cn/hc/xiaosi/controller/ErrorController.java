package cn.hc.xiaosi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ErrorController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/2311:31
 */
@Controller
public class ErrorController {

    private static final String ERROR_PATH = "/404";

    @RequestMapping(value = ERROR_PATH)
    public String handleError() {
        return "/404";
    }

//    @Override
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }
}
