package com.whoai.blog.config.handler;

import com.whoai.blog.constant.Role;
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
 * @see Role
 * @since 2022/04/11
 */
@Slf4j
public class RoleTypeHandler extends BaseTypeHandler<Role> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public Role getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        Integer code = object instanceof Integer ? (Integer) object : null;
        return Role.convert(code);
    }

    @Override
    public Role getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object object = rs.getObject(columnIndex);
        Integer code = object instanceof Integer ? (Integer) object : null;
        return Role.convert(code);
    }

    @Override
    public Role getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object object = cs.getObject(columnIndex);
        Integer code = object instanceof Integer ? (Integer) object : null;
        return Role.convert(code);
    }
}
