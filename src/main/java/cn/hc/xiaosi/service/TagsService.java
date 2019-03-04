package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.TagsCategoryInputDTO;
import cn.hc.xiaosi.dto.TagsInputDTO;
import cn.hc.xiaosi.dto.TagsOutputDTO;
import cn.hc.xiaosi.dto.TagsStatusInputDTO;
import cn.hc.xiaosi.entity.Tags;

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
     * 新增标签
     *
     * @param tagsInputDTO
     * @return
     */
    Message controlSaveTags(TagsInputDTO tagsInputDTO);

    /**
     * 启用，禁用标签
     *
     * @param tagsStatusInputDTO
     * @return
     */
    Message controlDeleteTags(TagsStatusInputDTO tagsStatusInputDTO);

    /**
     * 编辑标签信息
     *
     * @param tagsInputDTO
     * @return
     */
    Message controlUpdateTags(TagsInputDTO tagsInputDTO);

}
