package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Contact;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ContactStatusInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/2816:22
 */
@Data
public class ContactStatusInputDTO {
    private Integer id;

    private Integer status;

    /**
     * DTO对象转实体对象
     *
     * @return
     */
    public Contact convertToContact() {
        ContactStatusInputDTOConvert contactStatusInputDTOConvert = new ContactStatusInputDTOConvert();
        Contact convert = contactStatusInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param contact
     * @return
     */
    public ContactStatusInputDTO convertFor(Contact contact) {
        ContactStatusInputDTOConvert contactStatusInputDTOConvert = new ContactStatusInputDTOConvert();
        ContactStatusInputDTO convert = contactStatusInputDTOConvert.reverse().convert(contact);
        return convert;
    }

    /**
     * 转化类及方法
     */
    private static class ContactStatusInputDTOConvert extends Converter<ContactStatusInputDTO, Contact> {
        @Override
        protected Contact doForward(ContactStatusInputDTO contactStatusInputDTO) {
            Contact contact = new Contact();
            BeanUtils.copyProperties(contactStatusInputDTO, contact);
            return contact;
        }

        @Override
        protected ContactStatusInputDTO doBackward(Contact contact) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
