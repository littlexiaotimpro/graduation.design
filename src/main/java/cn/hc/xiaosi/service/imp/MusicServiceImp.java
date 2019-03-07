package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.MusicDAO;
import cn.hc.xiaosi.dto.MusicCateTagInputDTO;
import cn.hc.xiaosi.dto.MusicInputDTO;
import cn.hc.xiaosi.dto.MusicOutputDTO;
import cn.hc.xiaosi.dto.MusicStatusInputDTO;
import cn.hc.xiaosi.entity.Music;
import cn.hc.xiaosi.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName MusicServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:29
 */
@Service
public class MusicServiceImp implements MusicService {

    @Autowired
    private MusicDAO musicDAO;

    @Override
    public ArrayList<Music> controlFindAll() {
        return musicDAO.findAll();
    }

    @Override
    public Message controlSaveMusic(MusicInputDTO musicInputDTO) {
        Music music = musicInputDTO.convertToMusic();
        Integer result = musicDAO.saveMusic(music);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
    }

    @Override
    public Message controlDeleteMusic(MusicStatusInputDTO musicStatusInputDTO) {
        Music music = musicStatusInputDTO.convertToMusic();
        Integer result = musicDAO.deleteMusic(music);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }

    @Override
    public Message controlUpdateMusic(MusicInputDTO musicInputDTO) {
        Music music = musicInputDTO.convertToMusic();
        Integer result = musicDAO.updateMusic(music);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }

    @Override
    public ArrayList<MusicOutputDTO> clientFindUsingByEnCateTag(MusicCateTagInputDTO musicCateTagInputDTO) {
        Music music = musicCateTagInputDTO.convertToMusic();
        ArrayList<MusicOutputDTO> arrayList = new ArrayList<MusicOutputDTO>();
        Iterator iterator = musicDAO.findUsingByEnCateTag(music).iterator();
        while (iterator.hasNext()) {
            MusicOutputDTO musicOutputDTO = new MusicOutputDTO();
            musicOutputDTO = musicOutputDTO.convertFor((Music) iterator.next());
            arrayList.add(musicOutputDTO);
        }
        return arrayList;
    }
}
