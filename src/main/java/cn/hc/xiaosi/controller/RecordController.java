package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.RecordInputDTO;
import cn.hc.xiaosi.entity.Record;
import cn.hc.xiaosi.service.RecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @ClassName RecordController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/520:56
 */
@RestController
@RequestMapping(value = "record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 管理端
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端输出")
    public ArrayList<Record> records(){
        return recordService.controlFindAll();
    }

    /**
     * 客户端新增搜索记录
     * @param recordInputDTO
     * @return
     */
    @RequestMapping(value = "save")
    @ApiOperation(value = "新增搜索记录")
    public Message saveRecord(@RequestBody RecordInputDTO recordInputDTO){
        return recordService.saveReocrd(recordInputDTO);
    }

}
