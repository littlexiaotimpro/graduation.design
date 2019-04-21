package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.TagsCategoryInputDTO;
import cn.hc.xiaosi.dto.TagsInputDTO;
import cn.hc.xiaosi.dto.TagsOutputDTO;
import cn.hc.xiaosi.dto.TagsStatusInputDTO;
import cn.hc.xiaosi.entity.Tags;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName TagsService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/318:42
 */
public interface TagsService {

    /**
     * 客户端通过分类查询启用的标签
     *
     * @param tagsCategoryInputDTO
     * @return
     */
    ArrayList<TagsOutputDTO> clientFindUsingByEnCategory(TagsCategoryInputDTO tagsCategoryInputDTO);

    /**
     * 管理端获取所有的标签信息
     *
     * @return
     */
    ArrayList<Tags> controlFindAll();

    /**
     * 管理端获取所有的 标识--名称 的集合
     *
     * @return
     */
    ArrayList<TagsOutputDTO> controlFindAllCaption();

    /**
     * 新增标签
     *
     * @param tagsInputDTO
     * @param request
     * @return
     */
    Message controlSaveTags(TagsInputDTO tagsInputDTO, HttpServletRequest request);

    /**
     * 启用，禁用标签
     *
     * @param tagsStatusInputDTO
     * @param request
     * @return
     */
    Message controlDeleteTags(TagsStatusInputDTO tagsStatusInputDTO, HttpServletRequest request);

    /**
     * 编辑标签信息
     *
     * @param tagsInputDTO
     * @param request
     * @return
     */
    Message controlUpdateTags(TagsInputDTO tagsInputDTO, HttpServletRequest request);

}
