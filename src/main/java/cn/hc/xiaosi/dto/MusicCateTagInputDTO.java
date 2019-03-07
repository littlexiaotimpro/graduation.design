package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Music;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName MusicCateTagInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:10
 */
@Data
public class MusicCateTagInputDTO {
    private String encategory;

    private String entag;

    /**
     * 正向转化
     *
     * @return
     */
    public Music convertToMusic() {
        MusicCateTagInputDTOConvert musicCateTagInputDTOConvert = new MusicCateTagInputDTOConvert();
        Music convert = musicCateTagInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param music
     * @return
     */
    public MusicCateTagInputDTO convertFor(Music music) {
        MusicCateTagInputDTOConvert musicCateTagInputDTOConvert = new MusicCateTagInputDTOConvert();
        MusicCateTagInputDTO convert = musicCateTagInputDTOConvert.reverse().convert(music);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class MusicCateTagInputDTOConvert extends Converter<MusicCateTagInputDTO, Music> {
        @Override
        protected Music doForward(MusicCateTagInputDTO musicCateTagInputDTO) {
            Music music = new Music();
            BeanUtils.copyProperties(musicCateTagInputDTO, music);
            return music;
        }

        @Override
        protected MusicCateTagInputDTO doBackward(Music music) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
