package com.whoai.blog.dto;

public abstract class AbstractOutputDTO<DTO, Entity> extends AbstractDTO<DTO, Entity> {

    @Override
    public Entity convertToEntity() {
        throw new AssertionError("不支持正向转换！");
    }
}
