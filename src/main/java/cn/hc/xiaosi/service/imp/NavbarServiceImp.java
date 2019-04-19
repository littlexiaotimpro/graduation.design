package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.NavbarDAO;
import cn.hc.xiaosi.dto.NavbarInputDTO;
import cn.hc.xiaosi.dto.NavbarOutputDTO;
import cn.hc.xiaosi.dto.NavbarStatusInputDTO;
import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.entity.Navbar;
import cn.hc.xiaosi.service.LogService;
import cn.hc.xiaosi.service.NavbarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Message controlSaveNavbar(NavbarInputDTO navbarInputDTO) {
        boolean debug = log.isDebugEnabled();
        LogBean logBean = new LogBean();
        log.info("管理员[{}]尝试新增导航数据。", "admin");
        Navbar navbar = navbarInputDTO.convertToNavbar();
        Integer result = navbarDAO.saveNavbar(navbar);
        Message message = new Message();
        if (result == null || result == 0) {
            log.info("管理员[{}]新增导航数据失败", "admin");
            logBean.setOperation("新增").setOperator("admin").setContent("管理员admin新增导航数据失败");
            message.setCode(-1).setMsg("添加失败!");
        } else {
            if (debug) {
                log.debug("管理员[{}]新增导航数据成功，新增数据为：[{}]", "admin", navbarInputDTO);
            }
            log.info("管理员[{}]新增导航数据成功，新增数据为：[{}]，影响结果数：[{}]", "admin", navbarInputDTO, result);
            logBean.setOperation("新增").setOperator("admin").setContent("管理员admin新增导航数据成功，新增数据为：" + navbarInputDTO + "");
            message.setCode(1).setMsg("添加成功!");
        }
        logService.saveLog(logBean);
        return message;
    }

    @Override
    public Message controlDeleteNavbar(NavbarStatusInputDTO navbarStatusInputDTO) {
        Navbar navbar = navbarStatusInputDTO.convertToNavbar();
        Integer result = navbarDAO.deleteNavbar(navbar);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }

    @Override
    public Message controlUpdateNavbar(NavbarInputDTO navbarInputDTO) {
        Navbar navbar = navbarInputDTO.convertToNavbar();
        Integer result = navbarDAO.updateNavbar(navbar);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }
}
