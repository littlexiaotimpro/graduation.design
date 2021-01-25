package com.whoai.blog.service;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.MusicCateTagInputDTO;
import com.whoai.blog.dto.MusicInputDTO;
import com.whoai.blog.dto.MusicOutputDTO;
import com.whoai.blog.dto.MusicStatusInputDTO;
import com.whoai.blog.entity.Music;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
     * @param request
     * @return
     */
    String controlSaveIMG(MultipartFile file, String category, HttpServletRequest request) throws IOException;

    /**
     * 新增数据
     *
     * @param musicInputDTO
     * @param request
     * @return
     */
    Message controlSaveMusic(MusicInputDTO musicInputDTO, HttpServletRequest request);

    /**
     * 启用，禁用数据
     *
     * @param musicStatusInputDTO
     * @param request
     * @return
     */
    Message controlDeleteMusic(MusicStatusInputDTO musicStatusInputDTO, HttpServletRequest request);

    /**
     * 编辑数据
     *
     * @param musicInputDTO
     * @param request
     * @return
     */
    Message controlUpdateMusic(MusicInputDTO musicInputDTO, HttpServletRequest request);

    /**
     * 客户端获取数据
     *
     * @param musicCateTagInputDTO
     * @return
     */
    ArrayList<MusicOutputDTO> clientFindUsingByEnCateTag(MusicCateTagInputDTO musicCateTagInputDTO);
}
