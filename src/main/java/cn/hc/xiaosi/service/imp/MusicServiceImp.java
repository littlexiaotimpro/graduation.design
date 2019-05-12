package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.MusicDAO;
import cn.hc.xiaosi.dto.MusicCateTagInputDTO;
import cn.hc.xiaosi.dto.MusicInputDTO;
import cn.hc.xiaosi.dto.MusicOutputDTO;
import cn.hc.xiaosi.dto.MusicStatusInputDTO;
import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.entity.Music;
import cn.hc.xiaosi.service.LogService;
import cn.hc.xiaosi.service.MusicService;
import cn.hc.xiaosi.utils.JWTUtil;
import cn.hc.xiaosi.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName MusicServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/715:29
 */
@Service
@Slf4j
public class MusicServiceImp implements MusicService {

    @Autowired
    private MusicDAO musicDAO;

    @Autowired
    private LogService logService;

    @Override
    public ArrayList<Music> controlFindAll() {
        return musicDAO.findAll();
    }

    @Override
    public String controlSaveIMG(MultipartFile file, String category, HttpServletRequest request) throws IOException {
        return UploadUtil.getFileUrl(file, category, logService, request);
    }

    @Override
    public Message controlSaveMusic(MusicInputDTO musicInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试新增音乐数据。", operator);
            Music music = musicInputDTO.convertToMusic();
            Integer result = musicDAO.saveMusic(music);
            if (result == null || result == 0) {
                log.info("管理员[{}]新增音乐数据失败", operator);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增音乐数据失败");
                message.setCode(-1).setMsg("添加失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]新增音乐数据成功，新增数据为：[{}]", operator, musicInputDTO);
                }
                log.info("管理员[{}]新增音乐数据成功，新增数据为：[{}]，影响结果数：[{}]", operator, musicInputDTO, result);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增音乐数据成功，新增数据为：" + musicInputDTO);
                message.setCode(1).setMsg("添加成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlDeleteMusic(MusicStatusInputDTO musicStatusInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改音乐数据状态。", operator);
            Music music = musicStatusInputDTO.convertToMusic();
            Integer result = musicDAO.deleteMusic(music);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改音乐数据状态失败", operator);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改音乐数据状态失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改音乐数据状态成功，修改的音乐数据为：enMedia=[{}], status=[{}]", operator, musicStatusInputDTO.getEnmusic(), musicStatusInputDTO.getStatus());
                }
                log.info("管理员[{}]修改音乐数据状态成功，修改的音乐数据为：enMedia=[{}], status=[{}]，影响结果数：[{}]", operator, musicStatusInputDTO.getEnmusic(), musicStatusInputDTO.getStatus(), result);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改音乐数据状态成功，修改的音乐数据为：" + musicStatusInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlUpdateMusic(MusicInputDTO musicInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改音乐数据。", operator);
            Music music = musicInputDTO.convertToMusic();
            Integer result = musicDAO.updateMusic(music);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改音乐数据失败", operator);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改音乐数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改音乐数据成功，修改的音乐数据为：[{}]", operator, musicInputDTO);
                }
                log.info("管理员[{}]修改音乐数据成功，修改的音乐数据为：[{}]，影响结果数：[{}]", operator, musicInputDTO, result);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改音乐数据成功，修改的音乐数据为：" + musicInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
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
