package com.whoai.blog.dto;

import com.whoai.blog.entity.Music;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName MusicOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/714:45
 */
@Data
public class MusicOutputDTO {
    private String enmusic;

    private String enarticle;

    private String encategory;

    private String entag;

    private String imgmusic;

    private String caption;

    private String author;

    private String summary;

    /**
     * 正向转化
     *
     * @return
     */
    public Music convertToMusic() {
        MusicOutputDTOConvert musicOutputDTOConvert = new MusicOutputDTOConvert();
        Music convert = musicOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param music
     * @return
     */
    public MusicOutputDTO convertFor(Music music) {
        MusicOutputDTOConvert musicOutputDTOConvert = new MusicOutputDTOConvert();
        MusicOutputDTO convert = musicOutputDTOConvert.reverse().convert(music);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MusicOutputDTOConvert extends Converter<MusicOutputDTO, Music> {
        @Override
        protected Music doForward(MusicOutputDTO musicOutputDTO) {
            throw new AssertionError("不支持正向转化方法");
        }

        @Override
        protected MusicOutputDTO doBackward(Music music) {
            MusicOutputDTO musicOutputDTO = new MusicOutputDTO();
            BeanUtils.copyProperties(music, musicOutputDTO);
            return musicOutputDTO;
        }
    }

}
