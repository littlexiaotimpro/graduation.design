package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Category;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName CategoryOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/219:34
 */
@Data
public class CategoryOutputDTO {
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
     * DTO对象转为实体对象
     *
     * @return
     */
    public Category convertToCategory() {
        CategoryOutputDTOConvert categoryOutputDTOConvert = new CategoryOutputDTOConvert();
        Category convert = categoryOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param category
     * @return
     */
    public CategoryOutputDTO convertFor(Category category) {
        CategoryOutputDTOConvert categoryOutputDTOConvert = new CategoryOutputDTOConvert();
        CategoryOutputDTO convert = categoryOutputDTOConvert.reverse().convert(category);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class CategoryOutputDTOConvert extends Converter<CategoryOutputDTO, Category> {

        @Override
        protected Category doForward(CategoryOutputDTO categoryOutputDTO) {
            throw new AssertionError("不支持正向转化方法");
        }

        @Override
        protected CategoryOutputDTO doBackward(Category category) {
            CategoryOutputDTO categoryOutputDTO = new CategoryOutputDTO();
            BeanUtils.copyProperties(category, categoryOutputDTO);
            return categoryOutputDTO;
        }
    }

}
