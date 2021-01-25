package com.whoai.blog.service;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.ContactInputDTO;
import com.whoai.blog.dto.ContactOutputDTO;
import com.whoai.blog.dto.ContactStatusInputDTO;
import com.whoai.blog.entity.Contact;

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

    int clientUserCount();
}
