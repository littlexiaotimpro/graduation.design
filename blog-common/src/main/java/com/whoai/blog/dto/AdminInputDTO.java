package com.whoai.blog.dto;

import com.whoai.blog.entity.Admin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminInputDTO extends AbstractDTO<AdminInputDTO, Admin> {
    /**
     * 管理员编号
     */
    private String adminid;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限
     */
    private Integer permission;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;


    @Override
    public AdminInputDTO convertToDTO(Admin admin) {
        throw new AssertionError("不支持逆向转换！");
    }

    @Override
    public Admin convertToEntity() {
        DTOConvert<AdminInputDTO, Admin> converter = converter();
        converter.setEntity(new Admin());
        return converter.convert(this);
    }
}
