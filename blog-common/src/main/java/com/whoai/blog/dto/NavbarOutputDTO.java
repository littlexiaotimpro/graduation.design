package com.whoai.blog.dto;

import com.whoai.blog.entity.Navbar;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName NavbarOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/114:13
 */
@Data
public class NavbarOutputDTO {
    /**
     * 导航标识
     */
    private String ennav;

    /**
     * 名称
     */
    private String caption;

    /**
     * DTO对象转为实体对象
     *
     * @return
     */
    public Navbar convertToNavbar() {
        NavbarOutputDTOConvert navbarOutputDTOConvert = new NavbarOutputDTOConvert();
        Navbar convert = navbarOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param navbar
     * @return
     */
    public NavbarOutputDTO convertFor(Navbar navbar) {
        NavbarOutputDTOConvert navbarOutputDTOConvert = new NavbarOutputDTOConvert();
        NavbarOutputDTO convert = navbarOutputDTOConvert.reverse().convert(navbar);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class NavbarOutputDTOConvert extends Converter<NavbarOutputDTO, Navbar> {
        @Override
        protected Navbar doForward(NavbarOutputDTO navbarOutputDTO) {
            throw new AssertionError("不支持正向转化方法!");
//            Navbar navbar = new Navbar();
//            BeanUtils.copyProperties(navbarOutputDTO, navbar);
//            return navbar;
        }

        @Override
        protected NavbarOutputDTO doBackward(Navbar navbar) {
            NavbarOutputDTO navbarOutputDTO = new NavbarOutputDTO();
            BeanUtils.copyProperties(navbar, navbarOutputDTO);
            return navbarOutputDTO;
        }
    }

}
