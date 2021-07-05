package com.whoai.blog.dto;

import com.whoai.blog.entity.Contact;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ContactOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/2816:20
 */
@Data
public class ContactOutputDTO {
    private Integer id;

    private String nickname;

    private String content;

    /**
     * DTO对象转为实体对象
     *
     * @return
     */
    public Contact convertToContact() {
        ContactOutputDTOConvert contactOutputDTOConvert = new ContactOutputDTOConvert();
        Contact convert = contactOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param contact
     * @return
     */
    public ContactOutputDTO convertFor(Contact contact) {
        ContactOutputDTOConvert contactOutputDTOConvert = new ContactOutputDTOConvert();
        ContactOutputDTO convert = contactOutputDTOConvert.reverse().convert(contact);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class ContactOutputDTOConvert extends Converter<ContactOutputDTO, Contact> {

        @Override
        protected Contact doForward(ContactOutputDTO contactOutputDTO) {
            throw new AssertionError("不支持正向转化方法");
        }

        @Override
        protected ContactOutputDTO doBackward(Contact contact) {
            ContactOutputDTO contactOutputDTO = new ContactOutputDTO();
            BeanUtils.copyProperties(contact, contactOutputDTO);
            return contactOutputDTO;
        }
    }

}
