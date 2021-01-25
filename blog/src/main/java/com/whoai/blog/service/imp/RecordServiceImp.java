package com.whoai.blog.service.imp;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dao.ArticleDAO;
import com.whoai.blog.dao.RecordDAO;
import com.whoai.blog.dto.RecordInputDTO;
import com.whoai.blog.entity.Record;
import com.whoai.blog.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName RecordServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/520:48
 */
@Service
public class RecordServiceImp implements RecordService {

    @Autowired
    private RecordDAO recordDAO;

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public ArrayList<Record> controlFindAll() {
        return recordDAO.findAll();
    }

    @Override
    public Message saveReocrd(RecordInputDTO recordInputDTO) {
        Record record = recordInputDTO.convertToRecord();
        Integer result = recordDAO.saveRecord(record);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
    }

    @Override
    public ArrayList autoComplete(RecordInputDTO recordInputDTO) {
        ArrayList arrayList = new ArrayList();
        Record record = recordInputDTO.convertToRecord();
        Iterator iterator = recordDAO.autoCompleteFind().iterator();
        Map hashMap = new HashMap();
        int count = articleDAO.findUsingByRecord(record).size();
        hashMap.put("keyword", recordInputDTO.getKeyword());
        hashMap.put("count", count);
        arrayList.add(hashMap);
        while (iterator.hasNext()) {
            Map map = (HashMap) iterator.next();
            String key = map.get("keyword").toString();
            Record r = new Record();
            r.setKeyword(key);
            if (key.indexOf(recordInputDTO.getKeyword()) < 0 || key.equals(recordInputDTO.getKeyword())) {
                continue;
            } else {
                count = articleDAO.findUsingByRecord(r).size();
                map.put("count", count);
                arrayList.add(map);
            }
        }
        return arrayList;
    }
}
