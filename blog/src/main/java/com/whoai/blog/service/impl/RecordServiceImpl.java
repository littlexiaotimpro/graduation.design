package com.whoai.blog.service.impl;

import com.whoai.blog.dao.ArticleDAO;
import com.whoai.blog.dao.RecordDAO;
import com.whoai.blog.dto.RecordInputDTO;
import com.whoai.blog.entity.Record;
import com.whoai.blog.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDAO recordDAO;

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Record> findAll() {
        return recordDAO.findAll();
    }

    @Override
    @Transactional
    public Integer saveRecord(RecordInputDTO recordInputDTO) {
        // 搜索无需校验登录状态
        Record record = recordInputDTO.convertToEntity();
        return recordDAO.saveRecord(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object> autoComplete(RecordInputDTO recordInputDTO) {
        List<Object> arrayList = new ArrayList<>();
        Record record = recordInputDTO.convertToEntity();
        Iterator<Object> iterator = recordDAO.autoComplete().iterator();
        Map<String,Object> hashMap = new HashMap<>();
        int count = articleDAO.findByKeyword(record.getKeyword()).size();
        hashMap.put("keyword", recordInputDTO.getKeyword());
        hashMap.put("count", count);
        arrayList.add(hashMap);
        while (iterator.hasNext()) {
            Map<String,Object> map = (Map<String,Object>) iterator.next();
            String key = map.get("keyword").toString();
            if (!key.contains(recordInputDTO.getKeyword()) || key.equals(recordInputDTO.getKeyword())) {
                continue;
            }
            count = articleDAO.findByKeyword(key).size();
            map.put("count", count);
            arrayList.add(map);
        }
        return arrayList;
    }
}
