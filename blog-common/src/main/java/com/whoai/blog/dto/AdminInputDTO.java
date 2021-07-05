package com.whoai.blog.dto;

import com.whoai.blog.entity.Admin;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName AdminInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/5/518:56
 */
@Data
public class AdminInputDTO {
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

    /**
     * 正向转化
     *
     * @return
     */
    public Admin convertToAdmin() {
        AdminInputDTOConvert adminInputDTOConvert = new AdminInputDTOConvert();
        Admin convert = adminInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param admin
     * @return
     */
    public AdminInputDTO convertFor(Admin admin) {
        AdminInputDTOConvert adminInputDTOConvert = new AdminInputDTOConvert();
        AdminInputDTO convert = adminInputDTOConvert.reverse().convert(admin);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class AdminInputDTOConvert extends Converter<AdminInputDTO, Admin> {
        @Override
        protected Admin doForward(AdminInputDTO adminInputDTO) {
            Admin admin = new Admin();
            BeanUtils.copyProperties(adminInputDTO, admin);
            return admin;
        }

        @Override
        protected AdminInputDTO doBackward(Admin admin) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
