package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;

public interface BookAuditBiz {
    /**
     * 分页查询bookAudit
     * @param pageVo 分页
     * @param book 图书
     * @return 分页对象
     */
    public PageVo<Book> findBookAuditByPage(PageVo<Book> pageVo, Book book);

    public Book getBookById(int id);

    public void updateBook(Book book);
}
