package com.whoai.blog.dto;

import com.whoai.blog.entity.Media;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName MediaInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:22
 */
@Data
public class MediaInputDTO {
    private String enmedia;

    private String enarticle;

    private String ennav;

    private String encategory;

    private String entag;

    private String adminid;

    private String imgmedia;

    private String caption;

    private String showtime;

    private String summary;

    private String bluray720;

    private String bluray1080;

    private String bluraydisk;

    private Integer status;

    /**
     * 正向转化
     *
     * @return
     */
    public Media convertToMedia() {
        MediaInputDTOConvert mediaInputDTOConvert = new MediaInputDTOConvert();
        Media convert = mediaInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param media
     * @return
     */
    public MediaInputDTO convertFor(Media media) {
        MediaInputDTOConvert mediaInputDTOConvert = new MediaInputDTOConvert();
        MediaInputDTO convert = mediaInputDTOConvert.reverse().convert(media);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MediaInputDTOConvert extends Converter<MediaInputDTO, Media> {
        @Override
        protected Media doForward(MediaInputDTO mediaInputDTO) {
            Media media = new Media();
            BeanUtils.copyProperties(mediaInputDTO, media);
            return media;
        }

        @Override
        protected MediaInputDTO doBackward(Media media) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
