package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Category;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName CategoryNavbarInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/220:14
 */
@Data
public class CategoryNavbarInputDTO {
    /**
     * 导航标识
     */
    private String ennav;

    /**
     * DTO对象转为实体对象
     *
     * @return
     */
    public Category convertToCategory() {
        CategoryNavbarInputDTOConvert categoryNavbarInputDTOConvert = new CategoryNavbarInputDTOConvert();
        Category convert = categoryNavbarInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param category
     * @return
     */
    public CategoryNavbarInputDTO convertFor(Category category) {
        CategoryNavbarInputDTOConvert categoryNavbarInputDTOConvert = new CategoryNavbarInputDTOConvert();
        CategoryNavbarInputDTO convert = categoryNavbarInputDTOConvert.reverse().convert(category);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class CategoryNavbarInputDTOConvert extends Converter<CategoryNavbarInputDTO, Category> {
        @Override
        protected Category doForward(CategoryNavbarInputDTO categoryNavbarInputDTO) {
            Category category = new Category();
            BeanUtils.copyProperties(categoryNavbarInputDTO, category);
            return category;
        }

        @Override
        protected CategoryNavbarInputDTO doBackward(Category category) {
            throw new AssertionError("不支持逆向转化发方法");
        }
    }

}
