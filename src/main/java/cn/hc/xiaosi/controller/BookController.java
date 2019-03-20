package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.BookCateTagInputDTO;
import cn.hc.xiaosi.dto.BookInputDTO;
import cn.hc.xiaosi.dto.BookOutputDTO;
import cn.hc.xiaosi.dto.BookStatusInputDTO;
import cn.hc.xiaosi.entity.Book;
import cn.hc.xiaosi.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

/**
 * @ClassName BookController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/615:42
 */
@RestController
@RequestMapping(value = "book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "管理端输出")
    public ArrayList<Book> books() {
        return bookService.controlFindAll();
    }

    @RequestMapping(value = "img")
    @ApiOperation(value = "添加图片数据")
    public String saveIMG(@PathParam("imgbook") MultipartFile file, @PathParam("category") String category) {
        return bookService.controlSaveIMG(file, category);
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增书籍")
    public Message saveBook(@RequestBody BookInputDTO bookInputDTO) {
        return bookService.controlSaveBook(bookInputDTO);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用书籍")
    public Message deleteBook(@RequestBody BookStatusInputDTO bookStatusInputDTO) {
        return bookService.controlDeleteBook(bookStatusInputDTO);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑书籍信息")
    public Message updateBook(@RequestBody BookInputDTO bookInputDTO) {
        return bookService.controlUpdateBook(bookInputDTO);
    }

    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端输出")
    public ArrayList<BookOutputDTO> bookOutputDTOS(@RequestBody BookCateTagInputDTO bookCateTagInputDTO) {
        return bookService.clientFindByEnCateTag(bookCateTagInputDTO);
    }

}
