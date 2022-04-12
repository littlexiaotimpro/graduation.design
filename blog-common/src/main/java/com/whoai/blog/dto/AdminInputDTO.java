package com.whoai.blog.dto;

import com.whoai.blog.constant.Role;
import com.whoai.blog.constant.Status;
import com.whoai.blog.entity.Admin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminInputDTO extends AbstractInputDTO<AdminInputDTO, Admin> {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private Role role;

    /**
     * 权限
     */
    private String permission;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Status status;

    @Override
    public Admin convertToEntity() {
        DTOConvert<AdminInputDTO, Admin> converter = converter();
        converter.setEntity(new Admin());
        return converter.convert(this);
    }
}
