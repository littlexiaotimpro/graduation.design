package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.ContactDAO;
import cn.hc.xiaosi.dto.ContactInputDTO;
import cn.hc.xiaosi.dto.ContactOutputDTO;
import cn.hc.xiaosi.dto.ContactStatusInputDTO;
import cn.hc.xiaosi.entity.Contact;
import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.service.ContactService;
import cn.hc.xiaosi.service.LogService;
import cn.hc.xiaosi.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName ContactServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/2816:52
 */
@Service
@Slf4j
public class ContactServiceImp implements ContactService {

    @Autowired
    private ContactDAO contactDAO;

    @Autowired
    private LogService logService;

    @Override
    public ArrayList<Contact> controlFindAll() {
        return contactDAO.findAll();
    }

    @Override
    public Message clientSaveContact(ContactInputDTO contactInputDTO) {
        Contact contact = contactInputDTO.convertToContact();
        Integer result = contactDAO.saveContact(contact);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
    }

    @Override
    public int clientUserCount() {
        Integer result = contactDAO.userCount();
        return result == null ? 0 : result;
    }

    @Override
    public Message controlDeleteContact(ContactStatusInputDTO contactStatusInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改建议数据状态。", operator);
            Contact contact = contactStatusInputDTO.convertToContact();
            Integer result = contactDAO.deleteContact(contact);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改建议数据状态失败", operator);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改建议数据状态失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改建议数据状态成功，修改的建议数据为：enMedia=[{}], status=[{}]", operator, contactStatusInputDTO.getId(), contactStatusInputDTO.getStatus());
                }
                log.info("管理员[{}]修改建议数据状态成功，修改的建议数据为：enMedia=[{}], status=[{}]，影响结果数：[{}]", operator, contactStatusInputDTO.getId(), contactStatusInputDTO.getStatus(), result);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改建议数据状态成功，修改的建议数据为：" + contactStatusInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public ArrayList<ContactOutputDTO> clientFindAllUsing() {
        return contactDAO.findAllUsing();
    }
}
