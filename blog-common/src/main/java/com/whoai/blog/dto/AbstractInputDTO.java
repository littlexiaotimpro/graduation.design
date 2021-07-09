package com.whoai.blog.dto;

public abstract class AbstractInputDTO<DTO, Entity> extends AbstractDTO<DTO, Entity> {

    @Override
    public DTO convertToDTO(Entity entity) {
        throw new AssertionError("不支持逆向转换！");
    }
}
