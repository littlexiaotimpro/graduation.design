package com.whoai.blog.dto;

import com.whoai.blog.entity.Admin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDTO extends AbstractInputDTO<AdminDTO, Admin> {
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    @Override
    public Admin convertToEntity() {
        DTOConvert<AdminDTO, Admin> converter = converter();
        converter.setEntity(new Admin());
        return converter.convert(this);
    }

}
