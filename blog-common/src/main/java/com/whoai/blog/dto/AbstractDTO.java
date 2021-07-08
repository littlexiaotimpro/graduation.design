package com.whoai.blog.dto;

import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

/**
 * @param <A_DTO>    DTO对象
 * @param <A_Entity> 目标对象
 */
public abstract class AbstractDTO<A_DTO, A_Entity> {

    public abstract A_DTO convertToDTO(A_Entity entity);

    public abstract A_Entity convertToEntity();

    protected DTOConvert<A_DTO, A_Entity> converter() {
        return new DTOConvert<>();
    }

    static class DTOConvert<DTO, Entity> extends Converter<DTO, Entity> {

        private DTO dto;

        private Entity entity;

        public void setDto(DTO dto) {
            this.dto = dto;
        }

        public void setEntity(Entity entity) {
            this.entity = entity;
        }

        @Override
        protected Entity doForward(@NonNull DTO dto) {
            BeanUtils.copyProperties(dto, entity);
            return entity;
        }

        @Override
        protected DTO doBackward(@NonNull Entity entity) {
            BeanUtils.copyProperties(entity,dto );
            return dto;
        }
    }

}
