package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.RecordInputDTO;
import cn.hc.xiaosi.entity.Record;

import java.util.ArrayList;
import java.util.Map;

/**
 * @ClassName RecordService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/520:47
 */
public interface RecordService {

    /**
     * 管理端获取所有记录
     *
     * @return
     */
    ArrayList<Record> controlFindAll();

    /**
     * 客户端新增搜索记录
     *
     * @param recordInputDTO
     * @return
     */
    Message saveReocrd(RecordInputDTO recordInputDTO);

    /**
     * 查询已有搜索记录
     * 按照搜索程度自动给推荐最好的结果
     *
     * @return
     */
    ArrayList autoComplete();

}
