package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Media;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName MediaOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:17
 */
@Data
public class MediaOutputDTO {
    private String enmedia;

    private String enarticle;

    private String encategory;

    private String entag;

    private String imgmedia;

    private String caption;

    private String showtime;

    private String summary;

    /**
     * 正向转化
     *
     * @return
     */
    public Media convertToMedia() {
        MediaOutputDTOConvert mediaOutputDTOConvert = new MediaOutputDTOConvert();
        Media convert = mediaOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param media
     * @return
     */
    public MediaOutputDTO convertFor(Media media) {
        MediaOutputDTOConvert mediaOutputDTOConvert = new MediaOutputDTOConvert();
        MediaOutputDTO convert = mediaOutputDTOConvert.reverse().convert(media);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MediaOutputDTOConvert extends Converter<MediaOutputDTO, Media> {
        @Override
        protected Media doForward(MediaOutputDTO mediaOutputDTO) {
            throw new AssertionError("不支持正向转化方法");
        }

        @Override
        protected MediaOutputDTO doBackward(Media media) {
            MediaOutputDTO mediaOutputDTO = new MediaOutputDTO();
            BeanUtils.copyProperties(media, mediaOutputDTO);
            return mediaOutputDTO;
        }
    }
}
