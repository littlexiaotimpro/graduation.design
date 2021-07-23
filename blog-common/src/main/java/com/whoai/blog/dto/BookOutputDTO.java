package com.whoai.blog.dto;

import com.whoai.blog.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookOutputDTO extends AbstractOutputDTO<BookOutputDTO, Book> {
    private String enbook;

    /**
     * 获取文章路径
     */
    private String enarticle;

    private String encategory;

    private String entag;

    private String imgbook;

    private String caption;

    private String author;

    private String summary;

    /**
     * 链接地址
     */
    private String address;

    @Override
    public BookOutputDTO convertToDTO(Book book) {
        final DTOConvert<BookOutputDTO, Book> converter = converter();
        converter.setDto(new BookOutputDTO());
        return converter.reverse().convert(book);
    }
}
