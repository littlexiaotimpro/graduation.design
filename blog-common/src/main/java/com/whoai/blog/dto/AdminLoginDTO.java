package com.whoai.blog.dto;

import com.whoai.blog.entity.Admin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminLoginDTO extends AbstractInputDTO<AdminLoginDTO, Admin> {
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
        DTOConvert<AdminLoginDTO, Admin> converter = converter();
        converter.setEntity(new Admin());
        return converter.convert(this);
    }

}
