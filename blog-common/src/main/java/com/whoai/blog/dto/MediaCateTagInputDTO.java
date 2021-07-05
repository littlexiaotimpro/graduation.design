package com.whoai.blog.dto;

import com.whoai.blog.entity.Media;
import com.google.common.base.Converter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName MediaCateTagInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:21
 */
@Accessors(chain = true)
@Data
public class MediaCateTagInputDTO {
    private String encategory;

    private String entag;

    /**
     * 正向转化
     *
     * @return
     */
    public Media convertToMedia() {
        MediaCateTagInputDTOConvert mediaCateTagInputDTOConvert = new MediaCateTagInputDTOConvert();
        Media convert = mediaCateTagInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param media
     * @return
     */
    public MediaCateTagInputDTO convertFor(Media media) {
        MediaCateTagInputDTOConvert mediaCateTagInputDTOConvert = new MediaCateTagInputDTOConvert();
        MediaCateTagInputDTO convert = mediaCateTagInputDTOConvert.reverse().convert(media);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MediaCateTagInputDTOConvert extends Converter<MediaCateTagInputDTO, Media> {
        @Override
        protected Media doForward(MediaCateTagInputDTO mediaCateTagInputDTO) {
            Media media = new Media();
            BeanUtils.copyProperties(mediaCateTagInputDTO, media);
            return media;
        }

        @Override
        protected MediaCateTagInputDTO doBackward(Media media) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
