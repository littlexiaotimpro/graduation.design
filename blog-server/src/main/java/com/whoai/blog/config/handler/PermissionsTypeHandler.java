package com.whoai.blog.config.handler;

import com.whoai.blog.constant.Permission;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限列表
 *
 * @since 2022/4/12
 */
public class PermissionsTypeHandler extends BaseTypeHandler<List<Permission>> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Permission> parameter, JdbcType jdbcType) throws SQLException {
        Set<String> codes = parameter.stream()
                .map(permission -> permission.getCode().toString())
                .collect(Collectors.toSet());
        ps.setString(i, String.join(",", codes));
    }

    @Override
    public List<Permission> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        String codes = object != null ? object.toString() : null;
        return Permission.converts(codes);
    }

    @Override
    public List<Permission> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object object = rs.getObject(columnIndex);
        String codes = object != null ? object.toString() : null;
        return Permission.converts(codes);
    }

    @Override
    public List<Permission> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object object = cs.getObject(columnIndex);
        String codes = object != null ? object.toString() : null;
        return Permission.converts(codes);
    }
}
