package com.whoai.blog.service.imp;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dao.CategoryDAO;
import com.whoai.blog.dto.CategoryInputDTO;
import com.whoai.blog.dto.CategoryNavbarInputDTO;
import com.whoai.blog.dto.CategoryOutputDTO;
import com.whoai.blog.dto.CategoryStatusInputDTO;
import com.whoai.blog.entity.Category;
import com.whoai.blog.entity.LogBean;
import com.whoai.blog.service.CategoryService;
import com.whoai.blog.service.LogService;
import com.whoai.blog.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName CategoryServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/220:40
 */
@Service
@Slf4j
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private LogService logService;

    @Override
    public ArrayList<CategoryOutputDTO> clientFindUsingByEnNav(CategoryNavbarInputDTO categoryNavbarInputDTO) {
        Category category = categoryNavbarInputDTO.convertToCategory();
        ArrayList<CategoryOutputDTO> arrayList = new ArrayList<CategoryOutputDTO>();
        Iterator iterator = categoryDAO.findByEnNav(category).iterator();
        while (iterator.hasNext()) {
            CategoryOutputDTO categoryOutputDTO = new CategoryOutputDTO();
            categoryOutputDTO = categoryOutputDTO.convertFor((Category) iterator.next());
            arrayList.add(categoryOutputDTO);
        }
        return arrayList;
    }

    @Override
    public ArrayList<Category> controlFindAll() {
        return categoryDAO.findAll();
    }

    @Override
    public ArrayList<CategoryOutputDTO> controlFindAllCaption() {
        ArrayList<CategoryOutputDTO> arrayList = new ArrayList<CategoryOutputDTO>();
        Iterator iterator = categoryDAO.findAll().iterator();
        while (iterator.hasNext()) {
            CategoryOutputDTO categoryOutputDTO = new CategoryOutputDTO();
            categoryOutputDTO = categoryOutputDTO.convertFor((Category) iterator.next());
            arrayList.add(categoryOutputDTO);
        }
        return arrayList;
    }

    @Override
    public Message controlSaveCategory(CategoryInputDTO categoryInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试新增分类数据。", operator);
            Category category = categoryInputDTO.convertToCategory();
            Integer result = categoryDAO.saveCategory(category);
            if (result == null || result == 0) {
                log.info("管理员[{}]新增分类数据失败", operator);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增分类数据失败");
                message.setCode(-1).setMsg("添加失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]新增分类数据成功，新增数据为：[{}]", operator, categoryInputDTO);
                }
                log.info("管理员[{}]新增分类数据成功，新增数据为：[{}]，影响结果数：[{}]", operator, categoryInputDTO, result);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增分类数据成功，新增数据为：" + categoryInputDTO);
                message.setCode(1).setMsg("添加成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlDeleteCategory(CategoryStatusInputDTO categoryStatusInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改分类状态数据。", operator);
            Category category = categoryStatusInputDTO.convertToCategory();
            Integer result = categoryDAO.deleteCategory(category);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改分类状态数据失败", operator);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改分类状态数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改分类状态数据成功，修改数据为：[{}]", operator, categoryStatusInputDTO);
                }
                log.info("管理员[{}]修改分类状态数据成功，修改数据为：[{}]，影响结果数：[{}]", operator, categoryStatusInputDTO, result);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改分类状态数据成功，修改数据为：" + categoryStatusInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlUpdateCategory(CategoryInputDTO categoryInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改分类状态数据。", operator);
            Category category = categoryInputDTO.convertToCategory();
            Integer result = categoryDAO.updateCategory(category);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改分类数据失败", operator);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改分类数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改分类数据成功，修改数据为：[{}]", operator, categoryInputDTO);
                }
                log.info("管理员[{}]修改分类数据成功，修改数据为：[{}]，影响结果数：[{}]", operator, categoryInputDTO, result);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改分类数据成功，修改数据为：" + categoryInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }
}
