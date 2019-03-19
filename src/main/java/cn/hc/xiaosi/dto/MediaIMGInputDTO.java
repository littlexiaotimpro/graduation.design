package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Media;
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
public class MediaIMGInputDTO {
    private String encategory;
    private String imgmedia;

    /**
     * 正向转化
     *
     * @return
     */
    public Media convertToMedia() {
        MediaIMGInputDTOConvert mediaIMGInputDTOConvert = new MediaIMGInputDTOConvert();
        Media convert = mediaIMGInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param media
     * @return
     */
    public MediaIMGInputDTO convertFor(Media media) {
        MediaIMGInputDTOConvert mediaIMGInputDTOConvert = new MediaIMGInputDTOConvert();
        MediaIMGInputDTO convert = mediaIMGInputDTOConvert.reverse().convert(media);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MediaIMGInputDTOConvert extends Converter<MediaIMGInputDTO, Media> {
        @Override
        protected Media doForward(MediaIMGInputDTO mediaIMGInputDTO) {
            Media media = new Media();
            BeanUtils.copyProperties(mediaIMGInputDTO, media);
            return media;
        }

        @Override
        protected MediaIMGInputDTO doBackward(Media media) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
