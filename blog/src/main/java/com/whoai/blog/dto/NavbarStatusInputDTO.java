package com.whoai.blog.dto;

import com.whoai.blog.entity.Navbar;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName NavbarStatusInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/211:57
 */
@Data
public class NavbarStatusInputDTO {
    /**
     * 导航标识
     */
    private String ennav;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    /**
     * DTO对象转为实体对象
     *
     * @return
     */
    public Navbar convertToNavbar() {
        NavbarStatusInputDTOConvert navbarStatusInputDTOConvert = new NavbarStatusInputDTOConvert();
        Navbar convert = navbarStatusInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param navbar
     * @return
     */
    public NavbarStatusInputDTO convertFor(Navbar navbar) {
        NavbarStatusInputDTOConvert navbarStatusInputDTOConvert = new NavbarStatusInputDTOConvert();
        NavbarStatusInputDTO convert = navbarStatusInputDTOConvert.reverse().convert(navbar);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class NavbarStatusInputDTOConvert extends Converter<NavbarStatusInputDTO, Navbar> {

        @Override
        protected Navbar doForward(NavbarStatusInputDTO navbarStatusInputDTO) {
            Navbar navbar = new Navbar();
            BeanUtils.copyProperties(navbarStatusInputDTO, navbar);
            return navbar;
        }

        @Override
        protected NavbarStatusInputDTO doBackward(Navbar navbar) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
