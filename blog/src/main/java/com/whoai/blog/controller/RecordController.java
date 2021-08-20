package com.whoai.blog.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.dto.RecordInputDTO;
import com.whoai.blog.entity.Record;
import com.whoai.blog.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RecordController
 * 搜索记录控制器
 *
 * @author XiaoSi
 * @date 2019/3/5
 */
@Api("搜索记录控制器")
@RestController
@RequestMapping(value = "record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 查询所有记录
     */
    @GetMapping(value = "/find/all")
    @ApiOperation(value = "查询所有记录")
    public ResponseResult<List<Record>> records() {
        final List<Record> records = recordService.findAll();
        return ResponseResult.success(records, "查询成功");
    }

    /**
     * 新增搜索记录
     *
     * @param recordInputDTO 搜索记录
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增搜索记录")
    public ResponseResult<Void> saveRecord(@RequestBody RecordInputDTO recordInputDTO) {
        final Integer integer = recordService.saveRecord(recordInputDTO);
        if (integer <= 0) {
            return ResponseResult.fail("新增失败");
        }
        return ResponseResult.success(null, "新增成功");
    }

    /**
     * 自动填充搜索记录
     */
    @RequestMapping(value = "/auto")
    @ApiOperation(value = "自动填充搜索记录")
    public ResponseResult<List<Object>> autoRecord(@RequestBody RecordInputDTO recordInputDTO) {
        final List<Object> objects = recordService.autoComplete(recordInputDTO);
        return ResponseResult.success(objects, "查询成功");
    }

}
