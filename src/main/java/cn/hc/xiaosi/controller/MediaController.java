package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.MediaCateTagInputDTO;
import cn.hc.xiaosi.dto.MediaInputDTO;
import cn.hc.xiaosi.dto.MediaOutputDTO;
import cn.hc.xiaosi.dto.MediaStatusInputDTO;
import cn.hc.xiaosi.entity.Media;
import cn.hc.xiaosi.service.MediaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @ClassName MediaController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:46
 */
@RestController
@RequestMapping(value = "media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端输出")
    public ArrayList<Media> medias() {
        return mediaService.controlFindAll();
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增数据")
    public Message saveMedia(@RequestBody MediaInputDTO mediaInputDTO) {
        return mediaService.controlSaveMedia(mediaInputDTO);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用数据")
    public Message deleteMedia(@RequestBody MediaStatusInputDTO mediaStatusInputDTO) {
        return mediaService.controlDeleteMedia(mediaStatusInputDTO);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑数据")
    public Message updateMedia(@RequestBody MediaInputDTO mediaInputDTO) {
        return mediaService.controlUpdateMedia(mediaInputDTO);
    }

    /**
     * 客户端
     *
     * @param mediaCateTagInputDTO
     * @return
     */
    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端输出")
    public ArrayList<MediaOutputDTO> mediaOutputDTOS(@RequestBody MediaCateTagInputDTO mediaCateTagInputDTO) {
        return mediaService.clientFindUsingByEnCateTag(mediaCateTagInputDTO);
    }

}
