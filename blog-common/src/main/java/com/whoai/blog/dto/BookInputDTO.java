package com.whoai.blog.dto;

import com.whoai.blog.entity.Book;
import lombok.Data;

@Data
public class BookInputDTO extends AbstractInputDTO<BookInputDTO,Book> {
    private String enbook;

    private String ennav;

    private String enarticle;

    private String encategory;

    private String entag;

    private String adminid;

    private String imgbook;

    private String caption;

    private String author;

    private String summary;

    private String address;

    private Integer status;

    @Override
    public Book convertToEntity() {
        final DTOConvert<BookInputDTO, Book> converter = converter();
        converter.setEntity(new Book());
        return converter.convert(this);
    }
}
