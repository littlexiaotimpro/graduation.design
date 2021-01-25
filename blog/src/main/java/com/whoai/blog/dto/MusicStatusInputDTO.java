package com.whoai.blog.dto;

import com.whoai.blog.entity.Music;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName MusicStatusInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:09
 */
@Data
public class MusicStatusInputDTO {
    private String enmusic;

    private Integer status;

    /**
     * 正向转化
     *
     * @return
     */
    public Music convertToMusic() {
        MusicStatusInputDTOConvert musicStatusInputDTOConvert = new MusicStatusInputDTOConvert();
        Music convert = musicStatusInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param music
     * @return
     */
    public MusicStatusInputDTO convertFor(Music music) {
        MusicStatusInputDTOConvert musicStatusInputDTOConvert = new MusicStatusInputDTOConvert();
        MusicStatusInputDTO convert = musicStatusInputDTOConvert.reverse().convert(music);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MusicStatusInputDTOConvert extends Converter<MusicStatusInputDTO, Music> {
        @Override
        protected Music doForward(MusicStatusInputDTO musicStatusInputDTO) {
            Music music = new Music();
            BeanUtils.copyProperties(musicStatusInputDTO, music);
            return music;
        }

        @Override
        protected MusicStatusInputDTO doBackward(Music music) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
