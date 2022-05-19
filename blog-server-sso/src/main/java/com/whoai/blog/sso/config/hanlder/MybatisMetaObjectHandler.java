package com.whoai.blog.sso.config.hanlder;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.whoai.blog.sso.UserLoginInfo;
import com.whoai.blog.sso.UserLoginInfoHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Mybatis 元数据处理
 *
 * @author xiaosi
 * @date 2022/5/17
 * @since 1.0
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    private final static String STR_ACTIVE = "active";
    private final static String STR_GMT_CREATE = "gmtCreated";
    private final static String STR_CREATE_BY = "createBy";
    private final static String STR_CREATE_USER = "createUser";
    private final static String STR_GMT_MODIFY = "gmtModify";
    private final static String STR_MODIFY_BY = "modifyBy";
    private final static String STR_MODIFY_USER = "modifyUser";


    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置时间及状态
        if (metaObject.hasSetter(STR_ACTIVE)) {
            this.setFieldValByName(STR_ACTIVE, 1, metaObject);
        }
        if (metaObject.hasSetter(STR_GMT_CREATE)) {
            this.setFieldValByName(STR_GMT_CREATE, new Date(), metaObject);
        }
        if (metaObject.hasSetter(STR_GMT_MODIFY)) {
            this.setFieldValByName(STR_GMT_MODIFY, new Date(), metaObject);
        }
        // 设置当前登录用户信息
        UserLoginInfo user = UserLoginInfoHolder.getUser();
        if (ObjectUtil.isNotNull(user)) {
            if (metaObject.hasSetter(STR_CREATE_BY)) {
                this.setFieldValByName(STR_CREATE_BY, user.getId(), metaObject);
            }
            if (metaObject.hasSetter(STR_CREATE_USER)) {
                this.setFieldValByName(STR_CREATE_USER, user.getUsername(), metaObject);
            }
            if (metaObject.hasSetter(STR_MODIFY_BY)) {
                this.setFieldValByName(STR_MODIFY_BY, user.getId(), metaObject);
            }
            if (metaObject.hasSetter(STR_MODIFY_USER)) {
                this.setFieldValByName(STR_MODIFY_USER, user.getUsername(), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置修改时间
        if (metaObject.hasSetter(STR_GMT_MODIFY)) {
            this.setFieldValByName(STR_GMT_MODIFY, new Date(), metaObject);
        }
        // 设置当前登录用户信息
        UserLoginInfo user = UserLoginInfoHolder.getUser();
        if (ObjectUtil.isNotNull(user)) {
            if (metaObject.hasSetter(STR_MODIFY_BY)) {
                this.setFieldValByName(STR_MODIFY_BY, user.getId(), metaObject);
            }
            if (metaObject.hasSetter(STR_MODIFY_USER)) {
                this.setFieldValByName(STR_MODIFY_USER, user.getUsername(), metaObject);
            }
        }
    }
}
