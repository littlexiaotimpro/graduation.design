package com.whoai.blog.dto;

import com.whoai.blog.entity.Tags;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName TagsStatusInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/417:00
 */
@Data
public class TagsStatusInputDTO {
    /**
     * 标签标识
     */
    private String entag;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    /**
     * DTO对象转实体对象
     *
     * @return
     */
    public Tags convertToTags() {
        TagsStatusInputDTOConvert tagsStatusInputDTOConvert = new TagsStatusInputDTOConvert();
        Tags convert = tagsStatusInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param tags
     * @return
     */
    public TagsStatusInputDTO convertFor(Tags tags) {
        TagsStatusInputDTOConvert tagsStatusInputDTOConvert = new TagsStatusInputDTOConvert();
        TagsStatusInputDTO convert = tagsStatusInputDTOConvert.reverse().convert(tags);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class TagsStatusInputDTOConvert extends Converter<TagsStatusInputDTO, Tags> {
        @Override
        protected Tags doForward(TagsStatusInputDTO tagsStatusInputDTO) {
            Tags tags = new Tags();
            BeanUtils.copyProperties(tagsStatusInputDTO, tags);
            return tags;
        }

        @Override
        protected TagsStatusInputDTO doBackward(Tags tags) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
