package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.CategoryDAO;
import cn.hc.xiaosi.dto.CategoryInputDTO;
import cn.hc.xiaosi.dto.CategoryNavbarInputDTO;
import cn.hc.xiaosi.dto.CategoryOutputDTO;
import cn.hc.xiaosi.dto.CategoryStatusInputDTO;
import cn.hc.xiaosi.entity.Category;
import cn.hc.xiaosi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName CategoryServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/220:40
 */
@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

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
    public Message controlSaveCategory(CategoryInputDTO categoryInputDTO) {
        Category category = categoryInputDTO.convertToCategory();
        Integer result = categoryDAO.saveCategory(category);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
    }

    @Override
    public Message controlDeleteCategory(CategoryStatusInputDTO categoryStatusInputDTO) {
        Category category = categoryStatusInputDTO.convertToCategory();
        Integer result = categoryDAO.deleteCategory(category);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }

    @Override
    public Message controlUpdateCategory(CategoryInputDTO categoryInputDTO) {
        Category category = categoryInputDTO.convertToCategory();
        Integer result = categoryDAO.updateCategory(category);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }
}
