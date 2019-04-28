package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.ContactInputDTO;
import cn.hc.xiaosi.dto.ContactOutputDTO;
import cn.hc.xiaosi.dto.ContactStatusInputDTO;
import cn.hc.xiaosi.entity.Contact;
import cn.hc.xiaosi.service.ContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName ContactController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/2817:03
 */
@RestController
@RequestMapping(value = "contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端输出")
    public ArrayList<Contact> categories() {
        return contactService.controlFindAll();
    }


    @RequestMapping(value = "delete")
    @ApiOperation(value = "建议择优")
    public Message deleteCategory(@RequestBody ContactStatusInputDTO contactStatusInputDTO, HttpServletRequest request) {
        return contactService.controlDeleteContact(contactStatusInputDTO, request);
    }

    /**
     * 客户端
     *
     * @return
     */
    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端输出")
    public ArrayList<ContactOutputDTO> categoryOutputDTOS() {
        return contactService.clientFindAllUsing();
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增建议")
    public Message saveCategory(@RequestBody ContactInputDTO contactInputDTO) {
        return contactService.clientSaveContact(contactInputDTO);
    }

}
