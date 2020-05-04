package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;

import java.util.List;
import java.util.Optional;

/**
 * @author 张伟金
 * @date 2020/4/30-18:05
 */
public interface BookUploadBiz {
    /**
     * 分页查询book
     * @param pageVo 分页
     * @param book 图书
     * @return 分页对象
     */
    public PageVo<Book> findBookByPage(PageVo<Book> pageVo, Book book);

    /**
     * 通过图书名查找所对应所有的图书
     * @param book 图书对象
     * @return 集合
     */
    public List<Book> findBookByName(Book book);

    /**
     * 上传图书
     * @param book
     */
    public void uploadBook(Book book);

    public void deleteBookById(int bid);

    public Book findBookById(int bid);

    public void updateBook(Book book);
}
