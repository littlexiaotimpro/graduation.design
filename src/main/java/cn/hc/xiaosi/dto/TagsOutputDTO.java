package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Tags;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName TagsOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/318:35
 */
@Data
public class TagsOutputDTO {
    /**
     * 标签标识
     */
    private String entag;

    /**
     * 类别标识
     */
    private String encategory;

    /**
     * 名称
     */
    private String caption;

    /**
     * DTO对象转实体对象
     *
     * @return
     */
    public Tags convertToTags() {
        TagsOutputDTOConvert tagsOutputDTOConvert = new TagsOutputDTOConvert();
        Tags convert = tagsOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param tags
     * @return
     */
    public TagsOutputDTO convertFor(Tags tags) {
        TagsOutputDTOConvert tagsOutputDTOConvert = new TagsOutputDTOConvert();
        TagsOutputDTO convert = tagsOutputDTOConvert.reverse().convert(tags);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class TagsOutputDTOConvert extends Converter<TagsOutputDTO, Tags> {
        @Override
        protected Tags doForward(TagsOutputDTO tagsOutputDTO) {
            throw new AssertionError("不支持正向转换方法");
        }

        @Override
        protected TagsOutputDTO doBackward(Tags tags) {
            TagsOutputDTO tagsOutputDTO = new TagsOutputDTO();
            BeanUtils.copyProperties(tags, tagsOutputDTO);
            return tagsOutputDTO;
        }
    }

}
