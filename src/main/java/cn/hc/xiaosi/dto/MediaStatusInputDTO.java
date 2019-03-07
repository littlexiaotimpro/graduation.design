package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Media;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName MediaStatusInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:22
 */
@Data
public class MediaStatusInputDTO {
    private String enmedia;

    private Integer status;

    /**
     * 正向转化
     *
     * @return
     */
    public Media convertToMedia() {
        MediaStatusInputDTOConvert mediaStatusInputDTOConvert = new MediaStatusInputDTOConvert();
        Media convert = mediaStatusInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param media
     * @return
     */
    public MediaStatusInputDTO convertFor(Media media) {
        MediaStatusInputDTOConvert mediaStatusInputDTOConvert = new MediaStatusInputDTOConvert();
        MediaStatusInputDTO convert = mediaStatusInputDTOConvert.reverse().convert(media);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MediaStatusInputDTOConvert extends Converter<MediaStatusInputDTO, Media> {
        @Override
        protected Media doForward(MediaStatusInputDTO mediaStatusInputDTO) {
            Media media = new Media();
            BeanUtils.copyProperties(mediaStatusInputDTO, media);
            return media;
        }

        @Override
        protected MediaStatusInputDTO doBackward(Media media) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
