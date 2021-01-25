package com.whoai.blog.dto;

import com.whoai.blog.entity.Admin;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName AdminDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/2815:31
 */
@Data
public class AdminDTO {
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * DTO对象转为实体对象
     *
     * @return
     */
    public Admin convertToAdmin() {
        AdminDTOConvert adminDTOConvert = new AdminDTOConvert();
        Admin convert = adminDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param admin
     * @return
     */
    public AdminDTO convertFor(Admin admin) {
        AdminDTOConvert adminDTOConvert = new AdminDTOConvert();
        AdminDTO convert = adminDTOConvert.reverse().convert(admin);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class AdminDTOConvert extends Converter<AdminDTO, Admin> {
        @Override
        protected Admin doForward(AdminDTO adminDTO) {
            Admin admin = new Admin();
            BeanUtils.copyProperties(adminDTO, admin);
            return admin;
        }

        @Override
        protected AdminDTO doBackward(Admin admin) {
            throw new AssertionError("不支持逆向转化方法!");
        }
    }

}
