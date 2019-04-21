package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.*;
import cn.hc.xiaosi.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

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
     * 添加图片
     *
     * @param file
     * @param category
     * @param request
     * @return
     */
    String controlSaveIMG(MultipartFile file, String category, HttpServletRequest request);

    /**
     * 新增数据
     *
     * @param mediaInputDTO
     * @param request
     * @return
     */
    Message controlSaveMedia(MediaInputDTO mediaInputDTO, HttpServletRequest request);

    /**
     * 启用，禁用数据
     *
     * @param mediaStatusInputDTO
     * @param request
     * @return
     */
    Message controlDeleteMedia(MediaStatusInputDTO mediaStatusInputDTO, HttpServletRequest request);

    /**
     * 编辑数据
     *
     * @param mediaInputDTO
     * @param request
     * @return
     */
    Message controlUpdateMedia(MediaInputDTO mediaInputDTO, HttpServletRequest request);

    /**
     * 客户端获取数据
     *
     * @param mediaCateTagInputDTO
     * @return
     */
    ArrayList clientFindUsingByEnCateTag(MediaCateTagInputDTO mediaCateTagInputDTO);

}
