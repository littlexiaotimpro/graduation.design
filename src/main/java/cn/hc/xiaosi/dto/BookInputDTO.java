package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Book;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName BookInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/614:55
 */
@Data
public class BookInputDTO {
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

    /**
     * 正向转化
     *
     * @return
     */
    public Book convertToBook() {
        BookInputDTOConvert bookInputDTOConvert = new BookInputDTOConvert();
        Book convert = bookInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param book
     * @return
     */
    public BookInputDTO convertFor(Book book) {
        BookInputDTOConvert bookInputDTOConvert = new BookInputDTOConvert();
        BookInputDTO convert = bookInputDTOConvert.reverse().convert(book);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class BookInputDTOConvert extends Converter<BookInputDTO, Book> {
        @Override
        protected Book doForward(BookInputDTO bookInputDTO) {
            Book book = new Book();
            BeanUtils.copyProperties(bookInputDTO, book);
            return book;
        }

        @Override
        protected BookInputDTO doBackward(Book book) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
