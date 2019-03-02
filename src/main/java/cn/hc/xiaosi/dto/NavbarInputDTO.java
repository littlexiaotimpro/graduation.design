package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Navbar;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName NavbarInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/116:55
 */
@Data
public class NavbarInputDTO {
    /**
     * 导航标识
     */
    private String ennav;

    /**
     * 名称
     */
    private String caption;

    /**
     * 序号
     */
    private Integer navlevel;

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
        NavbarInputDTOConvert navbarInputDTOConvert = new NavbarInputDTOConvert();
        Navbar convert = navbarInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param navbar
     * @return
     */
    public NavbarInputDTO convertFor(Navbar navbar) {
        NavbarInputDTOConvert navbarInputDTOConvert = new NavbarInputDTOConvert();
        NavbarInputDTO convert = navbarInputDTOConvert.reverse().convert(navbar);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class NavbarInputDTOConvert extends Converter<NavbarInputDTO, Navbar> {
        @Override
        protected Navbar doForward(NavbarInputDTO navbarInputDTO) {
//            throw new AssertionError("不支持正向转化方法!");
            Navbar navbar = new Navbar();
            BeanUtils.copyProperties(navbarInputDTO, navbar);
            return navbar;
        }

        @Override
        protected NavbarInputDTO doBackward(Navbar navbar) {
            throw new AssertionError("不支持逆向转化方法!");
//            NavbarInputDTO navbarInputDTO = new NavbarInputDTO();
//            BeanUtils.copyProperties(navbar, navbarInputDTO);
//            return navbarInputDTO;
        }
    }

}
