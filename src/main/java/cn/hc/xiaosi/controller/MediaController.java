package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.*;
import cn.hc.xiaosi.entity.Media;
import cn.hc.xiaosi.service.MediaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Map;

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

    @RequestMapping(value = "img")
    @ApiOperation(value = "添加图片数据")
    public String saveIMG(@PathParam("imgmedia") MultipartFile file, @PathParam("category") String category) {
        return mediaService.controlSaveIMG(file, category);
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
    public ArrayList mediaOutputDTOS(@RequestBody MediaCateTagInputDTO mediaCateTagInputDTO) {
        return mediaService.clientFindUsingByEnCateTag(mediaCateTagInputDTO);
    }

}
