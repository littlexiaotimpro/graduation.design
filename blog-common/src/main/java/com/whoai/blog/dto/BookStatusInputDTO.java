package com.whoai.blog.dto;

import com.whoai.blog.entity.Book;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName BookStatusInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/614:54
 */
@Data
public class BookStatusInputDTO {
    private String enbook;

    private Integer status;

    /**
     * 正向转化
     *
     * @return
     */
    public Book convertToBook() {
        BookStatusInputDTOConvert bookStatusInputDTOConvert = new BookStatusInputDTOConvert();
        Book convert = bookStatusInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param book
     * @return
     */
    public BookStatusInputDTO convertFor(Book book) {
        BookStatusInputDTOConvert bookStatusInputDTOConvert = new BookStatusInputDTOConvert();
        BookStatusInputDTO convert = bookStatusInputDTOConvert.reverse().convert(book);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class BookStatusInputDTOConvert extends Converter<BookStatusInputDTO, Book> {
        @Override
        protected Book doForward(BookStatusInputDTO bookStatusInputDTO) {
            Book book = new Book();
            BeanUtils.copyProperties(bookStatusInputDTO, book);
            return book;
        }

        @Override
        protected BookStatusInputDTO doBackward(Book book) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
