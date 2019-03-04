package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Tags;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName TagsCategoryInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/318:47
 */
@Data
public class TagsCategoryInputDTO {
    /**
     * 类别标识
     */
    private String encategory;

    /**
     * DTO对象转实体对象
     *
     * @return
     */
    public Tags convertToTags() {
        TagsCategoryInputDTOConvert tagsCategoryInputDTOConvert = new TagsCategoryInputDTOConvert();
        Tags convert = tagsCategoryInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param tags
     * @return
     */
    public TagsCategoryInputDTO convertFor(Tags tags) {
        TagsCategoryInputDTOConvert tagsCategoryInputDTOConvert = new TagsCategoryInputDTOConvert();
        TagsCategoryInputDTO convert = tagsCategoryInputDTOConvert.reverse().convert(tags);
        return convert;
    }


    /**
     * 转换类及方法
     */
    private static class TagsCategoryInputDTOConvert extends Converter<TagsCategoryInputDTO, Tags> {
        @Override
        protected Tags doForward(TagsCategoryInputDTO tagsCategoryInputDTO) {
            Tags tags = new Tags();
            BeanUtils.copyProperties(tagsCategoryInputDTO, tags);
            return tags;
        }

        @Override
        protected TagsCategoryInputDTO doBackward(Tags tags) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
