package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.BookDAO;
import cn.hc.xiaosi.dto.BookCateTagInputDTO;
import cn.hc.xiaosi.dto.BookInputDTO;
import cn.hc.xiaosi.dto.BookOutputDTO;
import cn.hc.xiaosi.dto.BookStatusInputDTO;
import cn.hc.xiaosi.entity.Book;
import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.service.BookService;
import cn.hc.xiaosi.service.LogService;
import cn.hc.xiaosi.utils.JWTUtil;
import cn.hc.xiaosi.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName BookServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/615:32
 */
@Service
@Slf4j
public class BookServiceImp implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private LogService logService;

    @Override
    public ArrayList<Book> controlFindAll() {
        return bookDAO.findAll();
    }

    @Override
    public String controlSaveIMG(MultipartFile file, String category, HttpServletRequest request) throws IOException {
        return UploadUtil.getFileUrl(file, category, logService, request);
    }

    @Override
    public Message controlSaveBook(BookInputDTO bookInputDTO, HttpServletRequest request) {

        /**
         * 向数据库添加新数据
         */
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试新增书籍数据。", operator);
            Book book = bookInputDTO.convertToBook();
            Integer result = bookDAO.saveBook(book);
            if (result == null || result == 0) {
                log.info("管理员[{}]新增书籍数据失败", operator);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增书籍数据失败");
                message.setCode(-1).setMsg("添加失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]新增书籍数据成功，新增数据为：[{}]", operator, bookInputDTO);
                }
                log.info("管理员[{}]新增书籍数据成功，新增数据为：[{}]，影响结果数：[{}]", operator, bookInputDTO, result);
                logBean.setOperation("新增").setOperator(operator).setContent("管理员" + operator + "新增书籍数据成功，新增数据为：" + bookInputDTO);
                message.setCode(1).setMsg("添加成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlDeleteBook(BookStatusInputDTO bookStatusInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改书籍数据状态。", operator);
            Book book = bookStatusInputDTO.convertToBook();
            Integer result = bookDAO.deleteBook(book);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改书籍数据状态失败", operator);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改书籍数据状态失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改书籍数据状态成功，修改的书籍数据为：enMedia=[{}], status=[{}]", operator, bookStatusInputDTO.getEnbook(), bookStatusInputDTO.getStatus());
                }
                log.info("管理员[{}]修改书籍数据状态成功，修改的书籍数据为：enMedia=[{}], status=[{}]，影响结果数：[{}]", operator, bookStatusInputDTO.getEnbook(), bookStatusInputDTO.getStatus(), result);
                logBean.setOperation("删除").setOperator(operator).setContent("管理员" + operator + "修改书籍数据状态成功，修改的书籍数据为：" + bookStatusInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public Message controlUpdateBook(BookInputDTO bookInputDTO, HttpServletRequest request) {

        /**
         * 更新数据
         */
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改书籍数据。", operator);
            Book book = bookInputDTO.convertToBook();
            Integer result = bookDAO.updateBook(book);
            if (result == null || result == 0) {
                log.info("管理员[{}]修改书籍数据失败", operator);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改书籍数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]修改书籍数据成功，修改的书籍数据为：[{}]", operator, bookInputDTO);
                }
                log.info("管理员[{}]修改书籍数据成功，修改的书籍数据为：[{}]，影响结果数：[{}]", operator, bookInputDTO, result);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "修改书籍数据成功，修改的书籍数据为：" + bookInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }

    @Override
    public ArrayList<BookOutputDTO> clientFindByEnCateTag(BookCateTagInputDTO bookCateTagInputDTO) {
        Book book = bookCateTagInputDTO.convertToBook();
        ArrayList<BookOutputDTO> arrayList = new ArrayList<BookOutputDTO>();
        Iterator iterator = bookDAO.findUsingByEnCateTag(book).iterator();
        while (iterator.hasNext()) {
            BookOutputDTO bookOutputDTO = new BookOutputDTO();
            bookOutputDTO = bookOutputDTO.convertFor((Book) iterator.next());
            arrayList.add(bookOutputDTO);
        }
        return arrayList;
    }
}
