package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.MediaDAO;
import cn.hc.xiaosi.dto.MediaCateTagInputDTO;
import cn.hc.xiaosi.dto.MediaInputDTO;
import cn.hc.xiaosi.dto.MediaOutputDTO;
import cn.hc.xiaosi.dto.MediaStatusInputDTO;
import cn.hc.xiaosi.entity.Media;
import cn.hc.xiaosi.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

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
    public Message controlSaveMedia(MediaInputDTO mediaInputDTO) {
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
