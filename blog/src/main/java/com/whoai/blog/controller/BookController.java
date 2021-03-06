package com.whoai.blog.controller;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.BookCateTagInputDTO;
import com.whoai.blog.dto.BookInputDTO;
import com.whoai.blog.dto.BookOutputDTO;
import com.whoai.blog.dto.BookStatusInputDTO;
import com.whoai.blog.entity.Book;
import com.whoai.blog.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
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
    public String saveIMG(@PathParam("imgbook") MultipartFile file, @PathParam("category") String category, HttpServletRequest request) throws IOException {
        return bookService.controlSaveIMG(file, category, request);
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增书籍")
    public Message saveBook(@RequestBody BookInputDTO bookInputDTO, HttpServletRequest request) {
        return bookService.controlSaveBook(bookInputDTO, request);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用书籍")
    public Message deleteBook(@RequestBody BookStatusInputDTO bookStatusInputDTO, HttpServletRequest request) {
        return bookService.controlDeleteBook(bookStatusInputDTO, request);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑书籍信息")
    public Message updateBook(@RequestBody BookInputDTO bookInputDTO, HttpServletRequest request) {
        return bookService.controlUpdateBook(bookInputDTO, request);
    }

    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端输出")
    public ArrayList<BookOutputDTO> bookOutputDTOS(@RequestBody BookCateTagInputDTO bookCateTagInputDTO) {
        return bookService.clientFindByEnCateTag(bookCateTagInputDTO);
    }

}
