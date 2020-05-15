package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.BookAuditBiz;
import com.bateng.guestroom.dao.BookAuditDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "bookAuditBiz")
public class BookAuditBizImpl implements BookAuditBiz {
    @Autowired
    private BookAuditDao bookAuditDao;

    @Override
    public PageVo<Book> findBookAuditByPage(PageVo<Book> pageVo, Book book) {
        return bookAuditDao.findBookAuditByPage(pageVo,book);
    }

    @Override
    public Book getBookById(int id) {
        return bookAuditDao.findBookById(id);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        bookAuditDao.updateBook(book.getRea(),book.getFlag(),book.getId());
    }
}
