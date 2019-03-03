package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Category;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName CategoryStatusInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/312:59
 */
@Data
public class CategoryStatusInputDTO {
    /**
     * 类别标识
     */
    private String encategory;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    /**
     * DTO对象转实体对象
     *
     * @return
     */
    public Category convertToCategory() {
        CategoryStatusInputDTOConvert categoryStatusInputDTOConvert = new CategoryStatusInputDTOConvert();
        Category convert = categoryStatusInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param category
     * @return
     */
    public CategoryStatusInputDTO convertFor(Category category) {
        CategoryStatusInputDTOConvert categoryStatusInputDTOConvert = new CategoryStatusInputDTOConvert();
        CategoryStatusInputDTO convert = categoryStatusInputDTOConvert.reverse().convert(category);
        return convert;
    }

    /**
     * 转化类及方法
     */
    private static class CategoryStatusInputDTOConvert extends Converter<CategoryStatusInputDTO, Category> {
        @Override
        protected Category doForward(CategoryStatusInputDTO categoryStatusInputDTO) {
            Category category = new Category();
            BeanUtils.copyProperties(categoryStatusInputDTO, category);
            return category;
        }

        @Override
        protected CategoryStatusInputDTO doBackward(Category category) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
