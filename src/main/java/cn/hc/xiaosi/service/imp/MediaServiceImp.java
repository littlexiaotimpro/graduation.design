package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.MediaDAO;
import cn.hc.xiaosi.dto.*;
import cn.hc.xiaosi.entity.Media;
import cn.hc.xiaosi.service.MediaService;
import cn.hc.xiaosi.utils.OSSClientUtil;
import cn.hc.xiaosi.utils.UploadUtil;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static cn.hc.xiaosi.bean.OSSClientConstants.BACKET_NAME;
import static cn.hc.xiaosi.bean.OSSClientConstants.FOLDER;

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
        return UploadUtil.getImgUrl(file, category);
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

    @Override
    public ArrayList<MediaOutputDTO> clientFindUsingByEnCateTag(MediaCateTagInputDTO mediaCateTagInputDTO) {
        Media media = mediaCateTagInputDTO.convertToMedia();
        ArrayList<MediaOutputDTO> arrayList = new ArrayList<MediaOutputDTO>();
        Iterator iterator = mediaDAO.findUsingByEnCateTag(media).iterator();
        while (iterator.hasNext()) {
            MediaOutputDTO mediaOutputDTO = new MediaOutputDTO();
            mediaOutputDTO = mediaOutputDTO.convertFor((Media) iterator.next());
            arrayList.add(mediaOutputDTO);
        }
        return arrayList;
    }
}
