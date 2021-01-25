package com.whoai.blog.service.imp;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dao.NavbarDAO;
import com.whoai.blog.dto.NavbarInputDTO;
import com.whoai.blog.dto.NavbarOutputDTO;
import com.whoai.blog.dto.NavbarStatusInputDTO;
import com.whoai.blog.entity.LogBean;
import com.whoai.blog.entity.Navbar;
import com.whoai.blog.service.LogService;
import com.whoai.blog.service.NavbarService;
import com.whoai.blog.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName NavbarServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/114:07
 */
@Service
@Slf4j
public class NavbarServiceImp implements NavbarService {

    @Autowired
    private NavbarDAO navbarDAO;

    @Autowired
    private LogService logService;

    @Override
    public ArrayList<NavbarOutputDTO> clientFindAllUsing() {
        ArrayList<NavbarOutputDTO> arrayList = new ArrayList<NavbarOutputDTO>();
        Iterator iterator = navbarDAO.findAllUsing().iterator();
        while (iterator.hasNext()) {
            NavbarOutputDTO navbarOutputDTO = new NavbarOutputDTO();
            navbarOutputDTO = navbarOutputDTO.convertFor((Navbar) iterator.next());
            arrayList.add(navbarOutputDTO);
        }
        return arrayList;
    }

    @Override
    public ArrayList<Navbar> controlFindAll() {
        return navbarDAO.findAll();
    }

    @Override
    public ArrayList<NavbarOutputDTO> controlFindAllCaption() {
        ArrayList<NavbarOutputDTO> arrayList = new ArrayList<NavbarOutputDTO>();
        Iterator iterator = navbarDAO.findAll().iterator();
        while (iterator.hasNext()) {
            NavbarOutputDTO navbarOutputDTO = new NavbarOutputDTO();
            navbarOutputDTO = navbarOutputDTO.convertFor((Navbar) iterator.next());
            arrayList.add(navbarOutputDTO);
        }
        return arrayList;
    }


    @Override
    public Message controlSaveNavbar(NavbarInputDTO navbarInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试新增导航数据。", operator);
            Navbar navbar = navbarInputDTO.convertToNavbar();
            Integer result = navbarDAO.saveNavbar(navbar);
            if (result == null || result == 0) {
                log.info("管理员[{}]新增导航数据失败", operator);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增导航数据失败");
                message.setCode(-1).setMsg("添加失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]新增导航数据成功，新增数据为：[{}]", operator, navbarInputDTO);
                }
                log.info("管理员[{}]新增导航数据成功，新增数据为：[{}]，影响结果数：[{}]", operator, navbarInputDTO, result);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增导航数据成功，新增数据为：" + navbarInputDTO);
                message.setCode(1).setMsg("添加成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlDeleteNavbar(NavbarStatusInputDTO navbarStatusInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改导航数据状态。", operator);
            Navbar navbar = navbarStatusInputDTO.convertToNavbar();
            Integer result = navbarDAO.deleteNavbar(navbar);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改导航数据状态失败", operator);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改导航数据状态失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改导航数据状态成功，修改的导航数据为：enNav=[{}], status=[{}]", operator, navbarStatusInputDTO.getEnnav(), navbarStatusInputDTO.getStatus());
                }
                log.info("管理员[{}]修改导航数据状态成功，修改的导航数据为：enNav=[{}], status=[{}]，影响结果数：[{}]", operator, navbarStatusInputDTO.getEnnav(), navbarStatusInputDTO.getStatus(), result);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改导航数据状态成功，修改的导航数据为：" + navbarStatusInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlUpdateNavbar(NavbarInputDTO navbarInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改导航数据。", operator);
            Navbar navbar = navbarInputDTO.convertToNavbar();
            Integer result = navbarDAO.updateNavbar(navbar);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改导航数据失败", operator);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改导航数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改导航数据成功，修改的导航数据为：[{}]", operator, navbarInputDTO);
                }
                log.info("管理员[{}]修改导航数据成功，修改的导航数据为：[{}]，影响结果数：[{}]", operator, navbarInputDTO, result);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改导航数据成功，修改的导航数据为：" + navbarInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }
}
