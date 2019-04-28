package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.ContactInputDTO;
import cn.hc.xiaosi.dto.ContactOutputDTO;
import cn.hc.xiaosi.dto.ContactStatusInputDTO;
import cn.hc.xiaosi.entity.Contact;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName ContactService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/2816:52
 */
public interface ContactService {

    /**
     * 管理端
     *
     * @return
     */
    ArrayList<Contact> controlFindAll();

    Message controlDeleteContact(ContactStatusInputDTO contactStatusInputDTO, HttpServletRequest request);

    /**
     * 客户端
     *
     * @return
     */
    ArrayList<ContactOutputDTO> clientFindAllUsing();

    Message clientSaveContact(ContactInputDTO contactInputDTO);
}
