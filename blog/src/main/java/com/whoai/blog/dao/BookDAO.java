package com.whoai.blog.dao;

import com.whoai.blog.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * BookDAO继承基类
 */
@Repository
public interface BookDAO extends MyBatisBaseDao<Book, String> {

    /**
     * 管理端获取所有书籍
     *
     * @return
     */
    ArrayList<Book> findAll();

    /**
     * 新增书籍
     *
     * @param book
     * @return
     */
    Integer saveBook(@Param("book") Book book);

    /**
     * 启用，禁用书籍
     *
     * @param book
     * @return
     */
    Integer deleteBook(@Param("book") Book book);

    /**
     * 编辑书籍信息
     *
     * @param book
     * @return
     */
    Integer updateBook(@Param("book") Book book);

    /**
     * 通过分类，标签查询所有启用的书籍
     *
     * @param book
     * @return
     */
    ArrayList<Book> findUsingByEnCateTag(@Param("book") Book book);

}