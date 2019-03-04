package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.TagsDAO;
import cn.hc.xiaosi.dto.TagsCategoryInputDTO;
import cn.hc.xiaosi.dto.TagsInputDTO;
import cn.hc.xiaosi.dto.TagsOutputDTO;
import cn.hc.xiaosi.dto.TagsStatusInputDTO;
import cn.hc.xiaosi.entity.Tags;
import cn.hc.xiaosi.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName TagsServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/318:43
 */
@Service
public class TagsServiceImp implements TagsService {

    @Autowired
    private TagsDAO tagsDAO;

    @Override
    public ArrayList<TagsOutputDTO> clientFindUsingByEnCategory(TagsCategoryInputDTO tagsCategoryInputDTO) {
        Tags tags = tagsCategoryInputDTO.convertToTags();
        ArrayList<TagsOutputDTO> arrayList = new ArrayList<TagsOutputDTO>();
        Iterator iterator = tagsDAO.findByEnCategory(tags).iterator();
        while (iterator.hasNext()) {
            TagsOutputDTO tagsOutputDTO = new TagsOutputDTO();
            tagsOutputDTO = tagsOutputDTO.convertFor((Tags) iterator.next());
            arrayList.add(tagsOutputDTO);
        }
        return arrayList;
    }

    @Override
    public ArrayList<Tags> controlFindAll() {
        return tagsDAO.findAll();
    }

    @Override
    public Message controlSaveTags(TagsInputDTO tagsInputDTO) {
        Tags tags = tagsInputDTO.convertToTags();
        Integer result = tagsDAO.saveTags(tags);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
    }

    @Override
    public Message controlDeleteTags(TagsStatusInputDTO tagsStatusInputDTO) {
        Tags tags = tagsStatusInputDTO.convertToTags();
        Integer result = tagsDAO.deleteTags(tags);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }

    @Override
    public Message controlUpdateTags(TagsInputDTO tagsInputDTO) {
        Tags tags = tagsInputDTO.convertToTags();
        Integer result = tagsDAO.updateTags(tags);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }
}
