package com.whoai.blog.service.impl;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dao.ContactDAO;
import com.whoai.blog.dto.ContactInputDTO;
import com.whoai.blog.dto.ContactOutputDTO;
import com.whoai.blog.dto.ContactStatusInputDTO;
import com.whoai.blog.entity.Contact;
import com.whoai.blog.service.ContactService;
import com.whoai.blog.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactDAO contactDAO;

    public ContactServiceImpl(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

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
            log.info("管理员[{}]尝试修改建议数据状态。", operator);
            Contact contact = contactStatusInputDTO.convertToContact();
            Integer result = contactDAO.deleteContact(contact);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改建议数据状态失败", operator);
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改建议数据状态成功，修改的建议数据为：enMedia=[{}], status=[{}]", operator, contactStatusInputDTO.getId(), contactStatusInputDTO.getStatus());
                }
                log.info("管理员[{}]修改建议数据状态成功，修改的建议数据为：enMedia=[{}], status=[{}]，影响结果数：[{}]", operator, contactStatusInputDTO.getId(), contactStatusInputDTO.getStatus(), result);
                message.setCode(1).setMsg("操作成功!");
            }
        }
        return message;
    }

    @Override
    public ArrayList<ContactOutputDTO> clientFindAllUsing() {
        return contactDAO.findAllUsing();
    }
}
