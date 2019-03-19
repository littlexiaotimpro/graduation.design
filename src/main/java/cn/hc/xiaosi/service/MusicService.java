package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.MusicCateTagInputDTO;
import cn.hc.xiaosi.dto.MusicInputDTO;
import cn.hc.xiaosi.dto.MusicOutputDTO;
import cn.hc.xiaosi.dto.MusicStatusInputDTO;
import cn.hc.xiaosi.entity.Music;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * @ClassName MusicService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:29
 */
public interface MusicService {

    /**
     * 管理端获取所有数据
     *
     * @return
     */
    ArrayList<Music> controlFindAll();

    /**
     * 上传图片
     *
     * @param file
     * @param category
     * @return
     */
    String controlSaveIMG(MultipartFile file, String category);

    /**
     * 新增数据
     *
     * @param musicInputDTO
     * @return
     */
    Message controlSaveMusic(MusicInputDTO musicInputDTO);

    /**
     * 启用，禁用数据
     *
     * @param musicStatusInputDTO
     * @return
     */
    Message controlDeleteMusic(MusicStatusInputDTO musicStatusInputDTO);

    /**
     * 编辑数据
     *
     * @param musicInputDTO
     * @return
     */
    Message controlUpdateMusic(MusicInputDTO musicInputDTO);

    /**
     * 客户端获取数据
     *
     * @param musicCateTagInputDTO
     * @return
     */
    ArrayList<MusicOutputDTO> clientFindUsingByEnCateTag(MusicCateTagInputDTO musicCateTagInputDTO);
}
