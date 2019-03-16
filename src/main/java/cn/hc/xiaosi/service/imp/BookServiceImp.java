package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.BookDAO;
import cn.hc.xiaosi.dto.BookCateTagInputDTO;
import cn.hc.xiaosi.dto.BookInputDTO;
import cn.hc.xiaosi.dto.BookOutputDTO;
import cn.hc.xiaosi.dto.BookStatusInputDTO;
import cn.hc.xiaosi.entity.Book;
import cn.hc.xiaosi.service.BookService;
import cn.hc.xiaosi.utils.OSSClientUtil;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static cn.hc.xiaosi.bean.OSSClientConstants.BACKET_NAME;
import static cn.hc.xiaosi.bean.OSSClientConstants.FOLDER;

/**
 * @ClassName BookServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/615:32
 */
@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public ArrayList<Book> controlFindAll() {
        return bookDAO.findAll();
    }

    @Override
    public Message controlSaveBook(BookInputDTO bookInputDTO) {

        /**
         * 调用图片上传工具类，上传图片
         */
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        File file = new File(bookInputDTO.getImgbook());
        String category = bookInputDTO.getEncategory() + "/";
        String md5key = OSSClientUtil.uploadObject2OSS(ossClient, file, BACKET_NAME, FOLDER, category);
        System.out.println("上传后的文件MD5数字唯一签名:" + md5key);

        /**
         * 向数据库添加新数据
         */
        Book book = bookInputDTO.convertToBook();
        Integer result = bookDAO.saveBook(book);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("添加失败!");
        } else {
            return message.setCode(1).setMsg("添加成功!");
        }
    }

    @Override
    public Message controlDeleteBook(BookStatusInputDTO bookStatusInputDTO) {
        Book book = bookStatusInputDTO.convertToBook();
        Integer result = bookDAO.deleteBook(book);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
    }

    @Override
    public Message controlUpdateBook(BookInputDTO bookInputDTO) {

        /**
         * 调用图片上传工具类，上传图片
         */
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        File file = new File(bookInputDTO.getImgbook());
        String category = bookInputDTO.getEncategory() + "/";
        String md5key = OSSClientUtil.uploadObject2OSS(ossClient, file, BACKET_NAME, FOLDER, category);
        System.out.println("上传后的文件MD5数字唯一签名:" + md5key);

        /**
         * 更新数据
         */
        Book book = bookInputDTO.convertToBook();
        Integer result = bookDAO.updateBook(book);
        Message message = new Message();
        if (result == null || result == 0) {
            return message.setCode(-1).setMsg("操作失败!");
        } else {
            return message.setCode(1).setMsg("操作成功!");
        }
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
