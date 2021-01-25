package com.whoai.blog.controller;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.TagsCategoryInputDTO;
import com.whoai.blog.dto.TagsInputDTO;
import com.whoai.blog.dto.TagsOutputDTO;
import com.whoai.blog.dto.TagsStatusInputDTO;
import com.whoai.blog.entity.Tags;
import com.whoai.blog.service.TagsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName TagsController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/318:58
 */
@RestController
@RequestMapping(value = "tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端输出")
    public ArrayList<Tags> controlTags() {
        return tagsService.controlFindAll();
    }

    @RequestMapping(value = "caption")
    @ApiOperation(value = "名称输出")
    public ArrayList<TagsOutputDTO> getCaption() {
        return tagsService.controlFindAllCaption();
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增标签")
    public Message saveTags(@RequestBody TagsInputDTO tagsInputDTO, HttpServletRequest request) {
        return tagsService.controlSaveTags(tagsInputDTO, request);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用标签")
    public Message deleteTags(@RequestBody TagsStatusInputDTO tagsStatusInputDTO, HttpServletRequest request) {
        return tagsService.controlDeleteTags(tagsStatusInputDTO, request);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑标签信息")
    public Message updateTags(@RequestBody TagsInputDTO tagsInputDTO, HttpServletRequest request) {
        return tagsService.controlUpdateTags(tagsInputDTO, request);
    }

    /**
     * 客户端
     *
     * @param categoryInputDTO
     * @return
     */
    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端输出")
    public ArrayList<TagsOutputDTO> tagsOutputDTOS(@RequestBody TagsCategoryInputDTO categoryInputDTO) {
        return tagsService.clientFindUsingByEnCategory(categoryInputDTO);
    }

}
