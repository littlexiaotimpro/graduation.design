package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.CategoryInputDTO;
import cn.hc.xiaosi.dto.CategoryNavbarInputDTO;
import cn.hc.xiaosi.dto.CategoryOutputDTO;
import cn.hc.xiaosi.dto.CategoryStatusInputDTO;
import cn.hc.xiaosi.entity.Category;

import java.util.ArrayList;

/**
 * @ClassName CategoryService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/219:15
 */
public interface CategoryService {

    /**
     * 客户端根据导航编号查询启用的分类
     *
     * @param categoryNavbarInputDTO
     * @return
     */
    ArrayList<CategoryOutputDTO> clientFindUsingByEnNav(CategoryNavbarInputDTO categoryNavbarInputDTO);

    /**
     * 管理端查询所有分类
     *
     * @return
     */
    ArrayList<Category> controlFindAll();

    /**
     * 新增分类
     *
     * @param categoryInputDTO
     * @return
     */
    Message controlSaveCategory(CategoryInputDTO categoryInputDTO);


    /**
     * 启用，禁用分类
     *
     * @param categoryStatusInputDTO
     * @return
     */
    Message controlDeleteCategory(CategoryStatusInputDTO categoryStatusInputDTO);

    /**
     * 编辑分类信息
     *
     * @param categoryInputDTO
     * @return
     */
    Message controlUpdateCategory(CategoryInputDTO categoryInputDTO);


}