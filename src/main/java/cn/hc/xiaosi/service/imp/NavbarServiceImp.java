package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.NavbarDAO;
import cn.hc.xiaosi.dto.NavbarInputDTO;
import cn.hc.xiaosi.dto.NavbarOutputDTO;
import cn.hc.xiaosi.dto.NavbarStatusInputDTO;
import cn.hc.xiaosi.entity.Navbar;
import cn.hc.xiaosi.service.NavbarService;
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
public class NavbarServiceImp implements NavbarService {

    @Autowired
    private NavbarDAO navbarDAO;

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
        Navbar navbar = navbarInputDTO.convertToNavbar();
        Integer result = navbarDAO.saveNavbar(navbar);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
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
