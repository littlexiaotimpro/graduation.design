package com.whoai.blog.service;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.CategoryInputDTO;
import com.whoai.blog.dto.CategoryNavbarInputDTO;
import com.whoai.blog.dto.CategoryOutputDTO;
import com.whoai.blog.dto.CategoryStatusInputDTO;
import com.whoai.blog.entity.Category;

import javax.servlet.http.HttpServletRequest;
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
     * 管理端获取所有的 标识--名称 的集合
     *
     * @return
     */
    ArrayList<CategoryOutputDTO> controlFindAllCaption();

    /**
     * 新增分类
     *
     * @param categoryInputDTO
     * @param request
     * @return
     */
    Message controlSaveCategory(CategoryInputDTO categoryInputDTO, HttpServletRequest request);


    /**
     * 启用，禁用分类
     *
     * @param categoryStatusInputDTO
     * @param request
     * @return
     */
    Message controlDeleteCategory(CategoryStatusInputDTO categoryStatusInputDTO, HttpServletRequest request);

    /**
     * 编辑分类信息
     *
     * @param categoryInputDTO
     * @param request
     * @return
     */
    Message controlUpdateCategory(CategoryInputDTO categoryInputDTO, HttpServletRequest request);


}
