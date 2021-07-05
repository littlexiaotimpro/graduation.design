package com.whoai.blog.dto;

import com.whoai.blog.entity.Tags;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName TagsInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/417:00
 */
@Data
public class TagsInputDTO {
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
     * 序号
     */
    private Integer taglevel;

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
        TagsInputDTOConvert tagsInputDTOConvert = new TagsInputDTOConvert();
        Tags convert = tagsInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 实体对象转DTO对象
     *
     * @param tags
     * @return
     */
    public TagsInputDTO convertFor(Tags tags) {
        TagsInputDTOConvert tagsInputDTOConvert = new TagsInputDTOConvert();
        TagsInputDTO convert = tagsInputDTOConvert.reverse().convert(tags);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class TagsInputDTOConvert extends Converter<TagsInputDTO, Tags> {
        @Override
        protected Tags doForward(TagsInputDTO tagsInputDTO) {
            Tags tags = new Tags();
            BeanUtils.copyProperties(tagsInputDTO, tags);
            return tags;
        }

        @Override
        protected TagsInputDTO doBackward(Tags tags) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
