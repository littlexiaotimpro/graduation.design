package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Contact;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ContactInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/2816:10
 */
@Data
public class ContactInputDTO {

    private String nickname;

    private String email;

    private String title;

    private String content;

    /**
     * DTO对象转实体对象
     *
     * @return
     */
    public Contact convertToContact() {
        ContactInputDTOConvert contactInputDTOConvert = new ContactInputDTOConvert();
        Contact convert = contactInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param contact
     * @return
     */
    public ContactInputDTO convertFor(Contact contact) {
        ContactInputDTOConvert contactInputDTOConvert = new ContactInputDTOConvert();
        ContactInputDTO convert = contactInputDTOConvert.reverse().convert(contact);
        return convert;
    }

    /**
     * 对象转化类及方法
     */
    private static class ContactInputDTOConvert extends Converter<ContactInputDTO, Contact> {
        @Override
        protected Contact doForward(ContactInputDTO contactInputDTO) {
            Contact contact = new Contact();
            BeanUtils.copyProperties(contactInputDTO, contact);
            return contact;
        }

        @Override
        protected ContactInputDTO doBackward(Contact contact) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
