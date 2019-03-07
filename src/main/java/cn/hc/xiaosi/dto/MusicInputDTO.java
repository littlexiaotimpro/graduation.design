package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Music;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName MusicInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:10
 */
@Data
public class MusicInputDTO {
    private String enmusic;

    private String enarticle;

    private String ennav;

    private String encategory;

    private String entag;

    private String adminid;

    private String imgmusic;

    private String caption;

    private String author;

    private String summary;

    private Integer status;

    /**
     * 正向转化
     *
     * @return
     */
    public Music convertToMusic() {
        MusicInputDTOConvert musicInputDTOConvert = new MusicInputDTOConvert();
        Music convert = musicInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param music
     * @return
     */
    public MusicInputDTO convertFor(Music music) {
        MusicInputDTOConvert musicInputDTOConvert = new MusicInputDTOConvert();
        MusicInputDTO convert = musicInputDTOConvert.reverse().convert(music);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MusicInputDTOConvert extends Converter<MusicInputDTO, Music> {
        @Override
        protected Music doForward(MusicInputDTO musicInputDTO) {
            Music music = new Music();
            BeanUtils.copyProperties(musicInputDTO, music);
            return music;
        }

        @Override
        protected MusicInputDTO doBackward(Music music) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
