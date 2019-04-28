package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.dto.ContactOutputDTO;
import cn.hc.xiaosi.entity.Contact;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * ContactDAO继承基类
 */
@Repository
public interface ContactDAO extends MyBatisBaseDao<Contact, Integer> {

    /**
     * 管理端获取所有数据
     *
     * @return
     */
    ArrayList<Contact> findAll();

    /**
     * 用户建议新增
     *
     * @param contact
     * @return
     */
    Integer saveContact(@Param("contact") Contact contact);

    /**
     * 建议择优
     *
     * @param contact
     * @return
     */
    Integer deleteContact(@Param("contact") Contact contact);

    /**
     * 客户端优秀建议展示
     *
     * @return
     */
    ArrayList<ContactOutputDTO> findAllUsing();

}