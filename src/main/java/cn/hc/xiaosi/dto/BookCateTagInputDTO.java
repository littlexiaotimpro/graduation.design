package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Book;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName BookCateTagInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/614:55
 */
@Data
public class BookCateTagInputDTO {
    private String encategory;

    private String entag;

    /**
     * 正向转化
     *
     * @return
     */
    public Book convertToBook() {
        BookCateTagInputDTOConvert bookCateTagInputDTOConvert = new BookCateTagInputDTOConvert();
        Book convert = bookCateTagInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param book
     * @return
     */
    public BookCateTagInputDTO convertFor(Book book) {
        BookCateTagInputDTOConvert bookCateTagInputDTOConvert = new BookCateTagInputDTOConvert();
        BookCateTagInputDTO convert = bookCateTagInputDTOConvert.reverse().convert(book);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class BookCateTagInputDTOConvert extends Converter<BookCateTagInputDTO, Book> {
        @Override
        protected Book doForward(BookCateTagInputDTO bookCateTagInputDTO) {
            Book book = new Book();
            BeanUtils.copyProperties(bookCateTagInputDTO, book);
            return book;
        }

        @Override
        protected BookCateTagInputDTO doBackward(Book book) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }
}
