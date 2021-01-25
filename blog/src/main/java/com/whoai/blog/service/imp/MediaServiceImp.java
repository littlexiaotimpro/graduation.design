package com.whoai.blog.service.imp;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dao.MediaDAO;
import com.whoai.blog.dto.MediaCateTagInputDTO;
import com.whoai.blog.dto.MediaInputDTO;
import com.whoai.blog.dto.MediaOutputDTO;
import com.whoai.blog.dto.MediaStatusInputDTO;
import com.whoai.blog.entity.LogBean;
import com.whoai.blog.entity.Media;
import com.whoai.blog.service.LogService;
import com.whoai.blog.service.MediaService;
import com.whoai.blog.utils.JWTUtil;
import com.whoai.blog.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @ClassName MediaServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:28
 */
@Service
@Slf4j
public class MediaServiceImp implements MediaService {

    @Autowired
    private MediaDAO mediaDAO;

    @Autowired
    private LogService logService;

    @Override
    public ArrayList<Media> controlFindAll() {
        return mediaDAO.findAll();
    }

    @Override
    public String controlSaveIMG(MultipartFile file, String category, HttpServletRequest request) throws IOException {
        return UploadUtil.getFileUrl(file, category, logService, request);
    }

    @Override
    public Message controlSaveMedia(MediaInputDTO mediaInputDTO, HttpServletRequest request) {
        /**
         * 向数据库添加新数据
         */
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试新增媒体数据。", operator);
            Media media = mediaInputDTO.convertToMedia();
            Integer result = mediaDAO.saveMedia(media);
            if (result == null || result == 0) {
                log.info("管理员[{}]新增媒体数据失败", operator);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增媒体数据失败");
                message.setCode(-1).setMsg("添加失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]新增媒体数据成功，新增数据为：[{}]", operator, mediaInputDTO);
                }
                log.info("管理员[{}]新增媒体数据成功，新增数据为：[{}]，影响结果数：[{}]", operator, mediaInputDTO, result);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增媒体数据成功，新增数据为：" + mediaInputDTO);
                message.setCode(1).setMsg("添加成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlDeleteMedia(MediaStatusInputDTO mediaStatusInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改媒体数据状态。", operator);
            Media media = mediaStatusInputDTO.convertToMedia();
            Integer result = mediaDAO.deleteMedia(media);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改媒体数据状态失败", operator);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改媒体数据状态失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改媒体数据状态成功，修改的媒体数据为：enMedia=[{}], status=[{}]", operator, mediaStatusInputDTO.getEnmedia(), mediaStatusInputDTO.getStatus());
                }
                log.info("管理员[{}]修改媒体数据状态成功，修改的媒体数据为：enMedia=[{}], status=[{}]，影响结果数：[{}]", operator, mediaStatusInputDTO.getEnmedia(), mediaStatusInputDTO.getStatus(), result);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改媒体数据状态成功，修改的媒体数据为：" + mediaStatusInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlUpdateMedia(MediaInputDTO mediaInputDTO, HttpServletRequest request) {
        /**
         * 更新数据
         */
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改媒体数据。", operator);
            Media media = mediaInputDTO.convertToMedia();
            Integer result = mediaDAO.updateMedia(media);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改媒体数据失败", operator);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改媒体数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改媒体数据成功，修改的媒体数据为：[{}]", operator, mediaInputDTO);
                }
                log.info("管理员[{}]修改媒体数据成功，修改的媒体数据为：[{}]，影响结果数：[{}]", operator, mediaInputDTO, result);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改媒体数据成功，修改的媒体数据为：" + mediaInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    /**
     * 将取出的数据转为指定格式的json数组对象
     * 为了有序的排列数据，选择使用LinkedHashMap
     *
     * @param mediaCateTagInputDTO
     * @return
     */
    @Override
    public ArrayList clientFindUsingByEnCateTag(MediaCateTagInputDTO mediaCateTagInputDTO) {
        Media media = mediaCateTagInputDTO.convertToMedia();
        Iterator iterator = mediaDAO.findUsingByEnCateTag(media).iterator();
        Map<String, ArrayList<MediaOutputDTO>> map = new LinkedHashMap<String, ArrayList<MediaOutputDTO>>();
        while (iterator.hasNext()) {
            MediaOutputDTO mediaOutputDTO = new MediaOutputDTO();
            mediaOutputDTO = mediaOutputDTO.convertFor((Media) iterator.next());
            String key = mediaOutputDTO.getShowtime().split("-")[0];
            ArrayList<MediaOutputDTO> arrayList;
            if (map.get(key) == null) {
                arrayList = new ArrayList<MediaOutputDTO>();
                arrayList.add(mediaOutputDTO);
                map.put(key, arrayList);
            } else {
                arrayList = map.get(key);
                arrayList.add(mediaOutputDTO);
                map.put(key, arrayList);
            }
        }
        Iterator iter = map.keySet().iterator();
        ArrayList arrayList = new ArrayList();
        while (iter.hasNext()) {
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            String key = iter.next().toString();
            result.put("key", key);
            result.put("value", map.get(key));
            arrayList.add(result);
        }
        return arrayList;
    }
}
