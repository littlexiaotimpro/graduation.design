package com.whoai.blog.config.handler;

import com.whoai.blog.constant.Permission;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义 mybatis 实体属性类型解析器
 *
 * @date 2022/2/24
 * @see Permission
 */
@Slf4j
public class PermissionTypeHandler extends BaseTypeHandler<Permission> {

    /**
     * 设置属性值
     *
     * @param ps        SQL预处理对象
     * @param i         字段属性索引位
     * @param parameter 字段属性对应参数
     * @param jdbcType  字段属性类型
     * @throws SQLException 异常
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Permission parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public Permission getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        Integer code = object instanceof Integer ? (Integer) object : null;
        return Permission.convert(code);
    }

    @Override
    public Permission getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object object = rs.getObject(columnIndex);
        Integer code = object instanceof Integer ? (Integer) object : null;
        return Permission.convert(code);
    }

    @Override
    public Permission getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object object = cs.getObject(columnIndex);
        Integer code = object instanceof Integer ? (Integer) object : null;
        return Permission.convert(code);
    }
}
