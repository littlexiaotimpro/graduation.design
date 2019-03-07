package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.MediaCateTagInputDTO;
import cn.hc.xiaosi.dto.MediaInputDTO;
import cn.hc.xiaosi.dto.MediaOutputDTO;
import cn.hc.xiaosi.dto.MediaStatusInputDTO;
import cn.hc.xiaosi.entity.Media;

import java.util.ArrayList;

/**
 * @ClassName MediaService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:28
 */
public interface MediaService {

    /**
     * 管理端获取所有数据
     *
     * @return
     */
    ArrayList<Media> controlFindAll();

    /**
     * 新增数据
     *
     * @param mediaInputDTO
     * @return
     */
    Message controlSaveMedia(MediaInputDTO mediaInputDTO);

    /**
     * 启用，禁用数据
     *
     * @param mediaStatusInputDTO
     * @return
     */
    Message controlDeleteMedia(MediaStatusInputDTO mediaStatusInputDTO);

    /**
     * 编辑数据
     *
     * @param mediaInputDTO
     * @return
     */
    Message controlUpdateMedia(MediaInputDTO mediaInputDTO);

    /**
     * 客户端获取数据
     *
     * @param mediaCateTagInputDTO
     * @return
     */
    ArrayList<MediaOutputDTO> clientFindUsingByEnCateTag(MediaCateTagInputDTO mediaCateTagInputDTO);

}
