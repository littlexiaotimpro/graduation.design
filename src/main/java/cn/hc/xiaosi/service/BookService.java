package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.BookCateTagInputDTO;
import cn.hc.xiaosi.dto.BookInputDTO;
import cn.hc.xiaosi.dto.BookOutputDTO;
import cn.hc.xiaosi.dto.BookStatusInputDTO;
import cn.hc.xiaosi.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
     * 上传图片
     *
     * @param file
     * @param category
     * @param request
     * @return
     */
    String controlSaveIMG(MultipartFile file, String category, HttpServletRequest request) throws IOException;

    /**
     * 新增书籍
     *
     * @param bookInputDTO
     * @param request
     * @return
     */
    Message controlSaveBook(BookInputDTO bookInputDTO, HttpServletRequest request);

    /**
     * 启用，禁用书籍
     *
     * @param bookStatusInputDTO
     * @param request
     * @return
     */
    Message controlDeleteBook(BookStatusInputDTO bookStatusInputDTO, HttpServletRequest request);

    /**
     * 编辑书籍信息
     *
     * @param bookInputDTO
     * @param request
     * @return
     */
    Message controlUpdateBook(BookInputDTO bookInputDTO, HttpServletRequest request);

    /**
     * 客户端根据分类，标签查询
     *
     * @param bookCateTagInputDTO
     * @return
     */
    ArrayList<BookOutputDTO> clientFindByEnCateTag(BookCateTagInputDTO bookCateTagInputDTO);

}
