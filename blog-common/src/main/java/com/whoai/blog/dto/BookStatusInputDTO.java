package com.whoai.blog.dto;

import com.whoai.blog.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookStatusInputDTO extends AbstractInputDTO<BookStatusInputDTO, Book> {
    private String enbook;

    private Integer status;

    @Override
    public Book convertToEntity() {
        final DTOConvert<BookStatusInputDTO, Book> converter = converter();
        converter.setEntity(new Book());
        return converter.convert(this);
    }
}
