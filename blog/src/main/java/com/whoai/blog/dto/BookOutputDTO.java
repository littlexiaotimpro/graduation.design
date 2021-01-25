package com.whoai.blog.dto;

import com.whoai.blog.entity.Book;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName BookOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/614:54
 */
@Data
public class BookOutputDTO {
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

    /**
     * 正向转化
     *
     * @return
     */
    public Book convertToBook() {
        BookOutputDTOConvert bookOutputDTOConvert = new BookOutputDTOConvert();
        Book convert = bookOutputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param book
     * @return
     */
    public BookOutputDTO convertFor(Book book) {
        BookOutputDTOConvert bookOutputDTOConvert = new BookOutputDTOConvert();
        BookOutputDTO convert = bookOutputDTOConvert.reverse().convert(book);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class BookOutputDTOConvert extends Converter<BookOutputDTO, Book> {
        @Override
        protected Book doForward(BookOutputDTO bookOutputDTO) {
            throw new AssertionError("不支持正向转化方法");
        }

        @Override
        protected BookOutputDTO doBackward(Book book) {
            BookOutputDTO bookOutputDTO = new BookOutputDTO();
            BeanUtils.copyProperties(book, bookOutputDTO);
            return bookOutputDTO;
        }
    }

}
