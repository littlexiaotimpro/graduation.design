package com.whoai.blog.controller;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.MusicCateTagInputDTO;
import com.whoai.blog.dto.MusicInputDTO;
import com.whoai.blog.dto.MusicOutputDTO;
import com.whoai.blog.dto.MusicStatusInputDTO;
import com.whoai.blog.entity.Music;
import com.whoai.blog.service.MusicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName MusicController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:46
 */
@RestController
@RequestMapping(value = "music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端输出")
    public ArrayList<Music> musics() {
        return musicService.controlFindAll();
    }

    @RequestMapping(value = "img")
    @ApiOperation(value = "添加图片数据")
    public String saveIMG(@PathParam("imgmusic") MultipartFile file, @PathParam("category") String category, HttpServletRequest request) throws IOException {
        return musicService.controlSaveIMG(file, category, request);
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增音乐")
    public Message saveMusic(@RequestBody MusicInputDTO musicInputDTO, HttpServletRequest request) {
        return musicService.controlSaveMusic(musicInputDTO, request);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用音乐")
    public Message deleteMusic(@RequestBody MusicStatusInputDTO musicStatusInputDTO, HttpServletRequest request) {
        return musicService.controlDeleteMusic(musicStatusInputDTO, request);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑音乐信息")
    public Message updateMusci(@RequestBody MusicInputDTO musicInputDTO, HttpServletRequest request) {
        return musicService.controlUpdateMusic(musicInputDTO, request);
    }

    /**
     * 客户端
     *
     * @param musicCateTagInputDTO
     * @return
     */
    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端输出")
    public ArrayList<MusicOutputDTO> musicOutputDTOS(@RequestBody MusicCateTagInputDTO musicCateTagInputDTO) {
        return musicService.clientFindUsingByEnCateTag(musicCateTagInputDTO);
    }

}
