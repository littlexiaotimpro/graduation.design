package com.whoai.blog.dto;

import com.whoai.blog.entity.Record;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecordInputDTO extends AbstractInputDTO<RecordInputDTO, Record> {

    private String keyword;

    @Override
    public Record convertToEntity() {
        final DTOConvert<RecordInputDTO, Record> converter = converter();
        converter.setEntity(new Record());
        return converter.convert(this);
    }
}
