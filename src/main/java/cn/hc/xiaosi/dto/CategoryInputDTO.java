package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Category;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName CategoryInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/312:56
 */
@Data
public class CategoryInputDTO {
    /**
     * 类别标识
     */
    private String encategory;

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
    private Integer catelevel;

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
        CategoryInputDTOConvert categoryInputDTOConvert = new CategoryInputDTOConvert();
        Category convert = categoryInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param category
     * @return
     */
    public CategoryInputDTO convertFor(Category category) {
        CategoryInputDTOConvert categoryInputDTOConvert = new CategoryInputDTOConvert();
        CategoryInputDTO convert = categoryInputDTOConvert.reverse().convert(category);
        return convert;
    }

    /**
     * 对象转化类及方法
     */
    private static class CategoryInputDTOConvert extends Converter<CategoryInputDTO, Category> {
        @Override
        protected Category doForward(CategoryInputDTO categoryInputDTO) {
            Category category = new Category();
            BeanUtils.copyProperties(categoryInputDTO, category);
            return category;
        }

        @Override
        protected CategoryInputDTO doBackward(Category category) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
