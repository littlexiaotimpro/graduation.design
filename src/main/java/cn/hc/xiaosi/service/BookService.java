package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.BookCateTagInputDTO;
import cn.hc.xiaosi.dto.BookInputDTO;
import cn.hc.xiaosi.dto.BookOutputDTO;
import cn.hc.xiaosi.dto.BookStatusInputDTO;
import cn.hc.xiaosi.entity.Book;

import java.util.ArrayList;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/615:31
 */
public interface BookService {

    /**
     * 管理端输出
     *
     * @return
     */
    ArrayList<Book> controlFindAll();

    /**
     * 新增书籍
     *
     * @param bookInputDTO
     * @return
     */
    Message controlSaveBook(BookInputDTO bookInputDTO);

    /**
     * 启用，禁用书籍
     *
     * @param bookStatusInputDTO
     * @return
     */
    Message controlDeleteBook(BookStatusInputDTO bookStatusInputDTO);

    /**
     * 编辑书籍信息
     *
     * @param bookInputDTO
     * @return
     */
    Message controlUpdateBook(BookInputDTO bookInputDTO);

    /**
     * 客户端根据分类，标签查询
     *
     * @param bookCateTagInputDTO
     * @return
     */
    ArrayList<BookOutputDTO> clientFindByEnCateTag(BookCateTagInputDTO bookCateTagInputDTO);

}
