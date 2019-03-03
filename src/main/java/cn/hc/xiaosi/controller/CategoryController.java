package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.CategoryInputDTO;
import cn.hc.xiaosi.dto.CategoryNavbarInputDTO;
import cn.hc.xiaosi.dto.CategoryOutputDTO;
import cn.hc.xiaosi.dto.CategoryStatusInputDTO;
import cn.hc.xiaosi.entity.Category;
import cn.hc.xiaosi.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增分类")
    public Message saveCategory(@RequestBody CategoryInputDTO categoryInputDTO) {
        return categoryService.controlSaveCategory(categoryInputDTO);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用分类")
    public Message deleteCategory(@RequestBody CategoryStatusInputDTO categoryStatusInputDTO) {
        return categoryService.controlDeleteCategory(categoryStatusInputDTO);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑分类信息")
    public Message updateCategory(@RequestBody CategoryInputDTO categoryInputDTO) {
        return categoryService.controlUpdateCategory(categoryInputDTO);
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
