package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.MediaDAO;
import cn.hc.xiaosi.dto.*;
import cn.hc.xiaosi.entity.Media;
import cn.hc.xiaosi.service.MediaService;
import cn.hc.xiaosi.utils.UploadUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.*;


/**
 * @ClassName MediaServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:28
 */
@Service
public class MediaServiceImp implements MediaService {

    @Autowired
    private MediaDAO mediaDAO;

    @Override
    public ArrayList<Media> controlFindAll() {
        return mediaDAO.findAll();
    }

    @Override
    public String controlSaveIMG(MultipartFile file, String category) {
        return UploadUtil.getFileUrl(file, category);
    }

    @Override
    public Message controlSaveMedia(MediaInputDTO mediaInputDTO) {
        /**
         * 向数据库添加新数据
         */
        Media media = mediaInputDTO.convertToMedia();
        Integer result = mediaDAO.saveMedia(media);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
    }

    @Override
    public Message controlDeleteMedia(MediaStatusInputDTO mediaStatusInputDTO) {
        Media media = mediaStatusInputDTO.convertToMedia();
        Integer result = mediaDAO.deleteMedia(media);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }

    @Override
    public Message controlUpdateMedia(MediaInputDTO mediaInputDTO) {
        /**
         * 更新数据
         */
        Media media = mediaInputDTO.convertToMedia();
        Integer result = mediaDAO.updateMedia(media);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
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
