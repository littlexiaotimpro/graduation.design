package com.whoai.blog.controller;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.CategoryInputDTO;
import com.whoai.blog.dto.CategoryNavbarInputDTO;
import com.whoai.blog.dto.CategoryOutputDTO;
import com.whoai.blog.dto.CategoryStatusInputDTO;
import com.whoai.blog.entity.Category;
import com.whoai.blog.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/220:49
 */
@RestController
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端分类输出")
    public ArrayList<Category> categories() {
        return categoryService.controlFindAll();
    }

    @RequestMapping(value = "caption")
    @ApiOperation(value = "管理端名称输出")
    public ArrayList<CategoryOutputDTO> getCaption() {
        return categoryService.controlFindAllCaption();
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增分类")
    public Message saveCategory(@RequestBody CategoryInputDTO categoryInputDTO, HttpServletRequest request) {
        return categoryService.controlSaveCategory(categoryInputDTO, request);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用分类")
    public Message deleteCategory(@RequestBody CategoryStatusInputDTO categoryStatusInputDTO, HttpServletRequest request) {
        return categoryService.controlDeleteCategory(categoryStatusInputDTO, request);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑分类信息")
    public Message updateCategory(@RequestBody CategoryInputDTO categoryInputDTO, HttpServletRequest request) {
        return categoryService.controlUpdateCategory(categoryInputDTO, request);
    }

    /**
     * 客户端
     *
     * @param categoryNavbarInputDTO
     * @return
     */
    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端分类输出")
    public ArrayList<CategoryOutputDTO> categoryOutputDTOS(@RequestBody CategoryNavbarInputDTO categoryNavbarInputDTO) {
        return categoryService.clientFindUsingByEnNav(categoryNavbarInputDTO);
    }

}
