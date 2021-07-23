package com.whoai.blog.dto;

import com.whoai.blog.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookCateTagInputDTO extends AbstractInputDTO<BookCateTagInputDTO,Book> {
    private String encategory;

    private String entag;

    @Override
    public Book convertToEntity() {
        final DTOConvert<BookCateTagInputDTO, Book> converter = converter();
        converter.setEntity(new Book());
        return converter.convert(this);
    }
}
