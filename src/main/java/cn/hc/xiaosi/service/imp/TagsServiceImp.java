package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.TagsDAO;
import cn.hc.xiaosi.dto.TagsCategoryInputDTO;
import cn.hc.xiaosi.dto.TagsInputDTO;
import cn.hc.xiaosi.dto.TagsOutputDTO;
import cn.hc.xiaosi.dto.TagsStatusInputDTO;
import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.entity.Tags;
import cn.hc.xiaosi.service.LogService;
import cn.hc.xiaosi.service.TagsService;
import cn.hc.xiaosi.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName TagsServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/318:43
 */
@Service
@Slf4j
public class TagsServiceImp implements TagsService {

    @Autowired
    private TagsDAO tagsDAO;

    @Autowired
    private LogService logService;

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
    public ArrayList<TagsOutputDTO> controlFindAllCaption() {
        ArrayList<TagsOutputDTO> arrayList = new ArrayList<TagsOutputDTO>();
        Iterator iterator = tagsDAO.findAll().iterator();
        while (iterator.hasNext()) {
            TagsOutputDTO tagsOutputDTO = new TagsOutputDTO();
            tagsOutputDTO = tagsOutputDTO.convertFor((Tags) iterator.next());
            arrayList.add(tagsOutputDTO);
        }
        return arrayList;
    }

    @Override
    public Message controlSaveTags(TagsInputDTO tagsInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试新增标签数据。", operator);
            Tags tags = tagsInputDTO.convertToTags();
            Integer result = tagsDAO.saveTags(tags);
            if (result == null || result == 0) {
                log.info("管理员[{}]新增标签数据失败", operator);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增标签数据失败");
                message.setCode(-1).setMsg("添加失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]新增标签数据成功，新增数据为：[{}]", operator, tagsInputDTO);
                }
                log.info("管理员[{}]新增标签数据成功，新增数据为：[{}]，影响结果数：[{}]", operator, tagsInputDTO, result);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增标签数据成功，新增数据为：" + tagsInputDTO);
                message.setCode(1).setMsg("添加成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlDeleteTags(TagsStatusInputDTO tagsStatusInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改标签状态数据。", operator);
            Tags tags = tagsStatusInputDTO.convertToTags();
            Integer result = tagsDAO.deleteTags(tags);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改标签状态数据失败", operator);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改标签状态数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改标签状态数据成功，修改数据为：[{}]", operator, tagsStatusInputDTO);
                }
                log.info("管理员[{}]修改标签状态数据成功，修改数据为：[{}]，影响结果数：[{}]", operator, tagsStatusInputDTO, result);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改标签状态数据成功，修改数据为：" + tagsStatusInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlUpdateTags(TagsInputDTO tagsInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改标签状态数据。", operator);
            Tags tags = tagsInputDTO.convertToTags();
            Integer result = tagsDAO.updateTags(tags);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改标签数据失败", operator);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改标签数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改标签数据成功，修改数据为：[{}]", operator, tagsInputDTO);
                }
                log.info("管理员[{}]修改标签数据成功，修改数据为：[{}]，影响结果数：[{}]", operator, tagsInputDTO, result);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改标签数据成功，修改数据为：" + tagsInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }
}
